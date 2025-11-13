-- ========================================
-- DATOS INICIALES PARA PPAI
-- Sistema de Gestión de Eventos Sísmicos
-- ========================================

-- ========================================
-- 1. ESTADOS
-- ========================================
INSERT INTO estado (tipo_estado, nombre_estado) VALUES 
    ('AUTO_DETECTADO', 'Auto Detectado'),
    ('ANULADO', 'Anulado'),
    ('AUTO_CONFIRMADO', 'Auto Confirmado'),
    ('BLOQUEADO_EN_REVISION', 'Bloqueado En Revision'),
    ('CERRADO', 'Cerrado'),
    ('CONFIRMADO', 'Confirmado'),
    ('DERIVADO_EXPERTO', 'Derivado Experto'),
    ('PENDIENTE_DE_CIERRE', 'Pendiente De Cierre'),
    ('PENDIENTE_DE_REVISION', 'Pendiente De Revision'),
    ('RECHAZADO', 'Rechazado');

-- ========================================
-- 2. ROLES
-- ========================================
INSERT INTO rol (descripcion_rol, nombre) VALUES 
    ('Analista encargado de revisar eventos sísmicos', 'Analista Sismos'),
    ('Experto sismólogo', 'Experto'),
    ('Administrador del sistema', 'Administrador');

-- ========================================
-- 3. CLASIFICACIÓN DE SISMOS
-- ========================================
INSERT INTO clasificacion_sismo (km_profundidad_desde, km_profundidad_hasta, nombre) VALUES 
    (0.0, 60.0, 'Superficial'),
    (61.0, 300.0, 'Intermedio'),
    (301.0, 700.0, 'Profundo');

-- ========================================
-- 4. ALCANCE DE SISMOS
-- ========================================
INSERT INTO alcance_sismo (descripcion, nombre) VALUES 
    ('Sismo detectado en una región local', 'Local'),
    ('Sismo detectado a nivel regional', 'Regional'),
    ('Sismo detectado a nivel nacional', 'Nacional'),
    ('Sismo detectado a nivel internacional', 'Internacional');

-- ========================================
-- 5. ORIGEN DE GENERACIÓN
-- ========================================
INSERT INTO origen_de_generacion (descripcion, nombre) VALUES 
    ('Sismo generado en la interplaca', 'Interplaca'),
    ('Sismo generado por actividad humana', 'Inducido'),
    ('Sismo generado en la intraplaca', 'Intraplaca');

-- ========================================
-- 6. MAGNITUD RITCHER
-- ========================================
INSERT INTO magnitud_ritcher (descripcion_magnitud, numero) VALUES 
    ('Micro - Generalmente no se siente', 1.0),
    ('Menor - Raramente se siente', 2.0),
    ('Menor - A menudo se siente, pero raramente causa daño', 3.0),
    ('Ligero - Objetos colgantes se balancean', 4.0),
    ('Moderado - Puede causar daños mayores', 5.0),
    ('Fuerte - Puede ser destructivo', 6.0),
    ('Mayor - Puede causar daños graves', 7.0),
    ('Gran terremoto - Puede causar daños serios', 8.0),
    ('Gran terremoto - Devastador en áreas muy extensas', 9.0);

-- ========================================
-- 7. TIPOS DE DATO
-- ========================================
INSERT INTO tipo_de_dato (denominacion, nombre_unidad_medida, valor_umbral) VALUES 
    ('Aceleración', 'm/s²', 0.5),
    ('Velocidad', 'm/s', 0.3),
    ('Desplazamiento', 'cm', 0.1),
    ('Frecuencia', 'Hz', 10.0),
    ('Amplitud', 'mm', 5.0);

-- ========================================
-- 8. MOTIVO TIPO
-- ========================================
INSERT INTO motivo_tipo (descripcion) VALUES 
    ('Mantenimiento programado'),
    ('Falla técnica'),
    ('Condiciones climáticas adversas'),
    ('Falta de energía');

-- ========================================
-- 9. FABRICANTES
-- ========================================
INSERT INTO fabricante (nombre, razon_social) VALUES
    ('Kinemetrics', 'Kinemetrics Inc.'),
    ('Güralp', 'Güralp Systems Ltd.'),
    ('Nanometrics', 'Nanometrics Inc.');

-- ========================================
-- 10. MODELOS DE SISMÓGRAFO
-- ========================================
INSERT INTO modelo_sismografo (caracteristicas, nombre_modelo, fabricante_id) VALUES
    ('Sismógrafo de banda ancha', 'Episensor ES-T', 1);
INSERT INTO modelo_sismografo (caracteristicas, nombre_modelo, fabricante_id) VALUES
    ('Sismógrafo de corto periodo', 'CMG-40T', 2);
INSERT INTO modelo_sismografo (caracteristicas, nombre_modelo, fabricante_id) VALUES
    ('Sismógrafo de alta precisión', 'Trillium 120QA', 3);

-- ========================================
-- 11. EMPLEADOS
-- ========================================
INSERT INTO empleado (apellido, nombre, mail, telefono, rol_id) VALUES
    ('González', 'María', 'maria.gonzalez@sismos.gov', '261-4567890', 1),
    ('Rodríguez', 'Juan', 'juan.rodriguez@sismos.gov', '261-4567891', 1),
    ('Martínez', 'Carlos', 'carlos.martinez@sismos.gov', '261-4567892', 2),
    ('López', 'Ana', 'ana.lopez@sismos.gov', '261-4567893', 3);

-- ========================================
-- 12. PERMISOS
-- ========================================
INSERT INTO permiso (descripcion, nombre) VALUES
    ('Revisar eventos sísmicos', 'Revisar Eventos'),
    ('Confirmar eventos sísmicos', 'Confirmar Eventos'),
    ('Anular eventos sísmicos', 'Anular Eventos'),
    ('Administrar sistema', 'Administrar');

-- ========================================
-- 13. PERFILES
-- ========================================
INSERT INTO perfil (descripcion, nombre) VALUES
    ('Perfil de analista con permisos de revisión', 'Perfil Analista'),
    ('Perfil de experto con permisos extendidos', 'Perfil Experto'),
    ('Perfil de administrador del sistema', 'Perfil Admin');

-- ========================================
-- 14. RELACIÓN PERFIL-PERMISO (Tabla intermedia)
-- ========================================
-- Perfil Analista (1) tiene permisos: Revisar(1), Confirmar(2)
INSERT INTO perfil_permiso (perfil_id, permiso_id) VALUES (1, 1);
INSERT INTO perfil_permiso (perfil_id, permiso_id) VALUES (1, 2);

-- Perfil Experto (2) tiene permisos: Revisar(1), Confirmar(2), Anular(3)
INSERT INTO perfil_permiso (perfil_id, permiso_id) VALUES (2, 1);
INSERT INTO perfil_permiso (perfil_id, permiso_id) VALUES (2, 2);
INSERT INTO perfil_permiso (perfil_id, permiso_id) VALUES (2, 3);

-- Perfil Admin (3) tiene todos los permisos
INSERT INTO perfil_permiso (perfil_id, permiso_id) VALUES (3, 1);
INSERT INTO perfil_permiso (perfil_id, permiso_id) VALUES (3, 2);
INSERT INTO perfil_permiso (perfil_id, permiso_id) VALUES (3, 3);
INSERT INTO perfil_permiso (perfil_id, permiso_id) VALUES (3, 4);

-- ========================================
-- 15. USUARIOS
-- ========================================
INSERT INTO usuario (nombre_usuario, contrasena, empleado_id) VALUES
    ('mgonzalez', 'pass123', 1),
    ('jrodriguez', 'pass123', 2),
    ('cmartinez', 'pass123', 3),
    ('alopez', 'pass123', 4);

-- ========================================
-- 16. RELACIÓN USUARIO-PERFIL (Tabla intermedia)
-- ========================================
-- María González (1) tiene Perfil Analista (1)
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (1, 1);

-- Juan Rodríguez (2) tiene Perfil Analista (1)
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (2, 1);

-- Carlos Martínez (3) tiene Perfil Experto (2)
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (3, 2);

-- Ana López (4) tiene Perfil Admin (3)
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (4, 3);

-- ========================================
-- 17. SESIONES
-- ========================================
INSERT INTO sesion (fecha_hora_inicio, fecha_hora_fin, usuario_id) VALUES
    ('2024-11-13 08:00:00.000', NULL, 1);

-- ========================================
-- 18. ESTACIONES SISMOLÓGICAS
-- ========================================
INSERT INTO estacion_sismologica (codigo_estacion, nombre, longitud, latitud) VALUES
    ('PLATA', 'La Plata', -57.9536, -34.9205),
    ('MENZU', 'Mendoza', -68.8458, -32.8895),
    ('CHOCA', 'Chocón', -68.7500, -39.2667),
    ('TRELE', 'Trelew', -65.3050, -43.2489),
    ('USHUA', 'Ushuaia', -68.3029, -54.8019);

-- ========================================
-- 19. SISMÓGRAFOS (con timestamps completos)
-- ========================================
INSERT INTO sismografo (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id, modelo_sismografo_id) VALUES
    ('PLATA-S1', 'SN-2020-001', '2020-01-15 10:00:00.000', 1, 1);
INSERT INTO sismografo (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id, modelo_sismografo_id) VALUES
    ('PLATA-S2', 'SN-2020-002', '2020-01-15 10:30:00.000', 1, 2);
INSERT INTO sismografo (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id, modelo_sismografo_id) VALUES
    ('CHOCA-S1', 'SN-2019-001', '2019-06-20 11:00:00.000', 3, 3);
INSERT INTO sismografo (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id, modelo_sismografo_id) VALUES
    ('MENZU-S1', 'SN-2021-001', '2021-03-10 09:00:00.000', 2, 1);
INSERT INTO sismografo (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id, modelo_sismografo_id) VALUES
    ('MENZU-S2', 'SN-2021-002', '2021-03-10 09:30:00.000', 2, 2);
INSERT INTO sismografo (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id, modelo_sismografo_id) VALUES
    ('TRELE-S1', 'SN-2018-001', '2018-11-05 12:00:00.000', 4, 3);
INSERT INTO sismografo (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id, modelo_sismografo_id) VALUES
    ('USHUA-S1', 'SN-2022-001', '2022-07-22 08:00:00.000', 5, 1);

-- ========================================
-- 20. EVENTOS SÍSMICOS AUTO-DETECTADOS
-- ========================================

INSERT INTO evento_sismico (fecha_hora_ocurrencia, latitud_epicentro, latitud_hipocentro, longitud_epicentro, longitud_hipocentro, valor_magnitud, alcance_sismo_id, clasificacion_sismo_id, magnitud_ritcher_id, origen_de_generacion_id, estado_id) VALUES 
    ('2024-11-10 14:23:45.000', -31.5375, -31.5375, -68.5364, -68.5364, 5.0, 2, 1, 5, 1, 1);

INSERT INTO evento_sismico (fecha_hora_ocurrencia, latitud_epicentro, latitud_hipocentro, longitud_epicentro, longitud_hipocentro, valor_magnitud, alcance_sismo_id, clasificacion_sismo_id, magnitud_ritcher_id, origen_de_generacion_id, estado_id) VALUES 
    ('2024-11-11 08:15:30.000', -32.8895, -32.8895, -68.8458, -68.8458, 6.0, 2, 1, 6, 1, 1);

INSERT INTO evento_sismico (fecha_hora_ocurrencia, latitud_epicentro, latitud_hipocentro, longitud_epicentro, longitud_hipocentro, valor_magnitud, alcance_sismo_id, clasificacion_sismo_id, magnitud_ritcher_id, origen_de_generacion_id, estado_id) VALUES 
    ('2024-11-11 19:42:12.000', -24.1858, -24.1858, -65.2995, -65.2995, 4.0, 1, 1, 4, 2, 1);

INSERT INTO evento_sismico (fecha_hora_ocurrencia, latitud_epicentro, latitud_hipocentro, longitud_epicentro, longitud_hipocentro, valor_magnitud, alcance_sismo_id, clasificacion_sismo_id, magnitud_ritcher_id, origen_de_generacion_id, estado_id) VALUES 
    ('2024-11-12 03:28:55.000', -38.7167, -38.7167, -71.5397, -71.5397, 7.0, 4, 3, 7, 1, 1);

INSERT INTO evento_sismico (fecha_hora_ocurrencia, latitud_epicentro, latitud_hipocentro, longitud_epicentro, longitud_hipocentro, valor_magnitud, alcance_sismo_id, clasificacion_sismo_id, magnitud_ritcher_id, origen_de_generacion_id, estado_id) VALUES 
    ('2024-11-12 22:10:18.000', -34.9205, -34.9205, -57.9536, -57.9536, 3.0, 1, 1, 3, 2, 1);

-- ========================================
-- 21. SERIES TEMPORALES
-- ========================================

INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (1, 4);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (1, 5);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (2, 4);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (2, 5);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (3, 1);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (3, 2);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (4, 3);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (4, 4);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (4, 6);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (5, 1);
INSERT INTO serie_temporal (evento_sismico_id, sismografo_id) VALUES (5, 2);

-- ========================================
-- 22. MUESTRAS SÍSMICAS
-- ========================================

INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-10 14:23:45.000', 1);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-10 14:23:46.000', 1);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-10 14:23:47.000', 1);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-10 14:23:45.000', 2);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-10 14:23:46.000', 2);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-10 14:23:47.000', 2);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-11 08:15:30.000', 3);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-11 08:15:31.000', 3);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-11 08:15:32.000', 3);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-11 08:15:30.000', 4);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-11 08:15:31.000', 4);
INSERT INTO muestra_sismica (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-11-11 08:15:32.000', 4);

-- ========================================
-- 23. DETALLES DE MUESTRAS SÍSMICAS
-- ========================================

INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (0.65, 1, 1);
INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (0.42, 2, 1);
INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (0.15, 3, 1);
INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (12.5, 4, 1);
INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (7.8, 5, 1);

INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (0.78, 1, 2);
INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (0.51, 2, 2);
INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (0.18, 3, 2);
INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (13.2, 4, 2);
INSERT INTO detalle_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (8.5, 5, 2);

-- ========================================
-- 24. CAMBIOS DE ESTADO INICIALES
-- ========================================

INSERT INTO cambio_estado (fecha_hora_inicio, estado_id, evento_sismico_id) VALUES ('2024-11-10 14:23:45.000', 1, 1);
INSERT INTO cambio_estado (fecha_hora_inicio, estado_id, evento_sismico_id) VALUES ('2024-11-11 08:15:30.000', 1, 2);
INSERT INTO cambio_estado (fecha_hora_inicio, estado_id, evento_sismico_id) VALUES ('2024-11-11 19:42:12.000', 1, 3);
INSERT INTO cambio_estado (fecha_hora_inicio, estado_id, evento_sismico_id) VALUES ('2024-11-12 03:28:55.000', 1, 4);
INSERT INTO cambio_estado (fecha_hora_inicio, estado_id, evento_sismico_id) VALUES ('2024-11-12 22:10:18.000', 1, 5);