#!/bin/bash

set -e

conf="mosquitto/config/mosquitto.conf"
password="mosquitto/passwd/mosquitto_passwd.txt"

if [ -f $conf ]; then
   rm -f $conf
fi
if [ -f $password ]; then
   rm -f $password
fi
touch mosquitto/config/mosquitto.conf
echo "persistence false
log_dest stdout
allow_anonymous false
connection_messages true
listener 1883
password_file /etc/mosquitto/.passwd" > $conf
touch mosquitto/passwd/mosquitto_passwd.txt
echo "emergency:pimpon" > $password
docker-compose up --no-start
docker start mosquitto
docker exec -it mosquitto mosquitto_passwd -U /etc/mosquitto/.passwd
docker restart mosquitto