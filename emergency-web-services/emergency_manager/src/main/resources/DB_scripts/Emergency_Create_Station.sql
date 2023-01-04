CREATE TABLE station
(
 station_id int,
 station_name char(50) NOT NULL,
 station_coordinates  point NOT NULL,
 CONSTRAINT PK_STA PRIMARY KEY ( station_id )
);