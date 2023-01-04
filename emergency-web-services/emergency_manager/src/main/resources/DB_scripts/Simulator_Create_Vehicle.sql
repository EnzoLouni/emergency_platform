CREATE TABLE vehicle
(
 vehicle_id serial,
 team_id int NOT NULL,
 vehicle_coordinates point,
 CONSTRAINT PK_VEC PRIMARY KEY ( vehicle_id ),
 CONSTRAINT FK_VEC_TEAM FOREIGN KEY ( team_id ) REFERENCES team ( team_id )
);

CREATE INDEX FK_VEC_IDX ON vehicle
(
 team_id
);