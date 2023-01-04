CREATE TABLE team
(
 team_id  serial,
 station_id int NOT NULL,
 team_quality int NOT NULL,
 team_coordinates point,
 CONSTRAINT PK_TEAM PRIMARY KEY ( team_id ),
 CONSTRAINT FK_TEAM_STATION FOREIGN KEY ( station_id ) REFERENCES station ( station_id )
);