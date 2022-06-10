# Question One


This directory contains an API implemented using Java using Spark Framework and the database used is SQLite.

It is a Complaints database that keeps information about complaints filed by People.

The following API endpoints allow searching for Complaints

```shell
GET /complaints/search?field=description&s=Some Query
```


```shell
GET /people/search?field=description&s=Some Query
```

## Running and Testing

You can run the program using Java jar

```shell
$ java -jar target/question-one.jar
```

It will start a web server at port 3000 on localhost and you will be able to interact with the API.

```shell
GET http://localhost:3000/people/search?field=first_name&s=John Banda
```
