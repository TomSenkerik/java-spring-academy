# Readme

Learning app of Java and Spring Boot. App contains:

- Actuator autoconfig
- Lombok
- Marshaling and Unmarshaling data with FasterXML Jackson
- Redis, RabbitMQ and MongoDB autoconfig
- Default validation
- Endpoint documentation with OpenAPI

# Environments
### application.properties
There is default one `application.properties` where is property `spring.profiles.active` to switch between dev / test / prod environments.

### intellij
In run/debug configuration there is `Active profiles` where only dev / test / prod is placed.

### argument in CLI
Argument in CLI should be like this. I never try it. `-Dprofile="profile_name"`

# Git repository

https://github.com/TomSenkerik/java-spring-academy      

# Heroku

https://tom-learn-spring.herokuapp.com

# Endpoint documentation
The most interesting is the UI in browser.

### UI in browser
http://localhost:8080/swagger-ui/index.html

### some JSON
http://localhost:8080/api-docs/

### YAML format
http://localhost:8080/api-docs.yaml
