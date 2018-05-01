# Social Care Gateway Service with JADE Framework

### Pre requisites
To run this locally, it is necessary to install:
* [Java JDK](http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/)
* [Tomcat](http://tomcat.apache.org/)
* [Docker](https://www.docker.com/)

### Developing

For developing purposes, this project should be built with **Maven**:

```sh
$ mvn compile war:war
```

To run, copy the file located at target/GatewayService-1.0.0.war and deploy on a running instance of Tomcat Server.

### Deploying

Use **Docker** instead if you want to deploy the application:
```sh
# build the image
$ docker build -t gatewayservice .

# run the container
$ docker run --name gatewayservice -p 2000:2000 gatewayservice
```