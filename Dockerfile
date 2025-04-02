FROM maven:3.8.5-openjdk-18 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:18-ea-8-jdk-slim
COPY --from=build /target/ibell-embalagens-api-0.0.1-SNAPSHOT.jar ibell-embalagens-api.jar

# Defina as variáveis de ambiente
ENV DB_URL=""
ENV DB_USERNAME=""
ENV DB_PASSWORD=""

EXPOSE 8080

# Comando para executar a aplicação, referenciando as variáveis de ambiente
ENTRYPOINT ["java", "-jar", "ibell-embalagens-api.jar", \
            "--spring.datasource.url=${DB_URL}", \
            "--spring.datasource.username=${DB_USERNAME}", \
            "--spring.datasource.password=${DB_PASSWORD}"]