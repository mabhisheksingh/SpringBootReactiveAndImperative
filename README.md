# My Reactive and imperative Spring Boot Application

## Using both dependency **web and web flux** but from application.yml override default web application type.
```yml
spring:
  application:
    name: WebFluxSpringApplication
  main:
    web-application-type: reactive
```

## Enable log without restarting the application
### Get the Log level from the base package
![Initial log level](Images/Log%20at%20debug%20level.JPG)
```bash
curl --location 'http://localhost:8080/actuator/loggers/com.abhishek'
```


### Update the Log level in the base package
![log at debug level](Images/Log%20at%20debug%20level.JPG)
```bash
curl --location 'http://localhost:8080/actuator/loggers/com.abhishek' \
--header 'Content-Type: application/json' \
--data '{
  "configuredLevel": "INFO"
}'
```


## keynote while using admin client  in application
```text
If you already using Spring Cloud Discovery for your applications you don’t have to add the Spring Boot Admin Client to your applications. 
Just make the Spring Boot Admin Server a DiscoveryClient, the rest is done by our AutoConfiguration.

The following steps are for using Eureka, but other Spring Cloud Discovery implementations are supported as well. 
There are examples using Consul and Zookeeper.

Also, have a look at the Spring Cloud documentation.
```

## Config needs to change in Admin client side
#### Define port and server in the client 
For the Spring boot admin server visit here [Spring boot Admin server](https://github.com/mabhisheksingh/SpringBootAdminServer) 
```yaml
server:
  port: 8081
  address: 127.0.0.1

```
#### Define the admin server URL and out instance name on the client side.
```yaml
spring:
  boot:
    admin:
      client:
        auto-registration: true
        enabled: true
        url: http://localhost:9000
        instance:
          name: my-client-1
  application:
    name: WebFluxSpringApplication
```

### Implemented Global Exception handling through ControllerAdvice and functional exception handling also.
### testing and code coverage tool also implemented in this Git commit.

