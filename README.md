# Java Standarts

![Spring](https://github.com/alvelchev/spring-practices-demo/actions/workflows/build.yml/badge.svg)
[![Docker Image CI](https://github.com/alvelchev/spring-practices-demo/actions/workflows/docker-image.yml/badge.svg?branch=main)](https://github.com/alvelchev/spring-practices-demo/actions/workflows/docker-image.yml)
[![codecov](https://codecov.io/gh/alvelchev/spring-practices-demo/branch/main/graph/badge.svg)](https://codecov.io/gh/alvelchev/spring-practices-demo)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
</br>

Integration with
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

# Spring Boot Practices Application

In this project is demonstrated main Java Spring concepts.
Model view controller architecture, using <b>mapstruct</b> for objects mapping,<b>flyway</b> for database migration,<b>
JUNIT</b> 5 configuration for test coverage,read and filter data from json file and return it like a response, basic <b>
CRUD operations</b>.Also is demonstrated how to return a <b>Pageable response</b> which is easy to filter, sorting and
etc.Created <b>Global exception handler</b> for handle exception from the codebase and custom spring validator(example
with annotation for field and class level).Demo of how to use CriteriaBuilder for data retrieving.Added configuration for
collecting application metrics via Spring Boot Actuator and configuration for Injecting Git Information Into Spring.

# GitHub actions pipelines integration
Added integration with GitHub actions for code analyzis with sonarcloud and codecov for code coverage report               
Added integration with Docker.On every push event to main branch this action generates a new docker image with the relevant image tag.

- https://www.sonarsource.com/products/sonarcloud/
- https://sonarcloud.io/project/overview?id=alvelchev_spring-pageable-response-demo
- https://github.com/codecov/example-java-maven (example how to set-up codecov badge)
- https://app.codecov.io/gh/alvelchev/spring-practices-demo

# How to start the project

- 1.Clone the repo
- 2.Start local postgre sql
- 3.Maven clean install
- 4.Open swagger url to test the endpoints: http://localhost:8099/swagger-ui/index.html
- 5.Open Spring Boot Actuator endpoint: http://localhost:8099/actuator/

# Provided examples with implementation on SpringBoot.Links for more info:

- PageAble and sorting response: https://www.baeldung.com/spring-data-jpa-pagination-sorting
- Flyway: https://www.baeldung.com/database-migrations-with-flyway
- JUNIT5: https://www.baeldung.com/junit-5
- Global exception handler in JAVA: https://www.baeldung.com/java-global-exception-handler
- Actuator: https://www.baeldung.com/spring-boot-actuators
- CriteriaBuilder: https://www.baeldung.com/spring-data-criteria-queries
- Custom validator: https://www.baeldung.com/spring-mvc-custom-validator
- Spring Boot Actuator: https://www.baeldung.com/spring-boot-actuators
- Injecting Git Information Into Spring: https://www.baeldung.com/spring-git-information

Git commit plugin response:

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
    "group": "com.springpageable"
  }
}
