# Kisaragi-core
REST API for the main components on Kisaragi Project
## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally
First, you need to edit the datasource's properties in the `application.properties` file 

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.kisaragi.app.KisaragiCoreApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
