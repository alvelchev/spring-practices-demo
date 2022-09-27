# Spring Boot Web Application, support data jpa and pageable and sorting result

In this part of the tutorial series, I show how to setup a Spring MVC controller to suport CRUD operations, a Spring
service facad over a Spring Data JPA repository with pagination support using MapStruct

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
