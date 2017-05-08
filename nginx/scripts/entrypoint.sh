#!/bin/bash

set -e

NGINX_DEST="/etc/nginx/sites-enabled"
[[ ! -d "${NGINX_DEST}" ]] && mkdir -p "${NGINX_DEST}"

[[ "${DEBUG}" -eq 1 ]] && PATTERN="*.dev.conf" || PATTERN="*.prod.conf"

for CONF_FILE in `find /nginx/sites-templated -type f -name ${PATTERN}`; do
	sed -i -e "s/{{NGINX_DOMAIN}}/$NGINX_DOMAIN/g" ${CONF_FILE}
	cp -rp "${CONF_FILE}" "${NGINX_DEST}"
done;

nginx -g 'daemon off;'