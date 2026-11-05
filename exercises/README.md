# Exercises

[← Java Core](../README.md)

The two exercise collections develop complementary skills.

## Choose a collection

| Collection | Focus | How to work |
|---|---|---|
| [exercises-no-ai](exercises-no-ai/) | Traditional coding exercises | Write Java code yourself, without AI-generated solutions, and check it with the supplied automated tests. |
| [exercises-ai](exercises-ai/) | Code analysis and critical reasoning | Explain AI-generated code, reason about correctness, identify defects, and justify corrections or design choices. |

## Traditional coding exercises

The [exercise sources](exercises-no-ai/src/main/java/com/nbicocchi/exercises/) contain topic-specific instructions; the [tests](exercises-no-ai/src/test/) check your implementations.

With JDK 25 and Maven installed, run the tests from the repository root:

```bash
mvn -f exercises/exercises-no-ai/pom.xml test
```

## Code-analysis exercises

These exam-style exercises focus on understanding and critically evaluating code rather than writing it from scratch. The supplied exercises are validated by the instructor.

| Topic | Exercises |
|---|---|
| E2 | [Java Basics](<exercises-ai/E2 - Java Basics.md>) |
| E3 | [Object-Oriented Programming](<exercises-ai/E3 - Java Object-Oriented Programming.md>) |
| E4 | [Collections](<exercises-ai/E4 - Java Collections.md>) |
| E5 | [Exceptions](<exercises-ai/E5 - Java Exceptions.md>) |
| E6 | [Generics](<exercises-ai/E6 - Java Generics.md>) |
| E7 | [Functional Programming](<exercises-ai/E7 - Java Functional Programming.md>) |
| E8 | [Concurrency](<exercises-ai/E8 - Java Concurrency.md>) |

### Generate more practice

Use [PROMPT.md](exercises-ai/PROMPT.md) with an AI assistant to generate additional code-analysis exercises on the course topics.

The exam papers will be generated using the same method and validated by the instructor before use. Exercises generated independently for practice are **not automatically instructor-validated**.

## Printable material

The [Java Core Exercises book](<../books/Java Core Exercises.pdf>) collects the code-analysis exercises and solutions. See the [main README](../README.md#books) for PDF generation instructions.
