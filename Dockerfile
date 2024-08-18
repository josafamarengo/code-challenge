# Use uma imagem base com Java 11 e Maven para compilar o código do usuário
FROM maven:3.8.4-openjdk-11-slim AS build

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo pom.xml para o contêiner (se necessário)
COPY pom.xml .

# Baixe as dependências do Maven e faça a verificação preliminar do projeto
RUN mvn dependency:go-offline

# Copie o código fonte do usuário para o contêiner
COPY src ./src

# Compile o código do usuário (substitua pelo seu comando de compilação específico)
RUN mvn clean package

# Imagem final para executar a aplicação compilada
FROM adoptopenjdk/openjdk11:alpine-jre

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o artefato compilado da etapa anterior para o diretório de trabalho
COPY --from=build /app/target/*-runner.jar ./app.jar

# Comando para executar a aplicação quando o contêiner iniciar
CMD ["java", "-jar", "app.jar"]

