alter table agent
    add column station_id int,
add constraint "fk_ag_stat" foreign key ("station_id") references station ("station_id");

alter table vehicle
    add column station_id int,
add constraint "fk_vec_stat" foreign key ("station_id") references station ("station_id");

alter table team
drop column station_id;