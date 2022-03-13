# SPRING BOOT API ON HEROKU

### Intro :page_facing_up:

Trying to use a spring boot api, published on heroku cloud.

### Project Structure :wrench:

- [Maven](https://maven.apache.org/guides/index.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [Lombok](https://projectlombok.org/setup/maven)
- [WireMock](https://wiremock.org/docs/spring-boot/)
- [Mockito](https://site.mockito.org/)

### Connection to Heroku

Heroku URL: `https://springboot-messages.herokuapp.com/`

It's possible to set heroku to integrate to GitHub and auto deploy itself. But, you do need have the `Procfile` file on
your source root set ([read more](https://devcenter.heroku.com/articles/procfile)).

On this project, we set `Procfile` as below:

```
web: java -Dserver.port=$PORT -jar target/poc-heroku-1.0.jar
```

Also, bu default, heroku works on jdk 1.8, to make it work on another version, you'll need to set
the `system.properties` on your source root, passing de desired jdk, as below:

```
java.runtime.version=11
```

#### Connection to Heroku - Using GitHub Actions

Just in case, you don't want to auto deploy on heruko, you can set a job using GitHub actions, as
below ([read more](https://github.com/AkhileshNS/heroku-deploy)):

````
  deploy:
    name: Heroku Deploy
    needs: build
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: akhileshns/heroku-deploy@v3.12.12
        with:
          heroku_api_key: ${{secrets.HEROKU_API_KEY}}
          heroku_app_name: ${{secrets.HEROKU_APP_NAME}}
          heroku_email: ${{secrets.HEROKU_EMAIL}}
````

_**Obs.:** On both ways you'll need to set the `Procfile` and `system.properties` files._ :yum:

### Brainshop A.I. :boom:

This project integrates and uses [Brainshop](https://brainshop.ai/) API's, emulating a WeChat with an A.I.

### Code Coverage :white_check_mark:

This project uses [Snyk](https://docs.snyk.io/) and [Sonar Cloud](https://sonarcloud.io/) for check code coverage and quality, integrated to pipelines.

### Do it Yourself :bomb:

#### How to build:

````
mvn clean install -DskipTests
````

#### How to execute unit tests:

````
mvn test
````

#### How to execute functional tests / integration tests:

````
mvn clean test -Pfunctional-test
````

#### How to run spring boot application:

````
mvn spring-boot:run
````

While application running, the below URLs'll be able: :rocket:

- Actuator: `http://localhost:8080/actuator`
- Swagger: `http://localhost:8080/swagger-ui/index.html`
- Swagger Resources `http://localhost:8080/swagger-resources`
- Aplication URL: `http://localhost:8080/api/v1/`
