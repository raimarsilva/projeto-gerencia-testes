# FROM openjdk:8-alpine (deprecated)
FROM eclipse-temurin:25

COPY . .

# Utilizar o -x test para retirar os testes na compilação, pois dará erro
# por causa do banco de dados
# RUN ./gradlew clean build -x test

RUN mkdir /build/libs/ && chmod +x /build/libs/Projeto-Testes-0.0.1-SNAPSHOT.jar

WORKDIR /build/libs

#RUN javac Main.java

CMD ["java", "-Dspring.profiles.active=dev", "-jar", "Projeto-Testes-0.0.1-SNAPSHOT.jar"]
