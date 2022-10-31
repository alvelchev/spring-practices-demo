![Spring](https://github.com/alvelchev/spring-practices-demo/actions/workflows/build.yml/badge.svg)
# Spring Boot Application, support data jpa and pageable and sorting result


In this project is demonstrated main Java Spring concepts.
Model view controller architecture, using mapstruct for objects mapping,flyway for database migration,JUNIT 5 configuration for test coverage,read and filter data from json file and return it like a response, basic CRUD operations.Also is demonstrated how to return a Pageable response which is easy to filter, sorting and etc.Created Global exception handler for handle exception from the codebase and custom spring validator(example with annotation for field and class level).Demo of how to use CriteBuilder for data retrieving.

Added integration with GitHub actions for code analyzis 
- https://www.sonarsource.com/products/sonarcloud/
- https://sonarcloud.io/project/overview?id=alvelchev_spring-pageable-response-demo

# How to start the project
 - 1.Clone the repo
 - 2.Start local postgre sql
 - 3.Maven clean install
 - 4.Open swagger url to test the endpoints: http://localhost:8099/swagger-ui/index.html
 

# Provided examples with implementation on SpringBoot.Links for more info:
- PageAble and sorting response: https://www.baeldung.com/spring-data-jpa-pagination-sorting
- Flyway: https://www.baeldung.com/database-migrations-with-flyway
- JUNIT5: https://www.baeldung.com/junit-5
- Global exception handler in JAVA: https://www.baeldung.com/java-global-exception-handler
- Actuator: https://www.baeldung.com/spring-boot-actuators
- CriteriaBuilder: https://www.baeldung.com/spring-data-criteria-queries
- Custom validator: https://www.baeldung.com/spring-mvc-custom-validator

Example Pageable response:

```yaml
{
  {
   "content": [
    {
      "id": 1,
      "prop1": "test1",
      "prop2": "test2",
      "prop3": "test3"
    },
    {
      "id": 2,
      "prop1": "test1",
      "prop2": "test2",
      "prop3": "test3"
    }
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 6,
  "last": true,
  "numberOfElements": 6,
  "first": true,
  "number": 0,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "size": 10,
  "empty": false
  }
}
