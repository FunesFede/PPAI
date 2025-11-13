-- ========================================
-- ESQUEMA DE BASE DE DATOS SQLITE - PPAI
-- Sistema de Gestión de Eventos Sísmicos
-- ========================================

-- ========================================
-- TABLAS DE CATÁLOGO / CLASIFICACIÓN
-- ========================================

-- Tabla: estado (usa SINGLE_TABLE inheritance)
CREATE TABLE IF NOT EXISTS estado (
    estado_id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo_estado VARCHAR(50) NOT NULL,  -- Discriminator column
    nombre_estado VARCHAR(100)
);

-- Tabla: rol
CREATE TABLE IF NOT EXISTS rol (
    rol_id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion_rol VARCHAR(255),
    nombre VARCHAR(100) NOT NULL
);

-- Tabla: alcance_sismo
CREATE TABLE IF NOT EXISTS alcance_sismo (
    alcance_sismo_id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR(255),
    nombre VARCHAR(100) NOT NULL
);

-- Tabla: clasificacion_sismo
CREATE TABLE IF NOT EXISTS clasificacion_sismo (
    clasificacion_sismo_id INTEGER PRIMARY KEY AUTOINCREMENT,
    km_profundidad_desde REAL,
    km_profundidad_hasta REAL,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla: origen_de_generacion
CREATE TABLE IF NOT EXISTS origen_de_generacion (
    origen_de_generacion_id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR(255),
    nombre VARCHAR(100) NOT NULL
);

-- Tabla: magnitud_ritcher
CREATE TABLE IF NOT EXISTS magnitud_ritcher (
    magnitud_ritcher_id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion_magnitud VARCHAR(255),
    numero REAL NOT NULL
);

-- Tabla: motivo_tipo
CREATE TABLE IF NOT EXISTS motivo_tipo (
    motivo_tipo_id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR(255)
);

-- Tabla: tipo_de_dato
CREATE TABLE IF NOT EXISTS tipo_de_dato (
    tipo_de_dato_id INTEGER PRIMARY KEY AUTOINCREMENT,
    denominacion VARCHAR(100),
    nombre_unidad_medida VARCHAR(50),
    valor_umbral REAL
);

-- Tabla: fabricante
CREATE TABLE IF NOT EXISTS fabricante (
    fabricante_id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL,
    razon_social VARCHAR(255)
);

-- ========================================
-- TABLAS DE EMPLEADOS Y USUARIOS
-- ========================================

-- Tabla: empleado
CREATE TABLE IF NOT EXISTS empleado (
    empleado_id INTEGER PRIMARY KEY AUTOINCREMENT,
    apellido VARCHAR(100),
    nombre VARCHAR(100),
    mail VARCHAR(100),
    telefono VARCHAR(20),
    rol_id INTEGER,
    FOREIGN KEY (rol_id) REFERENCES rol(rol_id)
);

-- Tabla: permiso
CREATE TABLE IF NOT EXISTS permiso (
    permiso_id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR(255),
    nombre VARCHAR(100) NOT NULL
);

-- Tabla: perfil
CREATE TABLE IF NOT EXISTS perfil (
    perfil_id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR(255),
    nombre VARCHAR(100) NOT NULL
);

-- ========================================
-- TABLAS INTERMEDIAS (MANY-TO-MANY)
-- ========================================

-- Tabla intermedia: usuario_perfil (Usuario <-> Perfil)
CREATE TABLE IF NOT EXISTS usuario_perfil (
    usuario_id INTEGER NOT NULL,
    perfil_id INTEGER NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id) ON DELETE CASCADE,
    FOREIGN KEY (perfil_id) REFERENCES perfil(perfil_id) ON DELETE CASCADE
);

-- Tabla intermedia: perfil_permiso (Perfil <-> Permiso)
CREATE TABLE IF NOT EXISTS perfil_permiso (
    perfil_id INTEGER NOT NULL,
    permiso_id INTEGER NOT NULL,
    PRIMARY KEY (perfil_id, permiso_id),
    FOREIGN KEY (perfil_id) REFERENCES perfil(perfil_id) ON DELETE CASCADE,
    FOREIGN KEY (permiso_id) REFERENCES permiso(permiso_id) ON DELETE CASCADE
);

-- ========================================
-- TABLAS DE ESTACIONES Y SUSCRIPCIONES
-- ========================================

-- Tabla: estacion_sismologica
CREATE TABLE IF NOT EXISTS estacion_sismologica (
    estacion_sismologica_id INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo_estacion VARCHAR(50) NOT NULL,
    documento_certificacion_adquisicion VARCHAR(255),
    fecha_solicitud_certificacion TIMESTAMP,
    longitud REAL,
    latitud REAL,
    nombre VARCHAR(100) NOT NULL,
    nro_certificacion_adquisicion VARCHAR(100)
);

-- Tabla: suscripcion
CREATE TABLE IF NOT EXISTS suscripcion (
    suscripcion_id INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha_hora_inicio TIMESTAMP NOT NULL,
    fecha_hora_fin TIMESTAMP,
    estacion_sismologica_id INTEGER,
    FOREIGN KEY (estacion_sismologica_id) REFERENCES estacion_sismologica(estacion_sismologica_id)
);

-- Tabla: usuario
CREATE TABLE IF NOT EXISTS usuario (
    usuario_id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    suscripcion_id INTEGER,
    empleado_id INTEGER,
    FOREIGN KEY (suscripcion_id) REFERENCES suscripcion(suscripcion_id),
    FOREIGN KEY (empleado_id) REFERENCES empleado(empleado_id)
);

-- Tabla: sesion
CREATE TABLE IF NOT EXISTS sesion (
    sesion_id INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha_hora_inicio TIMESTAMP NOT NULL,
    fecha_hora_fin TIMESTAMP,
    usuario_id INTEGER,
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id)
);

-- ========================================
-- TABLAS DE SISMÓGRAFOS Y MODELOS
-- ========================================

-- Tabla: modelo_sismografo
CREATE TABLE IF NOT EXISTS modelo_sismografo (
    modelo_sismografo_id INTEGER PRIMARY KEY AUTOINCREMENT,
    caracteristicas TEXT,
    nombre_modelo VARCHAR(100) NOT NULL,
    fabricante_id INTEGER,
    FOREIGN KEY (fabricante_id) REFERENCES fabricante(fabricante_id)
);

-- Tabla: motivo_fuera_servicio
CREATE TABLE IF NOT EXISTS motivo_fuera_servicio (
    motivo_fuera_servicio_id INTEGER PRIMARY KEY AUTOINCREMENT,
    comentario TEXT,
    fecha_hora_inicio TIMESTAMP,
    fecha_hora_fin TIMESTAMP,
    motivo_tipo_id INTEGER,
    sismografo_id INTEGER,
    FOREIGN KEY (motivo_tipo_id) REFERENCES motivo_tipo(motivo_tipo_id),
    FOREIGN KEY (sismografo_id) REFERENCES sismografo(sismografo_id)
);

-- Tabla: sismografo
CREATE TABLE IF NOT EXISTS sismografo (
    sismografo_id INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha_adquisicion TIMESTAMP,
    identificador_sismografo VARCHAR(50) NOT NULL,
    nro_serie VARCHAR(100),
    estacion_sismologica_id INTEGER,
    modelo_sismografo_id INTEGER,
    FOREIGN KEY (estacion_sismologica_id) REFERENCES estacion_sismologica(estacion_sismologica_id),
    FOREIGN KEY (modelo_sismografo_id) REFERENCES modelo_sismografo(modelo_sismografo_id)
);

-- Tabla: cambio_estado (para sismógrafos y eventos)
CREATE TABLE IF NOT EXISTS cambio_estado (
    cambio_estado_id INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha_hora_fin TIMESTAMP,
    fecha_hora_inicio TIMESTAMP NOT NULL,
    estado_id INTEGER,
    empleado_id INTEGER,
    sismografo_id INTEGER,
    evento_sismico_id INTEGER,
    FOREIGN KEY (estado_id) REFERENCES estado(estado_id),
    FOREIGN KEY (empleado_id) REFERENCES empleado(empleado_id),
    FOREIGN KEY (sismografo_id) REFERENCES sismografo(sismografo_id),
    FOREIGN KEY (evento_sismico_id) REFERENCES evento_sismico(evento_sismico_id)
);

-- ========================================
-- TABLAS DE EVENTOS SÍSMICOS
-- ========================================

-- Tabla: evento_sismico
CREATE TABLE IF NOT EXISTS evento_sismico (
    evento_sismico_id INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha_hora_fin TIMESTAMP,
    fecha_hora_ocurrencia TIMESTAMP NOT NULL,
    latitud_epicentro REAL NOT NULL,
    latitud_hipocentro REAL NOT NULL,
    longitud_epicentro REAL NOT NULL,
    longitud_hipocentro REAL NOT NULL,
    valor_magnitud REAL NOT NULL,
    clasificacion_sismo_id INTEGER,
    magnitud_ritcher_id INTEGER,
    origen_de_generacion_id INTEGER,
    alcance_sismo_id INTEGER,
    estado_id INTEGER,
    empleado_id INTEGER,
    FOREIGN KEY (clasificacion_sismo_id) REFERENCES clasificacion_sismo(clasificacion_sismo_id),
    FOREIGN KEY (magnitud_ritcher_id) REFERENCES magnitud_ritcher(magnitud_ritcher_id),
    FOREIGN KEY (origen_de_generacion_id) REFERENCES origen_de_generacion(origen_de_generacion_id),
    FOREIGN KEY (alcance_sismo_id) REFERENCES alcance_sismo(alcance_sismo_id),
    FOREIGN KEY (estado_id) REFERENCES estado(estado_id),
    FOREIGN KEY (empleado_id) REFERENCES empleado(empleado_id)
);

-- ========================================
-- TABLAS DE SERIES TEMPORALES Y MUESTRAS
-- ========================================

-- Tabla: serie_temporal
CREATE TABLE IF NOT EXISTS serie_temporal (
    serie_temporal_id INTEGER PRIMARY KEY AUTOINCREMENT,
    condicion_alarma VARCHAR(100),
    fecha_hora_inicio_registro_muestras TIMESTAMP,
    fecha_hora_registro TIMESTAMP,
    frecuencia_muestreo REAL,
    sismografo_id INTEGER,
    evento_sismico_id INTEGER,
    FOREIGN KEY (sismografo_id) REFERENCES sismografo(sismografo_id),
    FOREIGN KEY (evento_sismico_id) REFERENCES evento_sismico(evento_sismico_id)
);

-- Tabla: muestra_sismica
CREATE TABLE IF NOT EXISTS muestra_sismica (
    muestra_sismica_id INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha_hora_muestra TIMESTAMP NOT NULL,
    serie_temporal_id INTEGER,
    FOREIGN KEY (serie_temporal_id) REFERENCES serie_temporal(serie_temporal_id)
);

-- Tabla: detalle_muestra_sismica
CREATE TABLE IF NOT EXISTS detalle_muestra_sismica (
    detalle_muestra_sismica_id INTEGER PRIMARY KEY AUTOINCREMENT,
    valor REAL NOT NULL,
    tipo_de_dato_id INTEGER,
    muestra_sismica_id INTEGER,
    FOREIGN KEY (tipo_de_dato_id) REFERENCES tipo_de_dato(tipo_de_dato_id),
    FOREIGN KEY (muestra_sismica_id) REFERENCES muestra_sismica(muestra_sismica_id)
);

-- ========================================
-- ÍNDICES PARA MEJORAR RENDIMIENTO
-- ========================================

CREATE INDEX IF NOT EXISTS idx_evento_sismico_fecha ON evento_sismico(fecha_hora_ocurrencia);
CREATE INDEX IF NOT EXISTS idx_evento_sismico_estado ON evento_sismico(estado_id);
CREATE INDEX IF NOT EXISTS idx_cambio_estado_evento ON cambio_estado(evento_sismico_id);
CREATE INDEX IF NOT EXISTS idx_cambio_estado_sismografo ON cambio_estado(sismografo_id);
CREATE INDEX IF NOT EXISTS idx_usuario_nombre ON usuario(nombre_usuario);
CREATE INDEX IF NOT EXISTS idx_serie_temporal_sismografo ON serie_temporal(sismografo_id);
CREATE INDEX IF NOT EXISTS idx_muestra_sismica_serie ON muestra_sismica(serie_temporal_id);
CREATE INDEX IF NOT EXISTS idx_usuario_perfil_usuario ON usuario_perfil(usuario_id);
CREATE INDEX IF NOT EXISTS idx_usuario_perfil_perfil ON usuario_perfil(perfil_id);
CREATE INDEX IF NOT EXISTS idx_perfil_permiso_perfil ON perfil_permiso(perfil_id);
CREATE INDEX IF NOT EXISTS idx_perfil_permiso_permiso ON perfil_permiso(permiso_id);