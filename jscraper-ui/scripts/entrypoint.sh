#!/bin/bash

set -e

npm run prebuild && npm run dev
cp -rp /src/static /jscraper/scraper/src/main/resources

tail -f /dev/null