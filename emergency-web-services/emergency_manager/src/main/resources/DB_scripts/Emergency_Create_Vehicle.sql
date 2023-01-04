CREATE TABLE vehicle
(
 vehicle_id serial,
 vehicle_capacity int NOT NULL,
 team_id int,
 vehicle_tank_capacity int NOT NULL,
 vehicle_isheavyweight boolean NOT NULL,
 vehicle_quality int,
 CONSTRAINT PK_VEC PRIMARY KEY ( vehicle_id ),
 CONSTRAINT FK_VEC_TEAM FOREIGN KEY ( team_id ) REFERENCES team ( team_id )
);

CREATE INDEX FK_VEC_IX IN vehicle
(
 team_id
);