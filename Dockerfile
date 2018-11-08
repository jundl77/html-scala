FROM hseeberger/scala-sbt:11.0.1_2.12.7_1.2.6

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY . /usr/src/app

RUN ["sbt", "compile"]

EXPOSE 3001

ENTRYPOINT ["sbt",  "run"]