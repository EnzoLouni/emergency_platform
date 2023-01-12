update station
set station_name = 'Casernement Villeurbane',
    station_coordinates = point(45.77, 4.896)
where station_id = 4;

alter table accident alter column team_id drop not null;

alter table accident alter column accident_status type varchar(30);
alter table agent alter column agent_name type varchar(30);
alter table equipment alter column equipment_type type varchar(30);
alter table equipment alter column equipment_features type varchar(30);
alter table station alter column station_name type varchar(30);