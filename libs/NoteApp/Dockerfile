FROM java:8-jdk-alpine

COPY ./target/NoteApp.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch NoteApp.jar'

ENTRYPOINT ["java","-jar","NoteApp.jar"]