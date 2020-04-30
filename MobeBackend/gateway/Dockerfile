FROM openjdk:11
ADD target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "gateway.jar"]