# Getting started with Testcontainers in a Java Spring Boot Project

This project is based on sample code from [Getting started with Testcontainers in a Java SpringBoot Project](https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers).

## 1. Setup Environment
Make sure you have Java 8+ and a [compatible Docker environment](https://www.testcontainers.org/supported_docker_environment/) installed.
If you are going to use Maven build tool then make sure Java 17+ is installed.

For example:

```shell
$ java -version
openjdk version "17.0.4" 2022-07-19
OpenJDK Runtime Environment Temurin-17.0.4+8 (build 17.0.4+8)
OpenJDK 64-Bit Server VM Temurin-17.0.4+8 (build 17.0.4+8, mixed mode, sharing)
$ docker version
...
Server: Docker Desktop 4.12.0 (85629)
 Engine:
  Version:          20.10.17
  API version:      1.41 (minimum version 1.12)
  Go version:       go1.17.11
...
```
## 2. Run Tests

Run the command to run the tests.

```shell
$ mvn clean verify
```

The tests should pass.