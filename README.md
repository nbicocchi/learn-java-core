# Java Core

### Software

* [JDK Development Kit 21](https://www.oracle.com/it/java/technologies/downloads/)
* [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/) [Plugins: [Mermaid](https://plugins.jetbrains.com/plugin/20146-mermaid)]
* [Scene Builder](https://gluonhq.com/products/scene-builder/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/)
* [Mermaid](https://mermaid.js.org/)

### Books
* **Big Java - Early Objects 7th Edition; Horstmann; Wiley Press (intermediate)**
* Effective Java; Bloch; O'Reilly (advanced)

### Videos
* [YouTube OOP Java](https://www.youtube.com/watch?v=y3H3xwI0prM&list=PLhlcRDRHVUzTruZmXalUSJAK26pouP8ST)
* [YouTube IntelliJ](https://www.youtube.com/c/intellijidea/videos)

### Official Tutorials
* [IntelliJ Tutorial](https://www.jetbrains.com/idea/resources/)
* [Java SE Tutorial](https://dev.java/)
* [Java SE API](https://docs.oracle.com/en/java/javase/21/docs/api/index.html)

### Other Tutorials/Exercises
* https://java-programming.mooc.fi/
* https://www.baeldung.com/
* https://jenkov.com/
* https://www.w3resource.com/
* https://codingbat.com/
* https://www.programiz.com/
* https://boldcoder.blogspot.com/
* https://exercism.io/
* https://github.com/TheAlgorithms/Java/
* https://github.com/charlax/professional-programming

### Contributing
Your pull requests are very welcome! To contribute, please refer to [this](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request) guide. For a more general introduction to GitHub, refer to [this](https://github.com/skills/) page. By contributing to this repository, you agree that your contributions will be licensed under the LICENSE file in the root directory of this source tree.

### Modules
[E1] From Functions to Classes

* Evolution of programming languages
* Limits of procedural programming
* Modularization, reuse of code, and object-oriented programming
* Object-oriented programming key features

[E2] Basics

> Read: Big Java (Chapter 2-4-5-6-7)

> Lab:
> [com.nbicocchi.exercises.warmup](code/exercises/src/main/java/com/nbicocchi/exercises/warmup),
> [com.nbicocchi.exercises.arrays](code/exercises/src/main/java/com/nbicocchi/exercises/arrays),
> [com.nbicocchi.exercises.strings](code/exercises/src/main/java/com/nbicocchi/exercises/strings) 

* Variables, methods and parameter passing
* Fundamental data types
* Decisions
* Loops
* Arrays (and brief introduction to ArrayList)
* Strings
* Input and output
* Random numbers
* [Coding Standard](books/Java-Coding-Standard.pdf)

[E3] Object Oriented Design

> Read: Big Java (Chapter 3-8-9-10-12)
> Read: UML Distilled (Chapter 3)

> Lab: [com.nbicocchi.exercises.oop](code/exercises/src/main/java/com/nbicocchi/exercises/oop)

* Instance variables and encapsulation
* Specifying the public interface of a class (and its implementation)
* Static variables and methods
* Inheritance hierarchies
* Abstract classes
* Interfaces (and anonymous implementations)
* Object: the cosmic superclass
* Wrapper types
* UML class diagrams 

[E4] Data Structures (Collections Framework)

> Read: Big Java (Chapter 14-15)

> Lab: package [com.nbicocchi.exercises.collections](code/exercises/src/main/java/com/nbicocchi/exercises/collections)

* Iterable and Iterator interfaces
* List interface and its implementations (ArrayList, LinkedList)
* Set interface and its implementations (HashSet, LinkedHashSet, TreeSet)
* Deque interface and its implementations (ArrayDeque, LinkedList)
* Map interface and its implementations (HashMap, LinkedHashMap, TreeMap)
* Sorting and searching

[E5] Generic Data Structures (Generics)

> Read: Big Java (Chapter 18)

> Lab: [com.nbicocchi.exercises.generics](code/exercises/src/main/java/com/nbicocchi/exercises/generics)

* Generic classes and type parameters
* Implementing generic types
* Generic methods
* Constraining type parameters
* Type erasure

[E6] Functional Programming

> Read: Big Java (Chapter 19), 
> Object-Oriented vs Functional Programming (Chapter 1)

> Lab: [com.nbicocchi.exercises.functional](code/exercises/src/main/java/com/nbicocchi/exercises/functional)

* Lambda expressions
* Functional interfaces
* Producing, transforming, and collecting functional streams
* The *Optional* type

[E7] Exceptions

> Read: Big Java (Chapter 11)

> Lab: [com.nbicocchi.exercises.exceptions](code/exercises/src/main/java/com/nbicocchi/exercises/exceptions)

* Throwing exceptions
* Catching exceptions
* Checked and unchecked exceptions
* Closing resources

[E8] IO/NIO Frameworks

> Read: Big Java (Chapter 21)

> Lab: [com.nbicocchi.exercises.nio](code/exercises/src/main/java/com/nbicocchi/exercises/nio)

* IO vs NIO frameworks
* Manipulating paths
* Manipulating filesystem (copy, move, etc.)
* Reading and writing files

[E9] Multi-threading

> Read: Big Java (Chapter 22)

> Lab: [com.nbicocchi.exercises.threads](code/exercises/src/main/java/com/nbicocchi/exercises/threads)

* Thread states
* Thread interference
* Thread synchronisation (synchronised/wait/notify)
* ExecutorService
* Callable/Future/Task

*"Any fool can write code that a computer can understand. Good programmers write code that humans can understand." --- Martin Fowler*
