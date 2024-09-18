<h1 align="center">
Spring Boot Maven Archetype
</h1>

<h4 align="center">
v0.0.1-SNAPSHOT
</h4>

## 📰 Description
This spring boot maven archetype provides a structured template for quickly setting up a Spring Boot 
project with best practices and predefined configurations. It is designed to help developers 
streamline the initialization of projects by generating boilerplate code, folder structure, 
and essential dependencies. The archetype reduces setup time and ensures consistency across 
projects, making it an ideal starting point for new development efforts.

##  📔 Table of Contents
<!--ts-->
   * [Pre-requirements](#✂️-pre-requirements)
   * [How to use](#🎮-how-to-use)
      * [Setup](#setup)
      * [Run](#run)
   * [Tools](#🔨-tools)
<!--te-->

## ✂️ Pre-requirements
* [JDK 22](https://www.oracle.com/br/java/technologies/downloads/)

## 🎮 How to use

###  Setup

Clone repository
```
git clone git@github.com:victorsantosbrazil/spring-boot-project-archetype.git
```

Enter the project folder
```
cd spring-boot-project-archetype
```

Install
```
./mvnw clean install
```

### Run
```
./mvnw spring-boot:run
```

## 🔨 Tools

The following tools were used to build this project:

* [Spring 3+](https://spring.io/) 
* [Maven](https://maven.apache.org/) 
* [Spotless](https://github.com/diffplug/spotless/tree/main/plugin-maven) 
* [JaCoCo](https://github.com/jacoco/jacoco)
* [OWASP Dependency Check](https://jeremylong.github.io/DependencyCheck/dependency-check-maven/)
* [PostgreSQL](https://www.postgresql.org/) 
* [Flyway](https://www.red-gate.com/products/flyway/community/) 
* [Docker](https://www.docker.com/)