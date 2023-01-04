CREATE TABLE accident
(
 accident_id serial,
 accident_intensity int NOT NULL,
 accident_coordinates  point NOT NULL,
 team_id int,
 CONSTRAINT PK_ACC PRIMARY KEY ( accident_id )
 CONSTRAINT FK_ACC_TEAM FOREIGN KEY ( team_id ) REFERENCES team (team_id)
);