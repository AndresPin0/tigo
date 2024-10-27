# Usa una imagen compatible con Java 22
FROM eclipse-temurin:22-jdk-alpine

WORKDIR /

COPY . .

RUN ./gradlew build

EXPOSE 8081

CMD ["java", "-jar", "build/libs/aplanchados-0.0.1-SNAPSHOT.jar"]
