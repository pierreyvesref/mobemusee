# mobemusee

Welcome to our MOBE application !

University project during my second year of Master's degree (2020). It was made in two months in a team of 4 people.

This is a museum web application which provides :

- an admin interface to manage the museum (JavaFX)
- a web interface to register customers and order museum tickets (Angular)
- a REST API to process requests with museum data (J2E)

You can see the full architecture in the file "Rapport de conception - Projet S2.pdf"



Prerequisites/Pré-requis :
 - maven
 - node.js (ng and npm)
 - docker
 - being patient (dockerization)


 -> run "script-docker.sh" script.


This file run the following commands :

#Backend packaging (jar):
$ mvn -f ./MobeBackend package -Dmaven.test.skip=true

#Backend dockerization :
$ docker-compose -f docker-compose-back.yml up

#Frontend dockerization :
$ docker-compose -f docker-compose-front.yml up

#Javafx compilation :
$ mvn compile -f ./MobeFrontend; mvn exec:java -f ./MobeFrontend -Dexec.mainClass='application.Main'
