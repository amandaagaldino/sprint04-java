FROM eclipse-temurin:17-jdk AS build

WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/quarkus-app/ /app/

CMD ["java", "-jar", "/app/quarkus-run.jar"]