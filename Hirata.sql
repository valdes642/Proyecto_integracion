DROP DATABASE IF EXISTS hirata_db;
CREATE DATABASE IF NOT EXISTS hirata_db;
USE hirata_db;

DROP TABLE IF EXISTS mantenimiento;
DROP TABLE IF EXISTS camion;
DROP TABLE IF EXISTS conductor;

-- 1. TABLA CONDUCTOR (Ahora con todos los campos que pide Java)
CREATE TABLE conductor (
    id_conductor INT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) NOT NULL UNIQUE,
    nombre_completo VARCHAR(100) NOT NULL, -- Tu código Java guarda aquí el "Nombre"
    apellidos VARCHAR(100) NOT NULL,       -- Nuevo campo
    licencia VARCHAR(50) NOT NULL,         -- Nuevo campo
    telefono VARCHAR(50) NOT NULL          -- Nuevo campo
);

-- 2. TABLA CAMION
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

-- 3. TABLA MANTENIMIENTO
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

-- 4. TABLA USUARIOS
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, 
    rol ENUM('Admin', 'Revision_Conductores', 'Revision_Mantenimiento') NOT NULL
);

-- DATOS DE PRUEBA
-- Conductores (Ahora con nombres, apellidos, licencias y teléfonos reales)
INSERT INTO conductor (rut, nombre_completo, apellidos, licencia, telefono) VALUES 
('12.345.678-9', 'Juan', 'Pérez', 'A5', '+56912345678'),
('11.765.432-1', 'María', 'González', 'A4', '+56987654321'),
('11.222.333-K', 'Carlos', 'Soto', 'A5', '+56911223344'),
('15.666.777-8', 'Ana Luisa', 'Morales', 'A4', '+56955667788'),
('19.444.555-0', 'Roberto', 'Jara', 'A5', '+56999887766'),
('16.111.222-3', 'Trinidad', 'Torres', 'A5', '+56944332211'),
('17.888.999-4', 'Raimundo', 'Vargas', 'A4', '+56955443322'),
('18.555.666-7', 'Fernanda', 'López', 'A5', '+56977665544'),
('20.333.444-5', 'Valentina', 'Muñoz', 'A4', '+56922334455'),
('14.999.000-1', 'Pedro', 'Sepúlveda', 'A5', '+56988990011');

-- Camiones
INSERT INTO camion (patente, marca, modelo, anio, kilometraje, id_conductor) VALUES 
('XY-ZZ-99', 'Scania', 'R500', 2021, 12000, 3),
('KJ-PT-45', 'Freightliner', 'Cascadia', 2023, 500, 4),
('TR-QW-11', 'Isuzu', 'Forward', 2019, 45600, 5),
('LL-PP-22', 'Volvo', 'VM 330', 2022, 2100, 1),
('AB-CD-12', 'Mercedes-Benz', 'Actros', 2020, 85000, 6),
('FG-HI-34', 'Scania', 'G410', 2022, 32000, 7),
('JK-LM-56', 'Volvo', 'FH16', 2024, 1500, 8),
('NP-QR-78', 'Ford', 'Cargo 1723', 2018, 120000, 9),
('ST-UV-90', 'Kenworth', 'T680', 2021, 54000, 10);

-- Registros de mantenimiento
INSERT INTO mantenimiento (id_camion, fecha_mantenimiento, tipo_mantenimiento, descripcion, kilometraje_al_momento) VALUES 
(1, '2025-10-15', 'Preventivo', 'Cambio de aceite y filtros', 4500),
(3, '2026-01-20', 'Correctivo', 'Reparación de frenos aire', 11800),
(2, '2025-11-05', 'Correctivo', 'Cambio de neumáticos traseros por desgaste', 550),
(4, '2026-02-15', 'Preventivo', 'Engrase general y cambio de plumillas', 2150),
(5, '2026-03-10', 'Preventivo', 'Revisión técnica anual y alineación', 85000),
(6, '2026-04-02', 'Preventivo', 'Cambio de aceite de transmisión', 32500),
(7, '2025-12-20', 'Correctivo', 'Reemplazo de batería de arranque', 1500),
(9, '2026-01-10', 'Correctivo', 'Reparación de sistema de refrigeración', 54000);

-- DATOS DE PRUEBA DE USUARIOS
INSERT INTO usuarios (username, password, rol) VALUES 
-- Usuarios originales de prueba básica
('admin', '1234', 'Admin'),
('conductor_rev', '1234', 'Revision_Conductores'),
('manto_rev', '1234', 'Revision_Mantenimiento'),

-- Nuevos Administradores
('JuanAdmin', 'Admin948372', 'Admin'),
('MariaAdmin', 'Admin158263', 'Admin'),
('CarlosAdmin', 'Admin739182', 'Admin'),

-- Nuevos perfiles de Revisión de Conductores
('PedroConductores', 'Conductor485739', 'Revision_Conductores'),
('AnaConductores', 'Conductor928374', 'Revision_Conductores'),
('LuisConductores', 'Conductor162738', 'Revision_Conductores'),

-- Nuevos perfiles de Revisión de Mantenimiento
('SofiaMantenimiento', 'Manto573829', 'Revision_Mantenimiento'),
('DiegoMantenimiento', 'Manto819263', 'Revision_Mantenimiento'),
('ElenaMantenimiento', 'Manto354682', 'Revision_Mantenimiento');

select * from usuarios
