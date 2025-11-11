# --- FASE 1: Build (A Cozinha de Preparo) ---
# Usamos uma imagem oficial do Maven (com Java 17) para compilar nosso código
FROM maven:3.8-openjdk-17 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o 'mapa' do projeto primeiro, para otimizar o cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia todo o resto do nosso projeto
COPY . .

# Executa o build do Maven, pulando os testes (que rodamos localmente)
RUN mvn clean install -DskipTests

# --- FASE 2: Run (A Linha de Servir) ---
# Usamos uma imagem leve, apenas com o Java Runtime, para economizar recursos
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o arquivo .jar compilado da Fase 1 para a nossa imagem final
COPY --from=build /app/target/adotepet-0.0.1-SNAPSHOT.jar ./app.jar

# O Render nos diz em qual porta devemos rodar usando a variável $PORT.
# Este comando mapeia a porta do Render para a porta do Spring.
CMD java -jar -Dserver.port=${PORT} ./app.jar