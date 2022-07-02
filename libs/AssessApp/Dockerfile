FROM java:8-jdk-alpine

COPY ./target/AssessApp.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch AssessApp.jar'

ENTRYPOINT ["java","-jar","AssessApp.jar"]