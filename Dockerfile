FROM openjdk:8-jdk-alpine
COPY target/recipe-service.jar recipe-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/recipe-service.jar"]
