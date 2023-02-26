# Object Oriented Programming (DIEF, UNIMORE)

### Software

* [Java SE Development Kit](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [Scene Builder](https://gluonhq.com/products/scene-builder/)
* [Mermaid](https://mermaid.js.org/)

### Books
* Big Java - Early Objects; Horstmann; Wiley Press (intermediate) [Book resources](https://bcs.wiley.com/he-bcs/Books?action=resource&bcsId=11274&itemId=1119499097&resourceId=44689)
* Effective Java; Bloch; O'Reilly (advanced)

### Videos
* [YouTube OOP Java](https://www.youtube.com/watch?v=y3H3xwI0prM&list=PLhlcRDRHVUzTruZmXalUSJAK26pouP8ST)
* [YouTube IntelliJ](https://www.youtube.com/c/intellijidea/videos)

### Tutorials
* [IntelliJ Tutorial](https://www.jetbrains.com/idea/resources/)
* [Java SE Tutorial](https://docs.oracle.com/javase/tutorial/)
* [Java SE API](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)
* [Baeldung](https://www.baeldung.com/)

### Other Exercises
* https://codingbat.com/java/
* https://www.w3resource.com/
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
* Respect Java Coding Standard (see assets/books/)
* 1000+ lines of logic code, excluding GUI/XML files
* Refer to [this project](https://github.com/logesh0304/RegexAssist) as a well-made example

---

### Exercises Modules
[E1] From Functions to Objects

* Evolution of programming languages
* Limits of procedural programming
* Modularization, reuse of code, and object-oriented programming
* Object-oriented programming key features

[E2] Basics

> Read: Big Java (Chapter 4-5-6), Java Coding Standard

> Lab: package com.nbicocchi.exercises.arrays 

> Lab: package com.nbicocchi.exercises.strings

* Types, variables, constants
* Methods and parameter passing
* Flow control
* Loops
* Strings
* Arrays 
* Terminal I/O
* Coding Standard

[E3] Object Oriented Programming

> Read: Big Java (Chapter 2-3-8-9-10)

> Lab: package com.nbicocchi.exercises.oop

* Classes, objects, packages
* Attributes, methods, visibility
* Constructors, getters/setters, toString()
* Inheritance, upcasting, downcasting
* Polymorphism, method overloading/overriding 
* Abstract methods, abstract classes, interfaces
* Wrapper types
* UML class diagrams

[E4] Data Structures (Collections Framework)

> Read: Big Java (Chapter 15)

> Lab: package com.nbicocchi.exercises.collections

* Collection (Set, Queue, List) interface and its implementations
* Map interface and its implementations
* Iterator and ListIterator (definition and use)
* Sorting and searching

[E5] Generic Data Structures (Generics)

> Read: Big Java (Chapter 18)

> Lab: package com.nbicocchi.exercises.generics

* Array sub-typing
* Object sub-typing
* Collection sub-typing
* Bounded wildcards types

[E6] Functional Interfaces

> Read: Big Java (Chapter 19), 
> Object Oriented vs Functional Programming (Chapter 1)

> Lab: package com.nbicocchi.exercises.functional

* Anonymous classes
* Lambda expressions
* Functional interfaces
* Streams

[E7] Exceptions

> Read: Big Java (Chapter 11)

> Lab: package com.nbicocchi.exercises.exceptions

* Exceptions (checked and unchecked) and Errors
* Catching Exceptions (try/catch/finally)
* Throwing Exceptions (throw)
* Delegation models (complete, partial, no delegation) (throws)

[E8] NIO Framework

> Read: Big Java (Chapter 21)

> Lab: package com.nbicocchi.exercises.nio

* Path
* Files
* Buffer
* FileChannel

---

### Project Modules
[P1] JavaFX Framework

> Read: Introduction to Programming Using Java (Chapter 6-13),
> Watch: [JavaFX GUI Full Course](https://www.youtube.com/watch?v=9XJicRt_FaI)

> Code: [https://github.com/nbicocchi/java-javafx](https://github.com/nbicocchi/java-javafx)

* Structure of JavaFX (key interfaces)
* Layout management
* Event delegation model
* Scene Builder

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

> Code: [https://github.com/nbicocchi/java-rest](https://github.com/nbicocchi/java-rest)

* Motivations and basic concepts
* Resources, Operations, Representations (JSON, XML)
* GET/POST/PUT/DELETE requests
* Design of a client/server staleless application

---

### FAQ
Developing a project at home is the goal of the course?
> **No! The goal is not developing a home project! The goal of the course is learning how to code in Java and Python**. Developing a project is only a (hopefully fun) way to do com.

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