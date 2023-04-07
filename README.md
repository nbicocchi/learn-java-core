# Object Oriented Programming (DIEF, UNIMORE)

### Software

* [Java SE Development Kit](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [Scene Builder](https://gluonhq.com/products/scene-builder/)
* [Mermaid](https://mermaid.js.org/)

### Books
* Big Java - Early Objects 7th Edition; Horstmann; Wiley Press (intermediate) [Book resources](https://bcs.wiley.com/he-bcs/Books?action=resource&bcsId=11274&itemId=1119499097&resourceId=44689)
* Effective Java; Bloch; O'Reilly (advanced)

### Videos
* [YouTube OOP Java](https://www.youtube.com/watch?v=y3H3xwI0prM&list=PLhlcRDRHVUzTruZmXalUSJAK26pouP8ST)
* [YouTube IntelliJ](https://www.youtube.com/c/intellijidea/videos)

### Official Tutorials
* [IntelliJ Tutorial](https://www.jetbrains.com/idea/resources/)
* [Java SE Tutorial](https://docs.oracle.com/javase/tutorial/)
* [Java SE API](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)

### Other Tutorials/Exercises
* https://jenkov.com/
* https://www.baeldung.com/
* https://www.w3resource.com/
* https://codingbat.com/
* https://www.programiz.com/
* https://exercism.io/

### GitHub
* https://github.com/TheAlgorithms/Java/

---

### Exam
* solve exercises (including unit tests) from com.nbicocchi.exercises (1 or 2) **(60% weight)**
* answer general questions about the key topics of the course (1 or 2) **(20% weight)**
* discuss the structure and engineering choices of a home project **(20% weight)**

---

### Project
* Stored on a GitHub repository (complete with README.md and LICENCE files)
* 500+ lines of logic code per person, 1000+ per couple, excluding GUI/XML files
* Respect [Java Coding Standard](https://github.com/nbicocchi/java-core/blob/main/assets/books/Java-Coding-Standard.pdf)
* Refer to [this project](https://github.com/logesh0304/RegexAssist) as a well-made example

---

### Introduction
[E1] From Functions to Objects

* Evolution of programming languages
* Limits of procedural programming
* Modularization, reuse of code, and object-oriented programming
* Object-oriented programming key features

### Exercises Modules
[E2] Basics

> Read: Big Java (Chapter 2-4-5-6-7)

> Lab: 
> [com.nbicocchi.exercises.warmup](https://github.com/nbicocchi/java-core/tree/main/src/main/java/com/nbicocchi/exercises/warmup),
> [com.nbicocchi.exercises.arrays](https://github.com/nbicocchi/java-core/tree/main/src/main/java/com/nbicocchi/exercises/arrays),
> [com.nbicocchi.exercises.strings](https://github.com/nbicocchi/java-core/tree/main/src/main/java/com/nbicocchi/exercises/strings) 

* Variables, methods and parameter passing
* Fundamental data types
* Decisions
* Loops
* Arrays (and brief introduction to ArrayList)
* Strings
* Input and output
* Random numbers
* [Coding Standard](https://github.com/nbicocchi/java-core/blob/main/assets/books/Java-Coding-Standard.pdf)

[E3] Object Oriented Design

> Read: Big Java (Chapter 3-8-9-10-12)
> Read: UML Distilled (Chapter 3)

> Lab: [com.nbicocchi.exercises.oop](https://github.com/nbicocchi/java-core/tree/main/src/main/java/com/nbicocchi/exercises/oop)

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

> Lab: package [com.nbicocchi.exercises.collections](https://github.com/nbicocchi/java-core/tree/main/src/main/java/com/nbicocchi/exercises/collections)

* Iterable and Iterator interfaces
* List interface and its implementations (ArrayList, LinkedList)
* Set interface and its implementations (HashSet, LinkedHashSet, TreeSet)
* Deque interface and its implementations (ArrayDeque, LinkedList)
* Map interface and its implementations (HashMap, LinkedHashMap, TreeMap)
* Sorting and searching

[E5] Generic Data Structures (Generics)

> Read: Big Java (Chapter 18)

> Lab: [com.nbicocchi.exercises.generics](https://github.com/nbicocchi/java-core/tree/main/src/main/java/com/nbicocchi/exercises/generics)

* Generic classes and type parameters
* Implementing generic types
* Generic methods
* Constraining type parameters
* Type erasure

[E6] Functional Programming

> Read: Big Java (Chapter 19), 
> Object Oriented vs Functional Programming (Chapter 1)

> Lab: [com.nbicocchi.exercises.functional](https://github.com/nbicocchi/java-core/tree/main/src/main/java/com/nbicocchi/exercises/functional)

* Lambda expressions
* Functional interfaces
* The Stream concept
* Producing, transforming, and collecting streams

[E7] Exceptions

> Read: Big Java (Chapter 11)

> Lab: [com.nbicocchi.exercises.exceptions](https://github.com/nbicocchi/java-core/tree/main/src/main/java/com/nbicocchi/exercises/exceptions)

* Throwing exceptions
* Catching exceptions
* Checked and unchecked exceptions
* Closing resources

[E8] IO/NIO Frameworks

> Read: Big Java (Chapter 21)

> Lab: [com.nbicocchi.exercises.nio](https://github.com/nbicocchi/java-core/tree/main/src/main/java/com/nbicocchi/exercises/nio)

* IO vs NIO frameworks
* Manipulating paths
* Manipulating filesystem (copy, move, etc)
* Reading and writing files

---

### Project Modules

> Code: [https://github.com/nbicocchi/java-javafx](https://github.com/nbicocchi/java-javafx)

[P1] JavaFX Framework

> Read: Introduction to Programming Using Java (Chapter 6-13),
> Watch: [JavaFX GUI Full Course](https://www.youtube.com/watch?v=9XJicRt_FaI)

* Structure of a JavaFX application 
* Introduction to Scene Builder
* Events
* Controls
* Layouts

[P2] Multi-threading

> Read: Big Java (Chapter 22)

* Thread states
* Race conditions
* Basic synchronisation (synchronised/sleep/yield/join)
* Advanced synchronisation (wait/notify)

[P3] Data Access (JDBC)

> Read: Big Java (Chapter 24)

* Database connection architecture
* Connection and Statement classes
* Connection Pools
* ResultSet class

[P4] Data Access (REST)

> Read: [Connecting JavaFX to a REST API](https://edencoding.com/connect-javafx-to-a-rest-api/)

* Motivations and basic concepts
* Resources, Operations, Representations (JSON, XML)
* GET/POST/PUT/DELETE requests
* Design of a client/server staleless application

---

### FAQ
Developing a project at home is the goal of the course?
> **No! The goal is not developing a home project! The goal of the course is learning how to code in Java and Python**. Developing a project is only a (hopefully fun) way to learn the language.

Can we develop the project in groups of 3 people?
> No, you can develop a project alone or with 1 colleague. Groups should be composed of teammates with comparable coding skills. Exceptions to this rule are negatively evaluated.

Can we use JavaScript, TypeScript, Kotlin, Dart, Swift, C++, C#?
> No! Only Java and Python are allowed. If you learn properly Java and Python, you will be able to quickly learn any modern object-oriented language. 

Which kind of project can we do?
> You can code a video game, a managing software, an Android app.

Can we use external libraries?
> Sure! The object-oriented paradigm has been initially proposed for promoting the reuse of code and building larger projects! For example, for developing games [LibGDX](https://libgdx.com/), [Slick2D](https://slick.ninjacave.com/), or [Tiled](https://www.mapeditor.org/) are useful. 

Which IDE should I use for developing an Android app?
> Android Studio is a version of IntelliJ specifically tuned for Android development.

What is the best moment for starting the project?
> The first half of the course concerns basic topics that must be assimilated before starting. My personal suggestion is to start the project around midterm and use the second part of the course to sketch out the main design.

While developing I get strange errors. What should I do?
> The first thing to do is copy and paste the error string on Google and check for eventual answers on StackOverflow. The vast majority of common errors has a dedicated page.