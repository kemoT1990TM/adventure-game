FROM eclipse-temurin:25-jre
VOLUME /tmp
ARG JAR_FILE=target/adventure-game-reactive-0.1.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
