FROM eclipse-temurin:22-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean build

EXPOSE 8081

CMD ["java", "-jar", "build/libs/aplanchados-0.0.1-SNAPSHOT.jar"]
