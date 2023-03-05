CREATE TABLE IF NOT EXISTS SPOT_ON (
    id uuid DEFAULT random_uuid() PRIMARY KEY ,
    url varchar(255),
    name varchar(255),
    intro varchar(50)
)