# Eisvogel

The unmodified standalone template and license are bundled from **Eisvogel 3.5.1**:

https://github.com/Wandmalfarbe/pandoc-latex-template/releases/tag/v3.5.1

License: BSD 3-Clause; see `LICENSE` and the copyright notice in `eisvogel.latex`.

`../../build_book.py` selects this template explicitly, so no user-level Pandoc
template installation or build-time download is needed. It uses XeLaTeX, a wide
single-column book layout, and DejaVu fonts instead of Eisvogel's optional Source
Sans/Source Code Pro font packages. Course sources remain unchanged.

Run `python3 scripts/build_book.py` to generate `books/Java Core.pdf`. The script
checks CLI tools and required LaTeX components before rendering. Remote images
are rejected, and failed builds leave the previous PDF untouched.
