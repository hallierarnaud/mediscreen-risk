FROM openjdk:11
COPY ./build/libs/Mediscreen-risk-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar", "Mediscreen-risk-0.0.1-SNAPSHOT.jar"]