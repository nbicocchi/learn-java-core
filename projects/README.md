# Projects

[← Java Core](../README.md)

Example applications and requirements for the project submitted before the exam.

## Examples

| Project | Purpose |
|---|---|
| [deploy-cli](deploy-cli/) | Command-line application packaging example. |
| [deploy-gui](deploy-gui/) | JavaFX application deployment example. |
| [Purble Burger Fastfood](purble-burger-fastfood/README.md) | JavaFX cooking game, with startup instructions, gameplay rules, and tests. |

## Exam submission

Publish your project in a **public GitHub repository** and send its URL to the instructor **before the exam** for review.

**Submitting the repository URL in advance is a requirement for admission to the exam.** Use the [project submission form](https://docs.google.com/forms/d/e/1FAIpQLSeNDcHf6t4hLVJFgaQgIYuyHf9RBsqFUl9wXsMIVWy4_-a4QA/viewform?usp=dialog).

## Required contents

- A `README.md` describing the project, prerequisites, and **exact startup instructions**. Provide either a launcher or an explicitly documented Maven command, including any required build or setup steps.
- A `LICENSE` file specifying the project's license.
- Documentation explaining how to use the application and its main features.
- Cross-platform support: **the instructor will run the project on Linux**. Avoid machine-specific absolute paths and hard-coded path separators; respect filename case and include all required resources.

## Assessment

The project is assessed as a working application, based on:

| Criterion | Expectations |
|---|---|
| **Reproducible startup** | The application starts on Linux using the launcher or Maven command documented in the README. |
| **Working functionality** | The main features work without crashes or blocking issues during normal use. |
| **Presentation and usability** | The interface is visually coherent and easy to understand, with clear instructions, messages, and interaction flow. A text-based interface is also acceptable if well presented and usable. |

The assessment focuses on the application **as seen and used**, not on a source-code review. Individual technical knowledge is assessed through the written exam.
