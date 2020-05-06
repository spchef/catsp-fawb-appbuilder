FROM openjdk:8-jdk-alpine
RUN apk update && apk add --update-cache  git maven bash && rm -rf /var/cache/apk/*
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
