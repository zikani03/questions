# Question Three API

This directory contains source code and distribution for an API written
in Java that provides two endpoints. One for creating a unique alphanumeric
string and another for disabling it.

## API

The API returns output using `text/plain`

**Create a token**: Generates a unique 9 character alphanumeric string

```
POST /tokens/create
Content-Type: text/plain
```

**Disable token**: Disables previously generated unique string

```
DELETE /tokens/disable/<token>
Content-Type: text/plain
```

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

If you have built the java jar file using the preceeding command, 
you can simply run: `java -jar target/question-three.jar` to start the API. It runs at port 8080

I have also included a pre-built version of the jar in the Use the following instructions to run the API.
This will start the API at port 8080 on localhost.

```jar
java -jar target/question-three.jar 
```

You can test the API with the following requests:

## Testing the functionality

### Creating a unique alphanumeric string

You can use the following CURL request to generate a new unique alphanumeric string:

```bash
$ curl -X POST "http://localhost:8080/tokens/create"
```

### Disabling a unique alphanumeric string

You can use the following cURL request to disable a given token.

```bash
$ curl -X DELETE "http://localhost:8080/tokens/disable/ABDECF123"
```

After disabling a string, you can try to use the /create endpoint again, you will receive
a previously disabled string instead of a new one. If there isn't a disabled token, you
will receive a new string.
