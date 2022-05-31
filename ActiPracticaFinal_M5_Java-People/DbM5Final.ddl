CREATE TABLE modulo (
    id_modulo       SERIAL NOT NULL,
    nombre          VARCHAR(50)
);

ALTER TABLE modulo ADD CONSTRAINT modulo_pk PRIMARY KEY ( id_modulo );

CREATE TABLE calificacion (
    id_calificacion            SERIAL NOT NULL,
    numeroevaluacion           INTEGER NOT NULL,
    nota                       REAL NOT NULL,
    id_alumno	               INTEGER NOT NULL,
    id_modulo	               INTEGER NOT NULL
);

ALTER TABLE calificacion ADD CONSTRAINT calificacion_pk PRIMARY KEY ( id_calificacion );

CREATE TABLE alumno (
    id_alumno	      SERIAL NOT NULL,
    rut               VARCHAR(50),
    nombres           VARCHAR(50),
    apellidos	      VARCHAR(50),
    genero            VARCHAR(50),
    fono              VARCHAR(50),
    curso             VARCHAR(50)
);

ALTER TABLE alumno ADD CONSTRAINT alumno_pk PRIMARY KEY ( id_alumno );

ALTER TABLE calificacion
    ADD CONSTRAINT calificacion_modulo_fk FOREIGN KEY ( id_modulo )
        REFERENCES modulo ( id_modulo );

ALTER TABLE calificacion
    ADD CONSTRAINT calificacion_alumno_fk FOREIGN KEY ( id_alumno )
        REFERENCES alumno ( id_alumno );