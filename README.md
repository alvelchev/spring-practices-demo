# Spring Boot Web Application, support data jpa and pageable and sorting result

In this project is demonstrated main Java Spring concepts.
Model view controller architecture, using mapstruct for objects mapping,flyway for database migration,JUNIT 5 configuration for test coverage,read and filter data from json file and return it like a response, basic CRUD operations.Also is demonstrated how to return a Pageable response which is easy to filter, sorting and etc.Created Global exception handler for handle exception from the codebase.

Added integration with git hub actions for code analyzis (https://www.sonarsource.com/products/sonarcloud/)
https://sonarcloud.io/project/overview?id=alvelchev_spring-pageable-response-demo

# Links for more info:
- PageAble and sorting response: https://www.baeldung.com/spring-data-jpa-pagination-sorting
- Flyway: https://www.baeldung.com/database-migrations-with-flyway
- JUNIT5: https://www.baeldung.com/junit-5
- Global exception handler in JAVA: https://www.baeldung.com/java-global-exception-handler
- Actuator: https://www.baeldung.com/spring-boot-actuators


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
