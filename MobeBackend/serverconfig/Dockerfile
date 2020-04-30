FROM openjdk:11
ADD target/serverconfig-0.0.1-SNAPSHOT.jar serverconfig.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "serverconfig.jar"]