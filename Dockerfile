FROM openjdk:8-jdk-alpine
COPY target/spring5-recipe-app-0.0.1-SNAPSHOT.jar spring5-recipe-app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/spring5-recipe-app-0.0.1-SNAPSHOT.jar"]