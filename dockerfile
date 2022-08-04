FROM openjdk:17

VOLUME /tmp

EXPOSE 9001

ARG JAR_FILE=target/UserMS-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]