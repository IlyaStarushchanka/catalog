FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD build/libs/catalog-0.0.1-SNAPSHOT.jar catalog.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/catalog.jar"]