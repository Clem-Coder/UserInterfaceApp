# Mediscreen

This application is designed to help health clinic to detect diabetes

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

- Docker

### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Docker:

After downloading docker, go in libs folder. This folder contain Dockerfile of applications we need to run in containers

### Create containes

For each application, go in the folder that contain the .jar and the Dockerfile with a terminal, and run this command: docker build -t (application_name) .
You have to respect the name of the application to run the docker compose:
PatientApp name -> patientapp
NoteApp name -> noteapp
AssessApp name -> assessapp
UserInterfaceAPP name -> interface

### Running App

go to the location of the docker-compose with terminal and run the command : docker-compose up

### Use Mediscreen

Once all the application are running in a docker container, go on : http://(docker-machine-ip):8080/

You are now in the homepage of the application ! 

You can :
-Search a patient
-Register new patient
-Add notes
-Asess a patient's risk of developing diabetes

