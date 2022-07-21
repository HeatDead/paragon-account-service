FROM openjdk:17-oracle

VOLUME /paragon-account-service

EXPOSE 8081

ARG JAR_FILE=target/paragon-account-service-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} paragon-account-service.jar

ENTRYPOINT ["java","-jar","/paragon-account-service.jar"]