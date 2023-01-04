CREATE TABLE accident
(
 accident_id serial,
 accident_intensity int NOT NULL,
 team_id int,
 accident_status char(50) NOT NULL,
 accident_coordinates numeric NOT NULL,
 CONSTRAINT PK_ACC PRIMARY KEY ( accident_id ),
 CONSTRAINT FK_ACC_TEAM FOREIGN KEY ( team_id ) REFERENCES team ( team_id )
);

CREATE INDEX FK_ACC_IDX ON accident
(
 team_id
);