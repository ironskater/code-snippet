# Activation
* step1. mvn clean install
* step2. docker-compose up --build

# Service access example
## Valid example
* GET http://localhost:8080/validation?password=123abc
* Expected Response
```json
{
  "isValid": true
}
```
## Invalid example
* GET http://localhost:8080/validation?password=123abcabc
* Expected Response
```json
{
  "isValid": false
}
```