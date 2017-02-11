FROM java:8-jdk-alpine

MAINTAINER Gabriel Malet <gabriemalet@gmail.com>

ENV PROJECT "jscraper"
ENV GRADLE_VERSION 3.3
ENV PROJECT_VERSION 0.0.1

RUN apk update \
    && apk add bash ca-certificates wget libstdc++ \
    && rm -rf /var/cache/apk/*

WORKDIR /install

RUN wget  https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip && \
    unzip gradle-$GRADLE_VERSION-bin.zip && \
    rm -f gradle-$GRADLE_VERSION-bin.zip && \
    ln -s gradle-$GRADLE_VERSION gradle && \
    echo -ne "- with Gradle $GRADLE_VERSION\n" >> /root/.built

COPY scripts/entrypoint.sh /entrypoint.sh

ADD . /jscraper
WORKDIR /jscraper

ENTRYPOINT ["/entrypoint.sh"]