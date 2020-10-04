# Web Spider Games

## Description:
 - This service get data from a game store and persist it into a relational database. The main job run one time per day. Beside that there is a api with a resource to force the job execution and anothers endpoints
 
## Technologies used
 - **Java 11** - programming language
 - **SpringBoot** - main framework
 - **Mysql** (H2 for local tests) - relational database
 - **PMD** - code analyser
 - **Jsoup** - html parser
 - **Springfox** - swagger documentation
 - **SpringCloudClient** - get configurations
 - **EurekaClient** - service registry and discovery 

## Swagger:
 - http://localhost:8081/swagger-ui.html#/game-api

## Dependencies:
 - https://github.com/eumagnun/config-server
 - https://github.com/eumagnun/eureka-server

## Note:
 - Before run this project run your dependencies
