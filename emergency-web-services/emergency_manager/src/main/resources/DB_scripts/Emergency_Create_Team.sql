CREATE TABLE team
(
 team_id  serial,
 station_id int NOT NULL,
 team_quality int NOT NULL,
 CONSTRAINT PK_TEAM PRIMARY KEY ( team_id ),
 CONSTRAINT FK_TEAM_STATION FOREIGN KEY ( station_id ) REFERENCES station ( station_id )
);

CREATE INDEX FK_TEAM_IDX ON team
(
 station_id
);