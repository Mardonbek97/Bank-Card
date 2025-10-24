FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . /app
RUN mvn -q -DskipTests package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/bank-rest-0.0.1-SNAPSHOT.jar"]
