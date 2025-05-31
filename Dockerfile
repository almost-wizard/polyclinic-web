# Базовый образ с Java и Maven для компиляции
FROM maven:3.8.6-openjdk-11-slim AS builder

# Копируем исходный код и pom.xml
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Компилируем проект
RUN mvn clean package -DskipTests

# Финальный образ с Tomcat
FROM tomcat:10.1-jdk11-openjdk-slim

# Копируем скомпилированный WAR из builder
COPY --from=builder /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Настраиваем логирование
RUN echo "org.apache.catalina.core.ContainerBase.[Catalina].[localhost].level = FINE" >> /usr/local/tomcat/conf/logging.properties
RUN echo "org.apache.catalina.core.ContainerBase.[Catalina].[localhost].handlers = java.util.logging.ConsoleHandler" >> /usr/local/tomcat/conf/logging.properties

# Открываем порт
EXPOSE 8080

# Запускаем Tomcat
CMD ["catalina.sh", "run"]
