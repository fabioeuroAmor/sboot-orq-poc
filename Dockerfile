# Usar uma imagem base do OpenJDK
FROM openjdk:21-jdk-slim
RUN java -version

# Definir a localização do aplicativo
ARG JAR_FILE=target/*.jar

# Copiar o JAR do build para a imagem
COPY ${JAR_FILE} app.jar

# Executar o JAR
ENTRYPOINT ["java","-jar","/app.jar"]
