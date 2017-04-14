FROM node:7.8-alpine

MAINTAINER Gabriel Malet <gabriemalet@gmail.com>

RUN apk update \
    && apk add bash \
    && rm -rf /var/cache/apk/*

ADD package.json /tmp/package.json
RUN cd /tmp && npm install
RUN mkdir -p /src && cp -a /tmp/node_modules /src/

WORKDIR /src
ADD . /src
RUN npm run prebuild && npm run dev

COPY scripts/entrypoint.sh /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]