FROM nginx:1.13.0-alpine

MAINTAINER Gabriel Malet <gabriemalet@gmail.com>

RUN apk update \
    && apk add bash \
    && rm -rf /var/cache/apk/*

WORKDIR /nginx
ADD . /nginx

EXPOSE 80

COPY scripts/entrypoint.sh /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]