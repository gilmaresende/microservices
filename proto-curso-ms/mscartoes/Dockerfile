FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#copiar de dentro do proprio container, no caso da pasta onde o maven buildou dentro do container
FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
#EXPOSE 8761 # quando o microservise roda na porta 0, não tem necessidade de expor ela, o eureka e o gatware cuidarão deste acesso
ARG RABBITMQ_SERVER
ARG EUREKA_SERVER

ENTRYPOINT java -jar -Dspring.profiles.active=prod app.jar

#copia do diretorio local do desenvolvimento
#FROM openjdk:17
#WORKDIR /app
#COPY ./target/eurekaserver-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8761
#ENTRYPOINT java -jar app.jar
