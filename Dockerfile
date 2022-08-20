FROM openjdk:17-alpine

COPY . /
RUN ./gradlew build
ENTRYPOINT ["java", "-jar", "/build/libs/user-tags-0.0.1-SNAPSHOT.jar"]
