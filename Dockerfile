FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# 🔥 Permissão pro mvnw
RUN chmod +x mvnw

# Build com wrapper
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/eventos-api-0.0.1-SNAPSHOT.jar"]