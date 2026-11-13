#!/usr/bin/env python3
"""Build separate course-notes and exercise PDFs; Python uses only its standard library."""

import argparse
import hashlib
import html
from html.parser import HTMLParser
import json
import os
import platform
from pathlib import Path
import re
import shutil
import subprocess
import sys
import tempfile
from urllib.parse import unquote, urlsplit


SCRIPT_DIR = Path(__file__).resolve().parent
ROOT = SCRIPT_DIR.parents[1]
REPOSITORY = "https://github.com/nbicocchi/learn-java-core/blob/main/"
TEMPLATE = SCRIPT_DIR / "templates/eisvogel/eisvogel.latex"


def run(command, **kwargs):
    return subprocess.run(command, check=True, text=True, **kwargs)


def natural_key(path):
    return [int(part) if part.isdigit() else part.casefold()
            for part in re.split(r"(\d+)", path.name)]


def printable_markdown(text):
    """Expose collapsible answers, without changing HTML-like text in code."""
    lines = []
    fence = None
    for line in text.splitlines():
        marker = re.match(r"^\s{0,3}(`{3,}|~{3,})", line)
        if marker:
            token = marker.group(1)
            if fence is None:
                fence = token
            elif token[0] == fence[0] and len(token) >= len(fence):
                fence = None
            lines.append(line)
            continue
        if fence is None:
            line = re.sub(r"</?details\b[^>]*>", "", line, flags=re.I)
            line = re.sub(
                r"<summary\b[^>]*>(.*?)</summary>",
                lambda match: "\n**" + html.unescape(match.group(1)) + "**\n",
                line, flags=re.I,
            )
        lines.append(line)
    return "\n".join(lines) + "\n"


def read_document(path):
    result = run(
        ["pandoc", "--from=gfm", "--to=json"],
        input=printable_markdown(path.read_text(encoding="utf-8")),
        stdout=subprocess.PIPE,
    )
    return json.loads(result.stdout)


def nodes(value):
    if isinstance(value, dict):
        if "t" in value:
            yield value
        for child in value.values():
            yield from nodes(child)
    elif isinstance(value, list):
        for child in value:
            yield from nodes(child)


def heading(level, title, identifier):
    return {"t": "Header", "c": [level, [identifier, [], []],
            [{"t": "Str", "c": title}]]}


def installation_command(packages):
    try:
        release = platform.freedesktop_os_release()
    except OSError:
        release = {}
    distributions = {release.get("ID", ""), *release.get("ID_LIKE", "").split()}
    if distributions & {"arch", "cachyos", "manjaro"}:
        arch_packages = {
            "pandoc": "pandoc-cli",
            "texlive-binaries": "texlive-bin",
            "texlive-xetex": "texlive-xetex",
            "texlive-latex-base": "texlive-latex",
            "texlive-latex-recommended": "texlive-latexrecommended",
            "texlive-latex-extra": "texlive-latexextra",
            "texlive-fonts-recommended": "texlive-fontsrecommended",
            "fonts-dejavu-core": "ttf-dejavu",
            "librsvg2-bin": "librsvg",
        }
        return "sudo pacman -S --needed " + " ".join(
            arch_packages.get(package, package) for package in packages)
    return "sudo apt install " + " ".join(packages)


def dependency_error(sources):
    """Report all missing executables with concrete installation instructions."""
    requirements = [
        (("pandoc",), "Pandoc", ["pandoc"]),
        (("xelatex",), "XeLaTeX", ["texlive-xetex", "texlive-latex-extra",
                                   "texlive-fonts-recommended", "fonts-dejavu-core"]),
        (("kpsewhich",), "TeX package checker", ["texlive-binaries"]),
        (("rsvg-convert",), "SVG converter", ["librsvg2-bin"]),
        (("magick", "convert"), "ImageMagick", ["imagemagick"]),
    ]
    uses_mermaid = any(
        re.search(r"^\s{0,3}(?:`{3,}|~{3,})\s*mermaid\b", p.read_text(encoding="utf-8"), re.M)
        for p in sources
    )
    if uses_mermaid:
        requirements += [
            (("node",), "Node.js (required by Mermaid CLI)", ["nodejs"]),
            (("mmdc",), "Mermaid CLI", ["nodejs", "npm"]),
        ]
    missing = [(commands, name, packages) for commands, name, packages in requirements
               if not any(shutil.which(command) for command in commands)]
    if not missing:
        return None
    lines = ["Missing required tools:"]
    packages = []
    for commands, name, needed in missing:
        lines.append(f"  - {name}: {' or '.join(commands)} not found in PATH")
        for package in needed:
            if package not in packages:
                packages.append(package)
    lines += ["", "Install the missing tools:", "  " + installation_command(packages)]
    if any(commands == ("mmdc",) for commands, _, _ in missing):
        lines.append('  npm install --global --prefix "$HOME/.local" @mermaid-js/mermaid-cli@11.4.1')
        lines.append('  export PATH="$HOME/.local/bin:$PATH"')
        lines.append("  Allow Puppeteer to download its Chrome browser during installation.")
    lines += ["", "On other systems, install the equivalent packages with your package manager.",
              "Then rerun this script, ensuring the commands are available in PATH.",
              "The existing PDF has not been modified."]
    return "\n".join(lines)


def latex_dependency_error():
    """Check Eisvogel's required TeX components before rendering the course."""
    requirements = {
        "texlive-latex-base": ["graphicx.sty", "longtable.sty", "afterpage.sty"],
        "texlive-latex-recommended": ["scrbook.cls", "fontspec.sty", "unicode-math.sty",
                                      "booktabs.sty", "setspace.sty"],
        "texlive-latex-extra": ["fvextra.sty", "pagecolor.sty", "csquotes.sty",
                               "mdframed.sty", "footnotebackref.sty", "titling.sty",
                               "footnotehyper.sty", "xurl.sty"],
    }
    files = [name for names in requirements.values() for name in names]
    result = subprocess.run(["kpsewhich", *files], text=True, capture_output=True)
    found = {Path(line).name for line in result.stdout.splitlines()}
    missing = [name for name in files if name not in found]
    if not missing:
        return None
    packages = [package for package, names in requirements.items()
                if any(name in missing for name in names)]
    return ("Missing LaTeX components required by Eisvogel:\n  - "
            + "\n  - ".join(missing) + "\n\nInstall the missing packages:\n  "
            + installation_command(packages) + "\nThe existing PDF has not been modified.")


class HTMLImages(HTMLParser):
    def __init__(self):
        super().__init__()
        self.targets = []

    def handle_starttag(self, tag, attrs):
        if tag in ("img", "image"):
            for name, value in attrs:
                if value and name in ("src", "href", "xlink:href"):
                    self.targets.append(value)


def image_targets(document):
    for node in nodes(document["blocks"]):
        if node["t"] == "Image":
            yield node["c"][2][0]
        elif node["t"] in ("RawBlock", "RawInline") and node["c"][0] == "html":
            parser = HTMLImages()
            parser.feed(node["c"][1])
            yield from parser.targets


def prepare_document(document, source, work, prefix, heading_offset=0):
    headers = {}
    for node in nodes(document["blocks"]):
        if node["t"] == "Header":
            content = node["c"]
            old_id = content[1][0]
            new_id = f"{prefix}-{old_id}"
            headers[old_id] = new_id
            content[1][0] = new_id
            if heading_offset:
                content[0] = min(content[0] + heading_offset, 6)

    for node in nodes(document["blocks"]):
        if node["t"] == "Image":
            target = node["c"][2]
            parsed = urlsplit(target[0])
            if parsed.scheme or parsed.netloc:
                raise ValueError(f"Use a local image in {source.name}: {target[0]}")
            image = (source.parent / unquote(parsed.path)).resolve()
            if not image.is_file():
                raise ValueError(f"Missing image in {source.name}: {target[0]}")
            digest = hashlib.sha256(str(image).encode()).hexdigest()[:16]
            if image.suffix.lower() in (".avif", ".webp", ".gif"):
                converted = work / f"image-{digest}.png"
                if not converted.exists():
                    run([shutil.which("magick") or shutil.which("convert"),
                         str(image) + "[0]", str(converted)])
                image = converted
            elif image.suffix.lower() == ".svg":
                converted = work / f"image-{digest}.pdf"
                if not converted.exists():
                    run(["rsvg-convert", "--format=pdf", "--output", str(converted), str(image)])
                image = converted
            target[0] = str(image)
            # Explicit bounds avoid oversized images and leave space for a baseline.
            node["c"][0][2] = [["width", "100%"], ["height", "180mm"]]
        elif node["t"] == "CodeBlock" and "mermaid" in node["c"][0][1]:
            diagram = node["c"][1]
            digest = hashlib.sha256(diagram.encode()).hexdigest()[:16]
            rendered = work / f"diagram-{digest}.png"
            if not rendered.exists():
                definition = rendered.with_suffix(".mmd")
                definition.write_text(diagram, encoding="utf-8")
                command = ["mmdc", "--input", str(definition), "--output", str(rendered),
                           "--backgroundColor", "white", "--scale", "2", "--quiet"]
                if os.environ.get("GITHUB_ACTIONS") == "true":
                    # Hosted Actions runners may not support Chromium's sandbox.
                    config = work / "puppeteer.json"
                    config.write_text(json.dumps({"args": ["--no-sandbox"]}), encoding="utf-8")
                    command += ["--puppeteerConfigFile", str(config)]
                environment = os.environ.copy()
                if not environment.get("PUPPETEER_EXECUTABLE_PATH"):
                    browser = next((path for name in (
                        "chromium", "chromium-browser", "google-chrome",
                        "google-chrome-stable", "chrome")
                        if (path := shutil.which(name))), None)
                    if browser:
                        environment["PUPPETEER_EXECUTABLE_PATH"] = browser
                run(command, env=environment)
            node.clear()
            node.update({"t": "Para", "c": [{"t": "Image", "c": [
                ["", [], [["width", "100%"], ["height", "180mm"]]],
                [{"t": "Str", "c": "Diagram"}], [str(rendered), ""]]}]})
        elif node["t"] == "Table":
            columns = node["c"][2]
            # Markdown tables have no physical widths. Give their text wrapping columns.
            for column in columns:
                if column[1]["t"] == "ColWidthDefault":
                    column[1] = {"t": "ColWidth", "c": 1 / len(columns)}
        elif node["t"] == "Str":
            # These decorative icons accompany explicit Yes/No labels in the notes.
            # Keep the labels rather than print missing-glyph boxes in text fonts.
            node["c"] = node["c"].replace("✅", "").replace("❌", "")
        elif node["t"] == "Link":
            target = node["c"][2]
            if target[0].startswith("#"):
                old_id = unquote(target[0][1:])
                if old_id in headers:
                    target[0] = "#" + headers[old_id]
            elif not urlsplit(target[0]).scheme and not target[0].startswith("//"):
                parsed = urlsplit(target[0])
                destination = (source.parent / unquote(parsed.path)).resolve()
                relative = destination.relative_to(ROOT).as_posix()
                target[0] = REPOSITORY + relative
                if parsed.fragment:
                    target[0] += "#" + parsed.fragment
    return document["blocks"]


def build_book(sources, output, title, subtitle, exercise_book=False):
    """Render one book, replacing its previous PDF only after a successful build."""
    output.parent.mkdir(parents=True, exist_ok=True)
    with tempfile.TemporaryDirectory(prefix=".course-book-", dir=output.parent) as folder:
        work = Path(folder)
        documents = [(source, read_document(source)) for source in sources]
        # Reject external images before starting any rendering or PDF generation.
        for source, document in documents:
            for target in image_targets(document):
                parsed = urlsplit(target)
                if parsed.scheme or parsed.netloc:
                    raise ValueError(f"Use a local image in {source.name}: {target}")
        book = documents[0][1]
        blocks = []
        for index, (source, document) in enumerate(documents):
            kind = "Exercises" if exercise_book else "Slide"
            print(f"{kind}: {source.stem}", flush=True)
            if exercise_book:
                blocks.append(heading(1, source.stem, f"exercise-collection-{index}"))
            blocks += prepare_document(document, source, work, f"document-{index}",
                                       heading_offset=1 if exercise_book else 0)
        book["blocks"] = blocks
        book["meta"] = {}
        manuscript = work / "book.json"
        manuscript.write_text(json.dumps(book, ensure_ascii=False), encoding="utf-8")
        header = work / "header.tex"
        header.write_text(r"""
\usepackage{fvextra}
\DefineVerbatimEnvironment{Highlighting}{Verbatim}{commandchars=\\\{\},breaklines,breakanywhere,fontsize=\small}
\usepackage{graphicx}
\setkeys{Gin}{keepaspectratio}
\setlength{\emergencystretch}{3em}
""", encoding="utf-8")
        temporary_pdf = work / "course-book.pdf"
        run(["pandoc", str(manuscript), "--from=json", "--standalone", "--template", str(TEMPLATE),
             "--pdf-engine=xelatex", "--top-level-division=chapter",
             "--toc", "--toc-depth=1", "--highlight-style=tango",
             f"--metadata=title:{title}", f"--metadata=subtitle:{subtitle}",
             "--metadata=author:Nicola Bicocchi",
             "--variable=book:true", "--variable=classoption:oneside", "--variable=classoption:open=any",
             "--variable=has-frontmatter:true", "--variable=papersize:a4",
             "--variable=geometry:margin=22mm,includehead,includefoot", "--variable=fontsize:10pt",
             "--variable=mainfont:DejaVu Sans", "--variable=monofont:DejaVu Sans Mono",
             "--variable=titlepage:true", "--variable=titlepage-color:193549",
             "--variable=titlepage-text-color:FFFFFF", "--variable=titlepage-rule-color:5CC8FF",
             "--variable=toc-own-page:true", f"--variable=header-left:{title}",
             "--variable=header-right:Java 25", "--variable=linestretch:1.1",
             "--variable=colorlinks:true", "--include-in-header", str(header),
             "--output", str(temporary_pdf)], cwd=work)
        if not temporary_pdf.is_file() or not temporary_pdf.read_bytes().startswith(b"%PDF-"):
            raise ValueError("The converter did not produce a valid PDF")
        # Both paths are on the same filesystem: failed builds never touch the old PDF.
        os.replace(temporary_pdf, output)
    unit = "exercise collections" if exercise_book else "lessons"
    print(f"Created {output} ({len(sources)} {unit})")


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--output-dir", type=Path, default=ROOT / "books",
                        help="Directory for both PDFs (default: books/)")
    parser.add_argument("--output", type=Path,
                        help="Override the course-notes PDF path (default: <output-dir>/Java Core.pdf)")
    parser.add_argument("--exercises-output", type=Path,
                        help="Override the exercises PDF path (default: <output-dir>/Java Core Exercises.pdf)")
    args = parser.parse_args()
    output_dir = args.output_dir.expanduser().resolve()
    notes_output = (args.output or output_dir / "Java Core.pdf").expanduser().resolve()
    exercises_output = (args.exercises_output or output_dir / "Java Core Exercises.pdf").expanduser().resolve()
    if notes_output == exercises_output:
        parser.error("The course-notes and exercises PDFs must have different output paths")

    slides = sorted(
        (path for path in (ROOT / "slides").glob("*.md") if path.name != "README.md"),
        key=natural_key,
    )
    exercises = sorted((ROOT / "exercises/exercises-ai").glob("E[0-9]*.md"), key=natural_key)
    if not slides or not exercises:
        parser.error("Expected slides/*.md and exercise collections in exercises/exercises-ai/E*.md")

    missing = dependency_error(slides + exercises)
    if missing:
        parser.error(missing)
    if not TEMPLATE.is_file():
        parser.error(f"Missing bundled Eisvogel template: {TEMPLATE}. Restore books/scripts/templates/eisvogel/.")
    missing = latex_dependency_error()
    if missing:
        parser.error(missing)

    build_book(slides, notes_output, "Java Core", "Course notes")
    build_book(exercises, exercises_output, "Java Core Exercises", "Exercises and solutions", exercise_book=True)


if __name__ == "__main__":
    try:
        main()
    except (OSError, ValueError, subprocess.CalledProcessError) as error:
        print(f"Book generation failed: {error}", file=sys.stderr)
        sys.exit(1)
