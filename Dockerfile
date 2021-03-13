FROM gradle:jdk15-hotspot AS build

COPY . /home/gradle/src

WORKDIR /home/gradle/src

CMD ["gradle", "test", "-q", "--no-daemon"]
