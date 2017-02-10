#!/bin/bash

set -e

maxTriesPostgres=10
echo "[DEBUG] > Check POSTGRES availability"
while ! nc -w 1 "${POSTGRES_HOST}" "${POSTGRES_PORT}" 1>/dev/null 2>&1
do
    [[ "${maxTriesPostgres}" -eq 0 ]] && echo "Postgres database is not available." && exit 1
    sleep 5
    maxTriesPostgres=$((maxTriesPostgres - 1))
done
echo "[DEBUG] > POSTGRES is available."

maxTriesRedis=10
echo "[DEBUG] > Check REDIS availability"

while ! nc -w 1 "${REDIS_HOST}" "${REDIS_PORT}" 1>/dev/null 2>&1
do
    [[ "${maxTriesRedis}" -eq 0 ]] && echo "Redis database is not available." && exit 1
    sleep 5
    maxTriesRedis=$((maxTriesRedis - 1))
done
echo "[DEBUG] > REDIS is available."

echo "[DEBUG] > Check database connection"
java -jar /jscraper/database/build/libs/dbConnect-0.0.1.jar \
    "check" "${POSTGRES_HOST}" "${POSTGRES_PORT}" "${POSTGRES_DB}" "${POSTGRES_USER}" "${POSTGRES_PASSWORD}" || \
     exit 1
echo "[DEBUG] > Database is available to connections."

echo "[DEBUG] > Migrate database"
java -jar /jscraper/database/build/libs/dbConnect-0.0.1.jar \
    "migrate" "${POSTGRES_HOST}" "${POSTGRES_PORT}" "${POSTGRES_DB}" "${POSTGRES_USER}" "${POSTGRES_PASSWORD}" || \
     exit 1
echo "[DEBUG] > Database is migrated."

# Launch server
echo "[DEBUG] > Launch server."
java -jar /jscraper/scraper/build/libs/jscraper-0.0.1.jar