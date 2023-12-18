FROM maven:3.8.6-openjdk-8-slim as builder
WORKDIR /app
COPY . .
RUN mvn -DskipTests package

FROM openjdk:8u342-jre-slim as main
WORKDIR /app
COPY --from=builder /app/target/my-app-1.0-SNAPSHOT.jar .
CMD java -jar my-app-1.0-SNAPSHOT.jar