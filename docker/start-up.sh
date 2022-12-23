#!/bin/bash

set -e

echo "Init configuration..."
sudo apt-get update
sudo apt install dos2unix
sudo apt install docker
echo "Loading configuration ..."
dos2unix init-data/init-postgres-db.sh
docker-compose up --no-start
docker start pimpon_bdd