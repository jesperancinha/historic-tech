# CRaC Service Spring Boot Example


## Installation notes

```shell
sudo chown root:root $JAVA_HOME/lib/criu
sudo chmod u+s $JAVA_HOME/lib/criu
```
## Recommentations

#### 1. Do not run via the Makefile

For some reason, this does not seem to work if we run the executable via the Makefile on IntelliJ.
It might be related to how service discovery works.
Running the Makefile in the IDE creates some pointers that may confuse the whole process.

## Resources

-   [SpringBoot 3.2 + CRaC](https://foojay.io/today/springboot-3-2-crac/)
-   [CRIU on Wikipedia](https://en.wikipedia.org/wiki/CRIU)
