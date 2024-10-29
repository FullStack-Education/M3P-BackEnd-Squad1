FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S executor && adduser -S executor -G executor
RUN mkdir -p /opt/app
RUN chown executor:executor /opt/app/
COPY target/educonnect-0.0.1-SNAPSHOT.jar /opt/app/app.jar
EXPOSE 8081
USER executor
WORKDIR /opt/app
ENTRYPOINT ["java", "-jar", "app.jar"]
