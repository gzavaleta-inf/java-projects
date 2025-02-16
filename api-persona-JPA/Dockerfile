FROM openjdk:17
VOLUME /tmp
EXPOSE 8090
ADD target/api-persona-JPA-2.4.5.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]