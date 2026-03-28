#DEVKIT
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app
COPY . .
RUN chmod +x gradlew
#RUN ./gradlew build -x test
RUN --mount=type=cache,target=/root/.gradle ./gradlew build -x test --stacktrace

#RUNTIME
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
