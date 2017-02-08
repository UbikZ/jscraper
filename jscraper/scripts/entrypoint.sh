#!/bin/bash

# Wait database is online
echo "[DEBUG] > Check database availability"
java -jar /jscraper/database/build/libs/dbConnect-0.0.1.jar \
    "check" "$POSTGRES_HOST" "$POSTGRES_PORT" "$POSTGRES_NAME" "$POSTGRES_USER" "$POSTGRES_PASSWORD" || \
     exit 1
echo "[DEBUG] > Database is ready."

echo "[DEBUG] > Migrate database"
java -jar /jscraper/database/build/libs/dbConnect-0.0.1.jar \
    "migrate" "$POSTGRES_HOST" "$POSTGRES_PORT" "$POSTGRES_NAME" "$POSTGRES_USER" "$POSTGRES_PASSWORD" || \
     exit 1
echo "[DEBUG] > Database is migrated."

# Launch server
echo "[DEBUG] > Launch server."
java -jar /jscraper/scraper/build/libs/jscraper-0.0.1.jar