FROM openjdk:11
COPY target/kameleoon-application.jar /opt
WORKDIR /opt
EXPOSE 8080
CMD ["java", "-jar", "/opt/kameleoon-application.jar"]