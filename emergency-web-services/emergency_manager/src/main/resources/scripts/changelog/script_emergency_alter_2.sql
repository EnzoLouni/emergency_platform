ALTER TABLE ACCIDENT
DROP COLUMN accident_coordinates,
ADD COLUMN accident_coordinates point;

ALTER TABLE EQUIPMENT
DROP COLUMN quality,
ADD COLUMN equipment_quality int,
DROP COLUMN features,
ADD COLUMN equipment_features char(50);

UPDATE EQUIPMENT
SET equipment_quality = 2, equipment_features = 'MOUSSE';
