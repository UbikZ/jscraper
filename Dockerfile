FROM maven:3-jdk-8-alpine

MAINTAINER Gabriel Malet <gabriemalet@gmail.com>

ENV PROJECT_NAME "jscraper"

ENV API_NAME "jscraper-api"
ENV CONSOLE_NAME "jscraper-console"

RUN apk update \
    && apk add bash \
    && rm -rf /var/cache/apk/*

ADD . /${PROJECT_NAME}
WORKDIR /${PROJECT_NAME}

EXPOSE 3000

COPY jscraper-scripts/entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]