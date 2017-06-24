FROM node:8-alpine

MAINTAINER Gabriel Malet <gabriemalet@gmail.com>

RUN apk update \
    && apk add bash \
    && rm -rf /var/cache/apk/*

ADD package.json /tmp/package.json
RUN cd /tmp && yarn install
RUN mkdir -p /src && cp -a /tmp/node_modules /src/

WORKDIR /src
ADD . /src

COPY scripts/entrypoint.sh /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]