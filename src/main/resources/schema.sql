create table car
(
   id UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
   make VARCHAR(50) NOT NULL,
   model VARCHAR(50) NOT NULL,
   year SMALLINT NOT NULL,
   generation VARCHAR(50),
   color VARCHAR(25),
   country VARCHAR(50)
);