FROM openjdk:8-jdk

ADD auth-service-1.0.jar auth-service-1.0.jar
RUN sh -c 'touch /auth-service-1.0.jar'
CMD ["java", "-jar", "/auth-service-1.0.jar"]