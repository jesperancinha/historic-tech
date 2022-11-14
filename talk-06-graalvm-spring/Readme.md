# GraalVM and Spring

## Quick Intro

Using GraalVM can be an amazing thing. We can benefit from the native code compilation and throw away initial startup
processes that usually take some time and delay not only the startup but also the initial calls to certain areas of the
compiled bundle. This is normally what we get from the Java runtime and it is made so in order to let JIT optimize the
machine code and make the application run with maximized performance. But how can we skip all of the JIT processing and
make our application still perform fast? Can GraalVM support that? Can GraalVM guarantee that applications will run
faster? What are its pitfalls? What is AOT? In this session, I want to make an introduction to GraalVM, and show an
application running in GraalVM and another running in a traditional way. Then I want to show comparisons between startup
times, runtime performance, and a range of other metrics to illustrate the differences.
