#mvn -f ./common install
mvn -f ./MobeBackend package -Dmaven.test.skip=true

docker rm -f $(docker ps -qa)

docker image rm serverconfig
docker image rm eureka
docker image rm gateway
docker image rm authentification
docker image rm museews
docker image rm paiementws
docker image rm mobeclient

gnome-terminal -- bash -c "npm install ./mobeclient; docker-compose -f docker-compose-front.yml up; exec bash"
gnome-terminal -- bash -c "mvn compile -f ./MobeFrontend; mvn exec:java -f ./MobeFrontend -Dexec.mainClass='application.Main'"
docker-compose -f docker-compose-back.yml up

