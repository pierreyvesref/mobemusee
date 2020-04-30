
Bienvenue sur notre applciation MOBE !

Pré-requis :
 - maven
 - node.js (commandes ng et npm)
 - docker
 - un peu de patience (dockerisation assez longue en particulier pour Angular)


 -> executer le script "script-docker.sh"



Ce fichier exécute les commandes suivantes :

	packaging en jar du backend :
$ mvn -f ./MobeBackend package -Dmaven.test.skip=true

	#conteneurisation du backend :
$ docker-compose -f docker-compose-back.yml up

	#conteneurisation du frontend :
$ docker-compose -f docker-compose-front.yml up

	#compilation de javafx :
$ mvn compile -f ./MobeFrontend; mvn exec:java -f ./MobeFrontend -Dexec.mainClass='application.Main'




