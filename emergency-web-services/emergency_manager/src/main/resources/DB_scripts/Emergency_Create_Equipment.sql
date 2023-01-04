CREATE TABLE equipment
(
 equipment_id serial,
 equipment_type char(50) NOT NULL,
 vehicle_id int NOT NULL,
 features char(50),
 quality int NOT NULL,
 CONSTRAINT PK_EQ PRIMARY KEY ( equipment_id ),
 CONSTRAINT FK_EQ_VEC FOREIGN KEY ( vehicle_id ) REFERENCES vehicle ( vehicle_id )
);

CREATE INDEX FK_EQ_IDX ON equipment
(
 vehicle_id
);