#!/bin/bash

if [ "${REDIS_PASS}" == "__reset__" ]; then
    unset REDIS_PASS
fi

if [ ! -f /.redis_configured ]; then
    if [ "${REDIS_PASS}" != "**None**" ]; then
        PASS=${REDIS_PASS:-$(pwgen -s 32 1)}
        _word=$( [ ${REDIS_PASS} ] && echo "preset" || echo "random" )
        echo "=> Securing redis with a ${_word} password"
        echo "requirepass $PASS" >> /etc/redis/redis.conf
        echo "=> Done!"
        echo "========================================================================"
        echo "You can now connect to this Redis server using:"
        echo ""
        echo "    redis-cli -a $PASS -h <host> -p <port>"
        echo ""
        echo "Please remember to change the above password as soon as possible!"
        echo "========================================================================"
    fi

    unset REDIS_PASS

    touch /.redis_configured
fi

exec redis-server /etc/redis/redis.conf