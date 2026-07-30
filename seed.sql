-- ==========================================
-- SEED DE PRUEBA
--
-- Contraseña para TODOS los usuarios:
-- pass123
--
-- BCrypt:
-- $2a$12$hpdqCbP5Mr0YJc03F4HyneLjdvFBdoKwaoZWAqjSXpp85BLntVTdK
-- ==========================================

-------------------------
-- ROLES
-------------------------

INSERT INTO roles (id, role) VALUES
                                 (1,'ROLE_ADMINISTRADOR'),
                                 (2,'ROLE_ESTUDIANTE'),
                                 (3,'ROLE_PROFESOR'),
                                 (4,'ROLE_VIGILANTE');

-------------------------
-- PARQUEADEROS
-------------------------

INSERT INTO parqueaderos (id_parqueadero,nombre,imagen) VALUES
    (1,'Parqueadero Principal','principal.jpg');

-------------------------
-- ESTADOS ESPACIO
-------------------------

INSERT INTO estados_de_espacio (id_estado,nombre) VALUES
                                                      (1,'DISPONIBLE'),
                                                      (2,'OCUPADO'),
                                                      (3,'RESERVADO');

-------------------------
-- ESTADOS RESERVA
-------------------------

INSERT INTO estados_de_reserva (id_estado,nombre) VALUES
                                                      (1,'PENDIENTE'),
                                                      (2,'ACTIVA'),
                                                      (3,'FINALIZADA'),
                                                      (4,'CANCELADA');

-------------------------
-- USUARIOS
-------------------------

INSERT INTO usuarios
(id_usuario,nombre,apellido,codigo,correo,contrasenia,id_parqueadero)
VALUES
    (1,'Admin','Sistema','ADMIN001','admin@demo.com',
     '$2a$12$hpdqCbP5Mr0YJc03F4HyneLjdvFBdoKwaoZWAqjSXpp85BLntVTdK',1),

    (2,'Juan','Perez','2024001','juan@demo.com',
     '$2a$12$hpdqCbP5Mr0YJc03F4HyneLjdvFBdoKwaoZWAqjSXpp85BLntVTdK',1),

    (3,'Laura','Gomez','PROF001','laura@demo.com',
     '$2a$12$hpdqCbP5Mr0YJc03F4HyneLjdvFBdoKwaoZWAqjSXpp85BLntVTdK',1),

    (4,'Carlos','Ruiz','VIG001','carlos@demo.com',
     '$2a$12$hpdqCbP5Mr0YJc03F4HyneLjdvFBdoKwaoZWAqjSXpp85BLntVTdK',1);

-------------------------
-- RELACIÓN USUARIO - ROL
-------------------------

INSERT INTO usuarios_roles(usuario_id_usuario,roles_id) VALUES
                                                            (1,1),
                                                            (2,2),
                                                            (3,3),
                                                            (4,4);

-------------------------
-- ADMINISTRADORES
-------------------------

INSERT INTO administradores(id_administrador)
VALUES (1);

-------------------------
-- VIGILANTES
-------------------------

INSERT INTO vigilantes(id_vigilante)
VALUES (4);

-------------------------
-- ESPACIOS
-------------------------

INSERT INTO espacios
(id_espacio,numero,id_estado,id_parqueadero)
VALUES
    (1,'A-01',1,1),
    (2,'A-02',2,1),
    (3,'A-03',1,1);

-------------------------
-- MOTOS
-------------------------

INSERT INTO motos
(id_moto,descripcion,imagen,modelo,placa,id_parqueadero,id_usuario)
VALUES
    (1,'Moto estudiante','moto1.jpg','NKD','ABC123',1,2),
    (2,'Moto profesor','moto2.jpg','XTZ150','XYZ987',1,3);

-------------------------
-- USUARIO - MOTO
-------------------------

INSERT INTO usuarios_motos(usuario_id_usuario,motos_id_moto)
VALUES
    (2,1),
    (3,2);

-------------------------
-- RESERVAS
-------------------------

INSERT INTO reservas
(id_reserva,fecha,id_espacio,id_estado,id_usuario)
VALUES
    (1,NOW(),1,2,2),
    (2,NOW(),3,1,3);

-------------------------
-- REGISTROS
-------------------------

INSERT INTO registros
(id_registro,fecha,hora_entrada,hora_salida,id_espacio,id_moto,id_vigilante)
VALUES
    (1,CURRENT_DATE,'08:00','12:00',2,2,4),
    (2,CURRENT_DATE,'13:00',NULL,1,1,4);

-------------------------
-- SANCIONES
-------------------------

INSERT INTO sanciones
(id_sancion,descripcion,fecha,id_sancionado,id_sancionador)
VALUES
    (1,'Parqueo en zona restringida',CURRENT_DATE,2,1);

-- ==========================================
-- SINCRONIZAR SECUENCIAS
-- ==========================================

SELECT setval(pg_get_serial_sequence('roles','id'), COALESCE((SELECT MAX(id) FROM roles),1), true);
SELECT setval(pg_get_serial_sequence('parqueaderos','id_parqueadero'), COALESCE((SELECT MAX(id_parqueadero) FROM parqueaderos),1), true);
SELECT setval(pg_get_serial_sequence('estados_de_espacio','id_estado'), COALESCE((SELECT MAX(id_estado) FROM estados_de_espacio),1), true);
SELECT setval(pg_get_serial_sequence('estados_de_reserva','id_estado'), COALESCE((SELECT MAX(id_estado) FROM estados_de_reserva),1), true);
SELECT setval(pg_get_serial_sequence('usuarios','id_usuario'), COALESCE((SELECT MAX(id_usuario) FROM usuarios),1), true);
SELECT setval(pg_get_serial_sequence('espacios','id_espacio'), COALESCE((SELECT MAX(id_espacio) FROM espacios),1), true);
SELECT setval(pg_get_serial_sequence('motos','id_moto'), COALESCE((SELECT MAX(id_moto) FROM motos),1), true);
SELECT setval(pg_get_serial_sequence('reservas','id_reserva'), COALESCE((SELECT MAX(id_reserva) FROM reservas),1), true);
SELECT setval(pg_get_serial_sequence('registros','id_registro'), COALESCE((SELECT MAX(id_registro) FROM registros),1), true);
SELECT setval(pg_get_serial_sequence('sanciones','id_sancion'), COALESCE((SELECT MAX(id_sancion) FROM sanciones),1), true);
