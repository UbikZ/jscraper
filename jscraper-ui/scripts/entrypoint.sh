#!/bin/bash

set -e

[[ "${DEBUG}" -eq 1 ]] && MODE="dev" || MODE="prod"

npm run prebuild && npm run ${MODE}

cp -rp /src/static /var/www/jscraper

tail -f /dev/null