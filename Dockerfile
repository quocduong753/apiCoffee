# Giai đoạn build với Gradle + JDK 21
FROM gradle:8.5.0-jdk21 AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# Giai đoạn chạy ứng dụng với JDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
