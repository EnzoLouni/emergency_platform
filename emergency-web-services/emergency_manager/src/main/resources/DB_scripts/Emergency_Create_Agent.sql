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