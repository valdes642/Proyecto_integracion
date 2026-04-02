
CREATE DATABASE IF NOT EXISTS hirata_db;
USE hirata_db;

CREATE TABLE conductor (
    id_conductor INT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) NOT NULL UNIQUE,
    nombre_completo VARCHAR(100) NOT NULL
);

CREATE TABLE camion (
    id_camion INT AUTO_INCREMENT PRIMARY KEY,
    patente VARCHAR(10) NOT NULL UNIQUE,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    kilometraje INT DEFAULT 0,
    id_conductor INT,
    FOREIGN KEY (id_conductor) REFERENCES conductor(id_conductor)
        ON DELETE SET NULL 
        ON UPDATE CASCADE
);

CREATE TABLE mantenimiento (
    id_mantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_camion INT NOT NULL,
    fecha_mantenimiento DATE NOT NULL,
    tipo_mantenimiento VARCHAR(50) NOT NULL,
    descripcion TEXT,
    kilometraje_al_momento INT NOT NULL,
    FOREIGN KEY (id_camion) REFERENCES camion(id_camion)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO conductor (rut, nombre_completo) VALUES 
('12345678-9', 'Juan Pérez'),
('98765432-1', 'María González');

INSERT INTO camion (patente, marca, modelo, anio, kilometraje, id_conductor) VALUES 
('AB-CD-12', 'Volvo', 'FH16', 2020, 4800, 1),
('EF-GH-34', 'Mercedes-Benz', 'Actros', 2022, 1500, 2);