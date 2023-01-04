CREATE TABLE sensor
(
 sensor_id serial,
 sensor_coordinates point NOT NULL,
 sensor_status boolean NOT NULL,
 CONSTRAINT PK_SEN PRIMARY KEY ( sensor_id )
);

CREATE TABLE station
(
 station_id int,
 station_name char(50) NOT NULL,
 station_coordinates  point NOT NULL,
 CONSTRAINT PK_STA PRIMARY KEY ( station_id )
);

INSERT INTO station ( station_id, station_name, station_coordinates )
VALUES
	(1, 'Caserne de la Madre', POINT(0,1)),
	(2, 'Caserne Edouard Philippe', POINT(3,0)),
	(3, 'Caserne du Bédouin', POINT(3,4)),
	(4, 'Caserne Hannibal Barca', POINT(3,7)),
	(5, 'Caserne George Sand', POINT(1,5)),
	(6, 'Caserne des papiers', POINT(6,9));

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

CREATE TABLE agent
(
 agent_id serial,
 agent_name char(50) NOT NULL,
 team_id int,
 agent_exhaustion int NOT NULL,
 agent_quality int NOT NULL,
 CONSTRAINT PK_AG PRIMARY KEY ( agent_id ),
 CONSTRAINT FK_AG_TEAM FOREIGN KEY ( team_id ) REFERENCES team ( team_id )
);

CREATE INDEX FK_AG_IDX ON agent
(
 team_id
);

CREATE TABLE vehicle
(
 vehicle_id int,
 vehicle_capacity int NOT NULL,
 team_id int,
 vehicle_tank_capacity int NOT NULL,
 vehicle_isheavyweight boolean NOT NULL,
 vehicle_quality int,
 CONSTRAINT PK_VEC PRIMARY KEY ( vehicle_id ),
 CONSTRAINT FK_VEC_TEAM FOREIGN KEY ( team_id ) REFERENCES team ( team_id )
);

CREATE INDEX FK_VEC_IX ON vehicle
(
 team_id
);

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

INSERT INTO agent ( agent_name, agent_exhaustion, agent_quality )
VALUES
	('Lonchambon Alexo', 0, 1 ),
	('Jean-Marie Lepen', 0, 10),
	('Mathias efdépé', 0, 3),
	('Raphou Laroué', 0, 7),
	('Jean Pourtal', 0, 2),
	('Diego Malafo', 0, 9),
	('Elise Mommy', 0, 4),
	('Big T', 4, 7),
	('Anne Chipolata', 2, 2),
	('John Doe', 0, 8);

INSERT INTO vehicle ( vehicle_id, vehicle_capacity, vehicle_tank_capacity, vehicle_isheavyweight )
VALUES
    (9, 12, 300, true),
    (10, 6, 120, false),
    (11, 4, 100, false),
    (12, 8, 260, true);

INSERT INTO equipment ( equipment_type, vehicle_id, features, quality )
VALUES
	( 'echelle', 9, '25m', 4),
	( 'echelle', 12, '40m', 7),
	( 'lance', 10, 'mousse', 10),
	( 'lance', 12, 'eau', 10),
	( 'lance', 9, 'eau', 10),
	( 'lance', 11, 'mousse', 10),
	( 'hache', 12, '', 4),
	( 'hache', 9, '', 4);


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