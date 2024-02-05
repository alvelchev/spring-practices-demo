### Please ⭐ this repository if you find it useful. Thank you.

# Spring Practices Demo

![Spring](https://github.com/alvelchev/spring-practices-demo/actions/workflows/build.yml/badge.svg)
[![Docker Image CI](https://github.com/alvelchev/spring-practices-demo/actions/workflows/docker-image.yml/badge.svg?branch=main)](https://github.com/alvelchev/spring-practices-demo/actions/workflows/docker-image.yml)
[![codecov](https://codecov.io/gh/alvelchev/spring-practices-demo/branch/main/graph/badge.svg)](https://codecov.io/gh/alvelchev/spring-practices-demo)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
</br>

Integration with
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

## Table of Contents

- [Introduction](#introduction)
- [Key Features](#key-features-and-demonstrations)
- [GitHub Actions Pipelines Integration](#github-actions-pipelines-integration)
- [How to Start the Project](#how-to-start-the-project)
- [Provided Examples with Implementation on Spring Boot](#provided-examples-with-implementation-on-spring-boot)
- [Branches](#branches)
- [Git Commit Plugin Response](#git-commit-plugin-response)

## Introduction

Welcome to the Spring Boot Best Practices Application! This project serves as a comprehensive demonstration of
fundamental Java Spring concepts, highlighting industry best practices and essential techniques for building robust
Spring Boot applications. The project is designed to be a reference for developers of all levels, providing examples of
a wide range of Spring Boot features and implementations.

## Key Features and Demonstrations

- **Model-View-Controller (MVC) Architecture:** Embracing the MVC pattern for a well-organized and modular structure.
- **MapStruct for Object Mapping:** Streamlining object mapping for enhanced readability and maintainability.
- **Flyway for Database Migration:** Seamless database migration management through Flyway.
- **JUnit 5 Configuration:** Configured JUnit 5 for effective testing and robust code coverage.
- **JSON Data Processing:** Efficiently reading and filtering data from JSON files.
- **Basic CRUD Operations:** Implementation of essential CRUD operations for effective resource management.
- **Pageable Response Handling:** Demonstrating easy filtering, sorting, and more with Pageable responses.
- **Global Exception Handling:** A global exception handler for managing exceptions across the codebase.
- **Custom Spring Validator:** Examples of custom Spring validators at field and class levels.
- **CriteriaBuilder for Data Retrieval:** Using CriteriaBuilder for advanced data retrieval.
- **Spring Boot Actuator:** Configuring Spring Boot Actuator for collecting application metrics.
- **Git Information Injection:** Injecting Git information into Spring for version tracking.
- **Quartz Scheduler:** Implementation of Quartz scheduler using Cron Triggers and Expressions.
- **Spring Custom Events:** Leveraging Spring Custom Events for event-driven architecture.

## GitHub Actions Pipelines Integration

- Integrated with GitHub Actions for code analyses with SonarCloud and Codecov for code coverage reports.
- Integrated with Docker. On every push event to the main branch, a new Docker image is generated.
- Integrated with Maven Dependency tree generation.

### SonarCloud:

- [SonarCloud](https://www.sonarsource.com/products/sonarcloud/)
- [SonarCloud Project Overview](https://sonarcloud.io/project/overview?id=alvelchev_spring-pageable-response-demo)

### Codecov:

- [Codecov Example](https://github.com/codecov/example-java-maven) (example of how to set up Codecov badge)
- [Codecov Dashboard](https://app.codecov.io/gh/alvelchev/spring-practices-demo)

## How to Start the Project

1. Clone the repository.
2. Start the local PostgreSQL.
3. Run Maven clean install.
4. Open the Swagger URL to test the endpoints: [Swagger UI](http://localhost:8099/swagger-ui/index.html)
5. Open Spring Boot Actuator endpoint: [Actuator](http://localhost:8099/actuator/)

## Provided Examples with Implementation on Spring Boot

- Pageable and Sorting Response: [Baeldung Tutorial](https://www.baeldung.com/spring-data-jpa-pagination-sorting) → (
  controller package)
- Flyway: [Baeldung Tutorial](https://www.baeldung.com/database-migrations-with-flyway) → (resources.db.migration
  package)
- JUNIT5: [Baeldung Tutorial](https://www.baeldung.com/junit-5) → (tests package)
- Global Exception Handler in Java: [Baeldung Tutorial](https://www.baeldung.com/java-global-exception-handler) → (
  advice package)
- CriteriaBuilder: [Baeldung Tutorial](https://www.baeldung.com/spring-data-criteria-queries) → (repository.custom
  package and CriteriaBuilderExampleController)
- Custom Validator: [Baeldung Tutorial](https://www.baeldung.com/spring-mvc-custom-validator) → (validator package)
- Spring Boot Actuator: [Baeldung Tutorial](https://www.baeldung.com/spring-boot-actuators)
- Injecting Git Information Into Spring: [Baeldung Tutorial](https://www.baeldung.com/spring-git-information) → (
  HistoryLinkProvidingGitInfoContributor.class)
- Quartz Job Scheduler with Cron Triggers and Expressions: [Quartz Scheduler](https://www.quartz-scheduler.org/) → (
  configuration.quartz package)
- Spring Custom Events: [Baeldung Tutorial](https://www.baeldung.com/spring-events) → (event package)

# Branches:

- Branch with name: "java11Version" is set up for JDK11
- Main branch is with the newest spring boot 3.0.2 and JDK17

# Git commit plugin response:

```yaml
// 20221101225521
// http://localhost:8099/actuator/info

{
  "git": {
    "commit": {
      "message": {
        "short": "Merge branch 'main' into gitCommitPlugin"
      },
      "id": {
        "abbrev": "34078b6",
        "full": "34078b613c32ac9cf4f076b42232e3d9523aaeb7",
        "describe": "34078b6-dirty"
      },
      "time": "2022-11-01T18:59:21Z",
      "history": "https://github.com/alvelchev/spring-practices-demo/commit/4b36876"
    },
    "branch": "gitCommitPlugin",
    "build": {
      "time": "2022-11-01T20:53:58Z",
      "version": "0.0.1"
    }
  },
  "build": {
    "artifact": "springpageable",
    "name": "springpageable",
    "time": "2022-11-01T20:53:56.838Z",
    "version": "0.0.1",
    "group": "com.springpracticesdemo"
  }
}
