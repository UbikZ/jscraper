#!/bin/bash

set -e

DEST="/var/www/jscraper"

[[ "${DEBUG}" -eq 1 ]] && MODE="dev" || MODE="prod"

npm run prebuild && npm run ${MODE}

mkdir -p ${DEST} && cp -rp /src/static ${DEST}

tail -f /dev/null