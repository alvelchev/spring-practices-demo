# Spring Boot Web Application, support data jpa and pageable and sorting result

In this project is demonstrated main Java Spring concepts.
Model view controller architecture, using mapstruct for objects mapping,flyway for database migration,JUNIT 5 configuration for test coverage,read and filter data from json file and return it like a response, basic CRUD operations.Also is demonstrated how to return a Pageable response which is easy to filter, sorting and etc.

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
