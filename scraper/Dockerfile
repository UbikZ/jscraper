FROM java:8-jdk-alpine

MAINTAINER Gabriel Malet <gabriemalet@gmail.com>

ENV PROJECT "jscraper"
ENV PROJECT_VERSION 0.0.1
ENV GRADLE_VERSION 3.3
ENV BIN_PATH /usr/local/bin
ENV BIN "gradle"

RUN apk update \
    && apk add bash ca-certificates wget \
    && rm -rf /var/cache/apk/*

WORKDIR $BIN_PATH

RUN wget https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip \
    && unzip gradle-$GRADLE_VERSION-bin.zip \
    && rm -f gradle-$GRADLE_VERSION-bin.zip \
    && chmod +x gradle-$GRADLE_VERSION \
    && ls -alt && ln -s gradle-$GRADLE_VERSION $BIN

WORKDIR /src
ADD . /src
ADD build.gradle /src/build.gradle

RUN $BIN_PATH/$BIN build

CMD java -jar /src/${PROJECT}-${PROJECT_VERSION}.jar