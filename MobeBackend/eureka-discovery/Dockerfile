FROM openjdk:11
ADD target/eureka-discovery-0.0.1-SNAPSHOT.jar eureka-discovery.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-discovery.jar"]