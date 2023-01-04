CREATE TABLE sensor
(
 sensor_id serial,
 sensor_coordinates point NOT NULL,
 sensor_status boolean NOT NULL,
 CONSTRAINT PK_SEN PRIMARY KEY ( sensor_id )
);