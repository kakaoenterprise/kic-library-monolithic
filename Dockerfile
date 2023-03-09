FROM docker.io/library/openjdk:8u212-jdk-alpine
COPY . .
RUN ./gradlew build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/build/libs/library-monolithic-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=local"]
