#FROM maven:3-jdk-8-alpine
#COPY . /usr/src/app
#WORKDIR /usr/src/app
#RUN mvn package
#ENV PORT 5000
#EXPOSE $PORT
#CMD [ "sh", "-c", "mvn -Dserver.port=${PORT} spring-boot:run" ]


