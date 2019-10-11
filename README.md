# KUP - Final practice
This repository consists of two microservices (Blog and Analyzer). Blog is the main microservice and needs the Analyzer to verify every comment that is posted on the blog.

## Requeriments
For building and running the application you may need:
- [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

Also, you can get up and running the application using docker and kubernetes:

- [Docker](https://docs.docker.com/install/)
- [Kubernetes](https://kubernetes.io/es/docs/tasks/tools/install-kubectl/) / [Minikube](https://kubernetes.io/es/docs/tasks/tools/install-minikube/)

## Clonning the repository

```
git clone https://github.com/Crydion/kup.git
```

## Running the application

You can run the application in any of the following ways

### From your IDE

1. Execute the `main` method in the `com.crydion.analyzer.AnalyzerApplication` class.
2. Execute the `main` method in the `com.crydion.blog.BlogApplication` class.

### With docker-compose

1. Open a new terminal and go to the repository directory (where the **docker-compose.yaml** is located).
2. Use the command `docker-compose up`.

### Using Minikube

1. Open a new terminal and go to the repository directory (make sure that you have started Minikube `minikube start`).
2. Use the command ``kubectl create -f minikube/db.yaml && kubectl create -f minikube/analyzer.yaml && kubectl create -f minikube/blog.yaml``.

## Testing the application

To access the application once it is up and running you can do it via localhost:8080 except for minikube. To access the application when it is started from minikube you have to run `kubectl service blog` to see the IP and PORT.

### First step, create a new user
```
curl -X POST \
  http://localhost:8080/users \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 49' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'cache-control: no-cache' \
  -d '{
    "username": "crydion",
    "password": "qwerty"
}'
```

### Login with the user created
In this case, the response from the server will contain a  header **Authorization** that contains the *Bearer* token.
```
curl -X POST \
  http://localhost:8080/login \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 49' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'cache-control: no-cache' \
  -d '{
    "username": "crydion",
    "password": "qwerty"
}'
```
***Authorization: Bearer  eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1NzA3ODQ2NzcsImlzcyI6IkNyeWRpb24iLCJzdWIiOiJjcnlkaW9uIiwiZXhwIjoxNTcxNjQ4Njc3fQ.ob96WzcgwoQ2SKVXXQjrs8Vdm-M234LWleau__Zrp-7p2rXlSgbWhXJGTFOEdlek-D_4kBEd3cYfBnxVwBmtdw***

### Create a post
```
curl -X POST \
  'http://localhost:8080/posts?=' \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Authorization: Bearer  eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1NzA3ODQ2NzcsImlzcyI6IkNyeWRpb24iLCJzdWIiOiJjcnlkaW9uIiwiZXhwIjoxNTcxNjQ4Njc3fQ.ob96WzcgwoQ2SKVXXQjrs8Vdm-M234LWleau__Zrp-7p2rXlSgbWhXJGTFOEdlek-D_4kBEd3cYfBnxVwBmtdw' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 71' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'cache-control: no-cache' \
  -d '{
    "author": "crydion",
    "title": "...",
    "content": "asdafdasfawfaw"
}'
```

### Create a comment
To add a comment to an existing post it is not necessary to be registered in the application, so you don't have to include the Authorization header.
```
curl -X POST \
  http://localhost:8080/posts/1/comments \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 48' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'cache-control: no-cache' \
  -d '{
    "author": "batar",
    "content": "adafawfasf"
}'
```

### List all posts
```
curl -X GET \
  http://localhost:8080/posts \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'cache-control: no-cache'
```

### Get one post
```
curl -X GET \
  http://localhost:8080/posts/1 \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'cache-control: no-cache'
```
