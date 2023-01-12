truncate table station restart identity CASCADE;

insert into station (station_id, station_name, station_coordinates)
values
    (1, 'Casernement Lyon-Confluence', point(45.756,4.825)),
    (2, 'Casernement Lyon-Corneille', point(45.75,4.846)),
    (3, 'Casernement Lyon-Rochat', point(45.763,4.841)),
    (4, 'Casernement Lyon-Duchère', point(45.791,4.796)),
    (5, 'Casernement Lyon-Croix-Rousse', point(45.784,4.819)),
    (6, 'Casernement Lyon-Gerland', point(45.732,4.827)),
    (7, 'SDMIS Etat Major', point(45.763,4.842));

insert into agent(agent_name, team_id, agent_exhaustion, agent_quality, station_id)
values
    ('John', null, 1, 4, 1),
    ('Joe', null, 2, 6, 1),
    ('Momo', null, 2, 3, 1),
    ('Marie', null, 4, 9, 1),
    ('Sarah', null, 5, 2, 1),
    ('Aymeric', null, 3, 7, 1),
    ('Naël', null, 1, 6, 1),
    ('Mathias', null, 0, 8, 1),

    ('Mohamet', null, 0, 2, 2),
    ('Paulette', null, 1, 2, 2),
    ('Aimée', null, 2, 7, 2),
    ('Hilaire', null, 3, 2, 2),
    ('René', null, 0, 5, 2),
    ('Pierrot', null, 4, 6, 2),
    ('Sebastien', null, 2, 3, 2),
    ('Adolphe', null, 1, 7, 2),
    ('Thomas', null, 0, 3, 2),

    ('Olivier', null, 1, 8, 3),
    ('Ann', null, 1, 2, 3),
    ('Maxime', null, 1, 8, 3),
    ('Gertrude', null, 1, 2, 3),
    ('Emma', null, 1, 8, 3),
    ('Matthieu', null, 1, 2, 3),

    ('Sonya', null, 0, 3, 4),
    ('Thierry', null, 0, 3, 4),
    ('Rébecca', null, 2, 7, 4),
    ('Pierre', null, 3, 6, 4),
    ('Enzo', null, 0, 6, 4),
    ('Cho', null, 1, 7, 4),
    ('Manuella', null, 3, 3, 4),

    ('Caroline', null, 3, 5, 5),
    ('Thony', null, 0, 8, 5),
    ('Haanita', null, 1, 4, 5),
    ('Gordon', null, 4, 3, 5),
    ('Donald', null, 5, 1, 5),
    ('Alexandra', null, 2, 8, 5),
    ('Paolo', null, 2, 3, 5),
    ('Oscar', null, 4, 9, 5),

    ('Diego', null, 5, 7, 6),
    ('André', null, 0, 1, 6),
    ('Marie-Christine', null, 1, 1, 6),
    ('Bruno', null, 2, 4, 6),
    ('Joseph', null, 2, 6, 6),
    ('Enrich', null, 1, 8, 6),

    ('Paulin', null, 1, 3, 7),
    ('Sam', null, 0, 1, 7),
    ('Romane', null, 3, 1, 7),
    ('Olivia', null, 4, 7, 7),
    ('Jeanne', null, 3, 8, 7),
    ('Clément', null, 1, 6, 7),
    ('Sophie', null, 1, 5, 7);

insert into vehicle (vehicle_id, vehicle_capacity, vehicle_tank_capacity, vehicle_isheavyweight , vehicle_quality, station_id )
values
    (1, 4, 120, false, 8, 1),
    (13, 4, 120, false, 10, 1),
    (7, 6, 200, true, 21, 1),
    (8, 8, 280, true, 27, 1),

    (2, 4, 120, false, 10, 2),
    (9, 6, 200, false, 24, 2),
    (10, 10, 300, true, 29, 2),

    (3, 4, 120, false, 10, 3),
    (11, 4, 180, false, 19, 3),
    (12, 4, 200, true, 24, 3),

    (4, 4, 120, false, 11, 4),
    (17, 6, 200, true, 23, 4),
    (14, 6, 200, true, 21, 4),

    (15, 4, 120, false, 10, 5),
    (16, 8, 300, false, 28, 5),

    (5, 4, 120, false, 10, 6),
    (6, 4, 120, false, 9, 6),

    (21, 4, 120, false, 13, 7),
    (18, 4, 120, false, 12, 7),
    (19, 6, 200, false, 23, 7),
    (20, 10, 300, true, 28, 7);


insert into equipment (equipment_type, vehicle_id, equipment_quality, equipment_features)
values
    ('LANCE', 1, 3, 'WATER'),
    ('LANCE', 2, 4, 'WATER'),
    ('LANCE', 3, 3, 'FOAM'),
    ('LANCE', 4, 2, 'WATER'),
    ('LANCE', 7, 9, 'FOAM'),
    ('LANCE', 9, 5, 'FOAM'),
    ('LANCE', 10, 4, 'WATER'),
    ('LANCE', 11, 3, 'FOAM'),
    ('LANCE', 14, 1, 'FOAM'),
    ('LANCE', 16, 2, 'WATER'),
    ('LANCE', 18, 8, 'FOAM'),
    ('LANCE', 19, 7, 'FOAM'),
    ('LANCE', 20, 5, 'FOAM'),
    ('LANCE', 18, 4, 'WATER'),
    ('LANCE', 19, 6, 'FOAM'),
    ('LANCE', 20, 5, 'FOAM'),

    ('AXE', 1, 5, 'FIREAXE'),
    ('AXE', 3, 5, 'FIREAXE'),
    ('AXE', 6, 5, 'FIREAXE'),
    ('AXE', 8, 5, 'FIREAXE'),
    ('AXE', 10, 5, 'FIREAXE'),
    ('AXE', 12, 5, 'FIREAXE'),
    ('AXE', 15, 5, 'FIREAXE'),
    ('AXE', 19, 5, 'FIREAXE'),

    ('LADDER', 1, 5, '15M'),
    ('LADDER', 3, 5, '15M'),
    ('LADDER', 19, 5, '20M'),
    ('LADDER', 19, 5, '25M');


truncate table sensor restart identity;

insert into sensor(sensor_coordinates, sensor_status)
values
    (point(45.73, 4.78), true),
    (point(45.74, 4.78), true),
    (point(45.75, 4.78), true),
    (point(45.76, 4.78), true),
    (point(45.77, 4.78), true),
    (point(45.78, 4.78), true),

    (point(45.73, 4.79), true),
    (point(45.74, 4.79), true),
    (point(45.75, 4.79), true),
    (point(45.76, 4.79), true),
    (point(45.77, 4.79), true),
    (point(45.78, 4.79), true),

    (point(45.73, 4.80), true),
    (point(45.74, 4.80), true),
    (point(45.75, 4.80), true),
    (point(45.76, 4.80), true),
    (point(45.77, 4.80), true),
    (point(45.78, 4.80), true),

    (point(45.73, 4.81), true),
    (point(45.74, 4.81), true),
    (point(45.75, 4.81), true),
    (point(45.76, 4.81), true),
    (point(45.77, 4.81), true),
    (point(45.78, 4.81), true),

    (point(45.73, 4.82), true),
    (point(45.74, 4.82), true),
    (point(45.75, 4.82), true),
    (point(45.76, 4.82), true),
    (point(45.77, 4.82), true),
    (point(45.78, 4.82), true),

    (point(45.73, 4.83), true),
    (point(45.74, 4.83), true),
    (point(45.75, 4.83), true),
    (point(45.76, 4.83), true),
    (point(45.77, 4.83), true),
    (point(45.78, 4.83), true),

    (point(45.73, 4.84), true),
    (point(45.74, 4.84), true),
    (point(45.75, 4.84), true),
    (point(45.76, 4.84), true),
    (point(45.77, 4.84), true),
    (point(45.78, 4.84), true),

    (point(45.73, 4.85), true),
    (point(45.74, 4.85), true),
    (point(45.75, 4.85), true),
    (point(45.76, 4.85), true),
    (point(45.77, 4.85), true),
    (point(45.78, 4.85), true),

    (point(45.73, 4.86), true),
    (point(45.74, 4.86), true),
    (point(45.75, 4.86), true),
    (point(45.76, 4.86), true),
    (point(45.77, 4.86), true),
    (point(45.78, 4.86), true),

    (point(45.73, 4.87), true),
    (point(45.74, 4.87), true),
    (point(45.75, 4.87), true),
    (point(45.76, 4.87), true),
    (point(45.77, 4.87), true),
    (point(45.78, 4.87), true);