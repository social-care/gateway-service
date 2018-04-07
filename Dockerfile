FROM maven:3.5-jdk-9-slim AS build-env
WORKDIR /build
COPY . ./
RUN mvn compile war:war
WORKDIR /build/target/
RUN mv GatewayService-*.war gateway.war

FROM tomcat:9-slim
WORKDIR /usr/local/tomcat/webapps
COPY --from=build-env /build/target/gateway.war ./
