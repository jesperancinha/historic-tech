# Closed World Example

## Introduction

This exercise is about understanding reflection in GraalVM native binaries.

## Installation

Example:

```shell
cd ~/.sdkman/candidates/java/22.2.r17-grl/
export LD_LIBRARY_PATH="$(pwd)/lib:$LD_LIBRARY_PATH"
```

Kotlin

```shell
sdk install kotlin
```

## Technical Docs

- https://github.com/oracle/graal/issues/5375
- https://sdk-forum.dji.net/hc/en-us/articles/11916949986585-Why-a-crash-ClassNotFoundException-Didn-t-find-class-kotlin-jvm-internal-Intrinsics-has-happened-
- https://javarevisited.blogspot.com/2012/10/5-ways-to-add-multiple-jar-to-classpath-java.html#axzz8JRqqAzrt

## References

- https://sdkman.io/
- https://docs.oracle.com/en/learn/understanding-reflection-graalvm-native-image/index.html#step-3--an-example-using-reflection
- https://en.wikipedia.org/wiki/Closed-world_assumption
- https://kotlinlang.org/docs/native-debugging.html
- https://www.graalvm.org/22.0/reference-manual/native-image/Reflection/