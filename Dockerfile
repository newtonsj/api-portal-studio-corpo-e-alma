FROM adoptopenjdk:11-jre-hotspot

ARG JAR_FILE=target/portal-studio-corpo-e-alma-2.0.2.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-jar", "app.jar"]

EXPOSE 80