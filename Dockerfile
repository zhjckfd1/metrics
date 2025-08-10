FROM bellsoft/liberica-openjdk-alpine:17.0.1
#FROM bellsoft/liberica-openjdk-alpine:21.0.2

COPY target/prometheus-0.0.1-SNAPSHOT.jar app/app.jar

#WORKDIR app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app/app.jar"]
#ENTRYPOINT ["java", "-jar", "app.jar"]
