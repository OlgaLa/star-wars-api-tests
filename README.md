# star-wars-api-tests

The project contains API tests for https://swapi.dev/

How to run the tests with gradle:
1. [Install gradle](https://gradle.org/install/) and the latest java
2. Clone the project
3. Go to the project folder
4. Run the command in terminal:

`./gradlew test`

How to run the tests in Docker:
1. [Install Docker](https://docs.docker.com/get-docker/) on your local machine 
2. Clone the project 
3. Go to the project folder
4. Run the command in terminal:

Linux/Mac: `docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle:jdk15-hotspot gradle test -q`

Windows in PowerShell: `docker run --rm -u gradle -v "$(PWD):/home/gradle/project" -w /home/gradle/project gradle:jdk15-hotspot gradle test -q`

Open index.html in a browser to see the tests results. This file is in the folder: `/TheStarWarsAPI/build/reports/tests/test/`

