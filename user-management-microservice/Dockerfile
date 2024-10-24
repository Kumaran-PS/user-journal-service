FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/user-management-microservice-0.0.1-SNAPSHOT.jar user-management-microservice.jar
ENTRYPOINT ["java","-jar","/user-management-microservice.jar"]
