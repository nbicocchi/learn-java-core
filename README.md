# Java Core

### Software

* [JDK Development Kit 25](https://www.oracle.com/it/java/technologies/downloads/#java25)
* [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/) [Plugins: [Mermaid](https://plugins.jetbrains.com/plugin/20146-mermaid)]
* [Scene Builder](https://gluonhq.com/products/scene-builder/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/)

### Books
* [Java Core — Course notes and exercises](<books/Java Core.pdf>)
* Big Java - Early Objects 7th Edition; Horstmann; Wiley Press

### Documentation
* [IntelliJ Tutorial](https://www.jetbrains.com/idea/resources/)
* [Java Tutorial](https://dev.java/)
* [Java API](https://docs.oracle.com/en/java/javase/25/docs/api/index.html)

### Exercises

The [exercises-ai](code/exercises-ai) directory contains exam-style exercises validated by the instructor.

For additional practice, use [PROMPT.md](exercises-new/PROMPT.md) with an AI assistant to generate more exercises on the course topics. The exam papers will be generated using the same method and validated by the instructor before use. Additional exercises generated independently for practice are not automatically instructor-validated.

### Project

Publish your project in a **public GitHub repository** and send its URL to the instructor **before the exam** for review. **Submitting the repository URL in advance is a requirement for admission to the exam.** Submit the repository URL through the [project submission form](https://docs.google.com/forms/d/e/1FAIpQLSeNDcHf6t4hLVJFgaQgIYuyHf9RBsqFUl9wXsMIVWy4_-a4QA/viewform?usp=dialog).

The project must include:

* A `README.md` describing the project, prerequisites, and exact Maven commands to build, test, and launch it.
* A `LICENSE` file specifying the project's license.
* Documentation explaining usage and the main design decisions.
* Cross-platform support: **the instructor will run the project on Linux**. Avoid machine-specific absolute paths and hard-coded path separators; respect filename case and include all required resources.

### Contributing
Your pull requests are very welcome! To contribute, please refer to [this](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request) guide. For a more general introduction to GitHub, refer to [this](https://github.com/skills/) page. By contributing to this repository, you agree that your contributions will be licensed under the LICENSE file in the root directory of this source tree.

### Modules

#### A0 — Computing in the Age of AI

* [A0 — Computing in the Age of AI](<slides/A0 — Computing in the Age of AI.md>)

#### E1 — From Functions to Classes

* [E1.1 — From functions to classes](<slides/E1.1 — From Functions to Classes.md>)
* [E1.2 — Environment and build tools](<slides/E1.2 — Environment and Build Tools.md>)

#### E2 — Basics

* [E2.1 — Types and expressions](<slides/E2.1 — Types and Expressions.md>)
* [E2.2 — Control flow](<slides/E2.2 — Control Flow.md>)
* [E2.3 — Methods and references](<slides/E2.3 — Methods and References.md>)
* [E2.4 — Arrays](<slides/E2.4 — Arrays.md>)
* [E2.5 — Strings](<slides/E2.5 — Strings.md>)
* [E2.6 — Wrapper classes](<slides/E2.6 — Wrapper Classes.md>)
* [E2.7 — Text input and output](<slides/E2.7 — Text Input and Output.md>)
* [E2.8 — Unit testing with JUnit](<slides/E2.8 — Unit Testing with JUnit.md>)

> **Practice:** [E2 — Java Basics](<exercises-new/E2 - Java Basics.md>)
>
> **Read:** Big Java (Chapters 2, 4, 5, 6, 7)

#### E3 — Object-Oriented Programming

* [E3.1 — Classes and constructors](<slides/E3.1 — Classes and Constructors.md>)
* [E3.2 — Encapsulation and invariants](<slides/E3.2 — Encapsulation and Invariants.md>)
* [E3.3 — Inheritance and polymorphism](<slides/E3.3 — Inheritance and Polymorphism.md>)
* [E3.4 — Interfaces](<slides/E3.4 — Interfaces.md>)
* [E3.5 — Abstract classes and composition](<slides/E3.5 — Abstract Classes and Composition.md>)
* [E3.6 — Object equality and hashing](<slides/E3.6 — Object Equality and Hashing.md>)
* [E3.7 — Immutability and records](<slides/E3.7 — Immutability and Records.md>)
* [E3.8 — Enums and state modelling](<slides/E3.8 — Enums and State Modelling.md>)
* [E3.9 — Nested classes](<slides/E3.9 — Nested Classes.md>)
* [E3.10 — SOLID principles](<slides/E3.10 — SOLID Principles.md>)

> **Practice:** [E3 — Object-Oriented Programming](<exercises-new/E3 - Java Object-Oriented Programming.md>)
>
> **Read:** Big Java (Chapters 3, 8, 9, 10, 12); UML Distilled (Chapter 3)

#### E4 — Collections

* [E4.1 — Collections, lists and iterators](<slides/E4.1 — Collections Lists and Iterators.md>)
* [E4.2 — Sets](<slides/E4.2 — Sets.md>)
* [E4.3 — Queues and deques](<slides/E4.3 — Queues and Deques.md>)
* [E4.4 — Maps](<slides/E4.4 — Maps.md>)
* [E4.5 — Collection ordering and search](<slides/E4.5 — Collection Ordering and Search.md>)

> **Practice:** [E4 — Collections](<exercises-new/E4 - Java Collections.md>)
>
> **Read:** Big Java (Chapters 14, 15)

#### E5 — Exceptions and Resources

* [E5.1 — Exceptions and propagation](<slides/E5.1 — Exceptions and Propagation.md>)
* [E5.2 — Error handling and contracts](<slides/E5.2 — Error Handling and Contracts.md>)
* [E5.3 — Resource ownership](<slides/E5.3 — Resource Ownership.md>)

> **Practice:** [E5 — Exceptions](<exercises-new/E5 - Java Exceptions.md>)
>
> **Read:** Big Java (Chapter 11)

#### E6 — Generics

* [E6.1 — Generic classes and methods](<slides/E6.1 — Generic Classes and Methods.md>)
* [E6.2 — Bounds and wildcards](<slides/E6.2 — Bounds and Wildcards.md>)
* [E6.3 — Type erasure](<slides/E6.3 — Type Erasure.md>)

> **Practice:** [E6 — Generics](<exercises-new/E6 - Java Generics.md>)
>
> **Read:** Big Java (Chapter 18)

#### E7 — Functional Programming

* [E7.1 — Lambdas and functional interfaces](<slides/E7.1 — Lambdas and Functional Interfaces.md>)
* [E7.2 — Function composition and Optional](<slides/E7.2 — Function Composition and Optional.md>)
* [E7.3 — Stream pipelines](<slides/E7.3 — Stream Pipelines.md>)
* [E7.4 — Stream collection and reduction](<slides/E7.4 — Stream Collection and Reduction.md>)

> **Practice:** [E7 — Functional Programming](<exercises-new/E7 - Java Functional Programming.md>)
>
> **Read:** Big Java (Chapter 19); Object-Oriented vs Functional Programming (Chapter 1)

#### E8 — Concurrency

* [E8.1 — Threads and execution](<slides/E8.1 — Threads and Execution.md>)
* [E8.2 — Shared state and synchronization](<slides/E8.2 — Shared State and Synchronization.md>)
* [E8.3 — Thread coordination](<slides/E8.3 — Thread Coordination.md>)
* [E8.4 — Executors, tasks and futures](<slides/E8.4 — Executors Tasks and Futures.md>)

> **Practice:** [E8 — Concurrency](<exercises-new/E8 - Java Concurrency.md>)
>
> **Read:** Big Java (Chapter 22)
