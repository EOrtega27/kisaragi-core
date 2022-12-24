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
# Running the project with MySQL

This project uses an PostgreSQL database. However, converting it to run 
with another relational database such as MySQL. 
Since the project uses Spring Data and the Repository pattern, it's even 
fairly easy to back the same service with MongoDB.

Here is what you would do to back the services with MySQL, for example:

### In pom.xml add:

```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```
### Edit the application.properties
```
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLInnoDBDialect
```
# SwaggerUI
Run the server and browse to `{route}/swagger-ui/index.html`