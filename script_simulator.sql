CREATE TABLE accident
(
 accident_id serial,
 accident_intensity int NOT NULL,
 accident_coordinates  point NOT NULL,
 team_id int,
 CONSTRAINT PK_ACC PRIMARY KEY ( accident_id )
 CONSTRAINT FK_ACC_TEAM FOREIGN KEY ( team_id ) REFERENCES team (team_id)
);

CREATE TABLE sensor
(
 sensor_id serial,
 sensor_coordinates point NOT NULL,
 sensor_status char(50) NOT NULL,
 CONSTRAINT PK_SEN PRIMARY KEY ( sensor_id )
);


CREATE TABLE station
(
 station_id serial,
 station_name char(50) NOT NULL,
 station_coordinates point NOT NULL,
 CONSTRAINT PK_STA PRIMARY KEY ( station_id )
);

CREATE TABLE team
(
 team_id  serial,
 station_id int NOT NULL,
 team_quality int NOT NULL,
 team_coordinates point,
 CONSTRAINT PK_TEAM PRIMARY KEY ( team_id ),
 CONSTRAINT FK_TEAM_STATION FOREIGN KEY ( station_id ) REFERENCES station ( station_id )
);

CREATE INDEX FK_TEAM_IDX ON team
(
 accident_id
);

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

INSERT INTO sensor ( sensor_coordinates, sensor_status )
VALUES
	(point(0,0), true),
	(point(1,0), true),
	(point(2,0), true),
	(point(3,0), true),
	(point(4,0), true),
	(point(5,0), true),
	(point(6,0), true),
	(point(7,0), true),
	(point(8,0), true),
	(point(9,0), true),
	
	(point(0,1), true),
	(point(1,1), true),
	(point(2,1), true),
	(point(3,1), true),
	(point(4,1), true),
	(point(5,1), true),
	(point(6,1), true),
	(point(7,1), true),
	(point(8,1), true),
	(point(9,1), true),
	
	(point(0,2), true),
	(point(1,2), true),
	(point(2,2), true),
	(point(3,2), true),
	(point(4,2), true),
	(point(5,2), true),
	(point(6,2), true),
	(point(7,2), true),
	(point(8,2), true),
	(point(9,2), true),
	
	(point(0,3), true),
	(point(1,3), true),
	(point(2,3), true),
	(point(3,3), true),
	(point(4,3), true),
	(point(5,3), true),
	(point(6,3), true),
	(point(7,3), true),
	(point(8,3), true),
	(point(9,3), true),
	
	(point(0,4), true),
	(point(1,4), true),
	(point(2,4), true),
	(point(3,4), true),
	(point(4,4), true),
	(point(5,4), true),
	(point(6,4), true),
	(point(7,4), true),
	(point(8,4), true),
	(point(9,4), true),
	
	(point(0,5), true),
	(point(1,5), true),
	(point(2,5), true),
	(point(3,5), true),
	(point(4,5), true),
	(point(5,5), true),
	(point(6,5), true),
	(point(7,5), true),
	(point(8,5), true),
	(point(9,5), true);
	
INSERT INTO vehicle ( vehicle_id, vehicle_coordinates )
VALUES
	(9, point(5,2)),
	(10, point(2,3)),
	(11, point(4,0)),
	(12, point(0,1)); 
	
INSERT INTO station ( station_id, station_name, station_coordinates )
VALUES
	(1, 'Caserne de la Madre', POINT(0,1)),
	(2, 'Caserne Edouard Philippe', POINT(3,0)),
	(3, 'Caserne du Bédouin', POINT(3,4)),
	(4, 'Caserne Hannibal Barca', POINT(3,7)),
	(5, 'Caserne George Sand', POINT(1,5)),
	(6, 'Caserne des papiers', POINT(6,9));