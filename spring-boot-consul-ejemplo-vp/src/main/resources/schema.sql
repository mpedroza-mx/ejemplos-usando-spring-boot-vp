CREATE SCHEMA AUTHORIZATION SA;
CREATE TABLE PAISES (
    id INT GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(250) NOT NULL,
    PRIMARY KEY(id)
);