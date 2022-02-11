FROM openjdk:17-slim

RUN mkdir -p /opt/app

ADD build/libs/smart-transfer-server-0.0.1.jar /opt/app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/app/smart-transfer-server-0.0.1.jar"]