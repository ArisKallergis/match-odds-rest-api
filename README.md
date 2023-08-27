# Match Odds REST api

Simple CRUD api with two entities: Match and MatchOdd

## Explore the api
### Without Docker
Postgres needs to be installed and set up to accept username and password described in application.properties

Start the app with `mvn spring-boot:run` and use the following:

- api: [localhost:8080](localhost:8080)
- swagger: [localhost:8080/swagger-ui/index.html](localhost:8080/swagger-ui/index.html)

###  Using Docker
Docker needs to be installed and have the docker-compose command available.

If installed, run `docker-compose up`. The Dockerfile image of the app should be built, and a postgres 15.4 image should be pulled.

- api: [localhost:8081](localhost:8081)
- swagger: [localhost:8081/swagger-ui/index.html](localhost:8081/swagger-ui/index.html)

### Postman
A postman collection is provided in `./postman` folder. Remember to change the `hostUrl` api variable depending
on whether you started the app with docker or not.