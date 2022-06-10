# Question Three API

This directory contains source code and distribution for an API written
in Java that provides two endpoints. One for creating a unique alphanumeric
string and another for disabling it.

## Prerequisites

You will need Java 17 to run the program.

## Building,

Building from source code. You can build the program from source code using the following
commands:

```sh
$ mvnw clean package
```

This will then create a file `target/question-three.jar`.


## Running and Testing

If you have built the java jar file using the preceeding command, you can simply run: `java -jar target/question-three.jar` to start the API. It runs at port 8080

I have also included a pre-built version of the jar in the Use the following instructions to run the API.
This will start the API at port 8080 on localhost.

```jar
java -jar target/question-three.jar 
```

You can test the API with the following requests:

## Testing the functionality


### Creating a unique alphanumeric string

```bash
$ curl -X POST "http://localhost:8080/tokens/create"
```

### Disabling a unique alphanumeric string

```bash
$ curl -X DELETE "http://localhost:8080/tokens/disable/ABDECF12"
```
