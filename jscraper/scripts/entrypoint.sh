#!/bin/bash

set -e

echo "[DEBUG] > Init configuration."
sed -i \
    -e "s/{{PG_HOST}}/${POSTGRES_HOST}/g" \
    -e "s/{{PG_DB}}/${POSTGRES_DB}/g" \
    -e "s/{{PG_PORT}}/${POSTGRES_PORT}/g" \
    -e "s/{{PG_USER}}/${POSTGRES_USER}/g" \
    -e "s/{{PG_PWD}}/${POSTGRES_PASSWORD}/g" \
    /jscraper/scraper/src/main/resources/application.properties
echo "[DEBUG] > Configuration ok."

echo "[DEBUG] > Build packages"
export GRADLE_USER_HOME=cache && /install/gradle/bin/gradle build -x test || exit 1
echo "[DEBUG] > Build OK."

maxTriesPostgres=10
echo "[DEBUG] > Check POSTGRES availability"
while ! nc -w 1 "${POSTGRES_HOST}" "${POSTGRES_PORT}" 1>/dev/null 2>&1
do
    [[ "${maxTriesPostgres}" -eq 0 ]] && echo "Postgres database is not available." && exit 1
    sleep 5
    maxTriesPostgres=$((maxTriesPostgres - 1))
done
echo "[DEBUG] > POSTGRES is available."

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