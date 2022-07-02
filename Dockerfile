FROM java:8-jdk-alpine

COPY ./target/UserInterface.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch UserInterface.jar'

ENTRYPOINT ["java","-jar","UserInterface.jar"]