FROM java:8-jdk-alpine

COPY ./target/PatientApp.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch PatientApp.jar'

ENTRYPOINT ["java","-jar","PatientApp.jar"]