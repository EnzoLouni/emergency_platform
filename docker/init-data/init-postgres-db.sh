#!/bin/bash

set -e

psql -U ${POSTGRES_USER:-emergency} ${POSTGRES_DB:-postgres} <<-EOSQL
	SELECT 'CREATE DATABASE ${PROJET_SF:-projet_sf}' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '${PROJET_SF:-projet_sf}')\gexec ;
    \c ${PROJET_SF:-projet_sf};
    CREATE SCHEMA IF NOT EXISTS emergency AUTHORIZATION ${EMERGENCY_USER:-emergency};
    GRANT ALL PRIVILEGES ON DATABASE ${PROJET_SF:-projet_sf} TO ${EMERGENCY_USER:-emergency};
	CREATE SCHEMA IF NOT EXISTS simulator AUTHORIZATION ${EMERGENCY_USER:-emergency};
    GRANT ALL PRIVILEGES ON DATABASE ${PROJET_SF:-projet_sf} TO ${EMERGENCY_USER:-emergency};
EOSQL