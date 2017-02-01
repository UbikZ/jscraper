FROM redis:3.2.7-alpine

MAINTAINER Gabriel Malet <gabrielmalet@gmail.com>

RUN apk update \
    && apk add bash pwgen \
    && rm -rf /var/cache/apk/* /tmp/* /var/tmp/*

COPY scripts/entrypoint.sh /entrypoint.sh
COPY redis.conf /etc/redis/redis.conf

CMD ["/entrypoint.sh"]