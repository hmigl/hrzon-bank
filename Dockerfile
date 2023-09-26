FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

COPY .mvn/ .mvn
COPY pom.xml ./

COPY src ./src

# TODO: fix tests and remove 'skipTests' option
RUN mvn -DskipTests=true package

# lightweight base image just for running the application
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /hrzon-bank-app

COPY --from=build /app/target/*.jar ./app.jar

CMD ["java", "-jar", "app.jar"]
