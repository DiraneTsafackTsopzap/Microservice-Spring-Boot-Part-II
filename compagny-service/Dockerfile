# Spécifiez l'image de base OpenJDK
FROM openjdk:22

  # Définir une variable d'argument pour le fichier JAR
ARG JAR_FILE=target/*.jar

  # Copier le fichier JAR dans l'image Docker
  # Notez l'utilisation correcte de la syntaxe de substitution dans COPY
COPY ${JAR_FILE} /compagny-service.jar

  # Définir le point d'entrée pour l'image Docker
ENTRYPOINT ["java", "-jar", "/compagny-service.jar"]

  # Exposer le port sur lequel l'application sera accessible
EXPOSE 8081