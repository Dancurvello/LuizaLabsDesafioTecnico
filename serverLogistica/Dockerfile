FROM openjdk:17-alpine

WORKDIR /app

# Exponha a porta que o aplicativo Spring Boot estará ouvindo (ajuste conforme necessário)
EXPOSE 8080

COPY target/serverLogistica-*.jar app.jar

CMD ["java", "-jar", "app.jar"]

