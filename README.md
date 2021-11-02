# Java Bootcamp Payments Project

## Start application

1. Build with maven (Java 11, Maven 3.6.x)  
   `mvn clean install`
2. Run Spring Boot application with VM options `-DfirebaseKey="[...]"`
    - the firebaseKey property is optional and enables Firebase
    - if the property is not read properly using ", replace " with ' and enclose the json using "

## Test application

1. With Swagger by accessing: http://localhost:8080/swagger.html
2. With Curl: `curl 'http://localhost:8080/firebase/getAllPaths' -H 'Cookie: JSESSIONID=[...]'`

## Access deployed application

Go to https://payments-jb.herokuapp.com/ and follow the same steps as when running the application locally 