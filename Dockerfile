# Usa imagem do Java 17
FROM eclipse-temurin:17-jdk
# Diretório dentro do container
WORKDIR /app

# Copia o projeto
COPY src .

# Build do projeto
RUN ./mvnw clean package -DskipTests

# Porta da aplicação
EXPOSE 8080

# Comando para rodar
CMD ["java", "-jar", "target/eventos-api-0.0.1-SNAPSHOT.jar"]