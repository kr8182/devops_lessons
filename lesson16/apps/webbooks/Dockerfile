FROM maven:3.8.1-openjdk-17 as builder
WORKDIR /app
COPY . .
RUN mvn -DskipTests package

FROM openjdk:17 as main
WORKDIR /app
COPY --from=builder /app/target/digitallibrary-1.0.0-snapshot.jar .
CMD java -jar digitallibrary-1.0.0-snapshot.jar
EXPOSE 8080