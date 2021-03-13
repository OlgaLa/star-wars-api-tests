# star-wars-api-tests

The project contains API tests for https://swapi.dev/

How to run the tests
1. You can run them using docker. 
To do this you need to clone the project, install docker and run the command in cmd:

docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle:jdk15-hotspot gradle test -q

2. You can run them on your local machine. 
To do this you need to install dependencies and than run 
./gradlew test
