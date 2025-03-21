# Use uma imagem base do Java
FROM openjdk:11-jre-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR do seu aplicativo para o contêiner
COPY target/sboot-orq-poc-0.0.1-SNAPSHOT.jar aplicativo.jar

# Exponha a porta que o aplicativo usará
EXPOSE 8080

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "aplicativo.jar"]
