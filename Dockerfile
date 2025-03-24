# Usar uma imagem base do OpenJDK
FROM openjdk:11-jre-slim

# Definir a localização do aplicativo
ARG JAR_FILE=target/*.jar

# Copiar o JAR do build para a imagem
COPY ${JAR_FILE} app.jar

# Executar o JAR
ENTRYPOINT ["java","-jar","/app.jar"]
