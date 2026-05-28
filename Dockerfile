# Estágio 1: Compilação (Build) usando Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o pom.xml e baixa as dependências (aproveita o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia a pasta src e gera o arquivo .jar (pulando os testes para o build ser mais rápido)
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Execução (Runtime) usando uma imagem leve do Java 21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia apenas o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Informa a porta que a aplicação vai rodar
EXPOSE 8080

# Comando para iniciar a API
ENTRYPOINT ["java", "-jar", "app.jar"]