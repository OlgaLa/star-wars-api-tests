FROM gradle:jdk15-hotspot AS build

COPY . /home/gradle/src

WORKDIR /home/gradle/src

RUN ls

RUN gradle test --no-daemon

