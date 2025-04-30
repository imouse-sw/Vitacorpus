CREATE DATABASE IF NOT EXISTS vitacorpus;

USE vitacorpus;

# ----------------------------
# CREACIÓN DE TABLAS
# ----------------------------

# ----------------------------
# Catálogo CAT_OBJETIVO
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.CAT_OBJETIVO
(
	idObjetivos INT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

# ----------------------------
# Tabla TBL_USUARIO
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_USUARIO
(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    contrasena VARCHAR(20) NOT NULL,
    fechaRegistro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CAT_OBJETIVO_idObjetivos INT,
    
	FOREIGN KEY (CAT_OBJETIVO_idObjetivos) REFERENCES vitacorpus.CAT_OBJETIVO(idObjetivos)
);

# ----------------------------
# Catálogo CAT_ALIMENTOS_RESTRINGIDOS
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.CAT_ALIMENTOS_RESTRINGIDOS
(
	idAlimento INT PRIMARY KEY AUTO_INCREMENT,
    alimento VARCHAR(30) NOT NULL,
    sustituto1 VARCHAR(30) NOT NULL,
    sustituto2 VARCHAR(30)
);

# ----------------------------
# Tabla TBL_REGISTRO_DATOS
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_REGISTRO_DATOS
(
	idRegistro INT PRIMARY KEY AUTO_INCREMENT,
    edad INT NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    estatura DECIMAL(5,2) NOT NULL,
    fechaActualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    imc DECIMAL(5,2) NOT NULL,
    TBL_USUARIO_idUsuario INT NOT NULL,
    
    FOREIGN KEY (TBL_USUARIO_idUsuario) REFERENCES vitacorpus.TBL_USUARIO(idUsuario)
);

# ----------------------------
# Tabla TBL_RUTINA
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_RUTINA
(
	idRutina INT PRIMARY KEY AUTO_INCREMENT,
    rutina VARCHAR(1000) NOT NULL,
    descripcion VARCHAR(3000) NOT NULL,
    precauciones VARCHAR(1000),
    gradoDificultad INT NOT NULL,
    CAT_OBJETIVO_idObjetivos INT NOT NULL,
    
	FOREIGN KEY (CAT_OBJETIVO_idObjetivos) REFERENCES vitacorpus.CAT_OBJETIVO(idObjetivos)
);

# ----------------------------
# Tabla TBL_DIETA
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_DIETA
(
	idDieta INT PRIMARY KEY AUTO_INCREMENT,
    dieta VARCHAR(45) NOT NULL,
    CAT_OBJETIVO_idObjetivos INT NOT NULL,
    
	FOREIGN KEY (CAT_OBJETIVO_idObjetivos) REFERENCES vitacorpus.CAT_OBJETIVO(idObjetivos)
);

# ----------------------------
# Tabla TBL_EJERCICIO
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_EJERCICIO
(
    idEjercicio INT PRIMARY KEY AUTO_INCREMENT,
    ejercicioLunes VARCHAR(2000) NOT NULL,
    ejercicioMartes VARCHAR(2000) NOT NULL,
    ejercicioMiercoles VARCHAR(2000) NOT NULL,
    ejercicioJueves VARCHAR(2000) NOT NULL,
    ejercicioViernes VARCHAR(2000) NOT NULL,
    ejercicioSabado VARCHAR(2000) NOT NULL,
    ejercicioDomingo VARCHAR(2000) NOT NULL
);


# ----------------------------
# Tabla TBL_COMIDA
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_COMIDA
(
	idComida INT PRIMARY KEY AUTO_INCREMENT,
    comidasLunes VARCHAR(400) NOT NULL,
    comidasMartes VARCHAR(400) NOT NULL,
    comidasMiercoles VARCHAR(400) NOT NULL,
    comidasJueves VARCHAR(400) NOT NULL,
    comidasViernes VARCHAR(400) NOT NULL,
    comidasSabado VARCHAR(400) NOT NULL,
    comidasDomingo VARCHAR(400) NOT NULL
);

# ----------------------------
# CREACIÓN DE TABLAS TRANSITIVAS
# ----------------------------

# ----------------------------
# Relación REL_USUARIO_RESTRICCION
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_USUARIO_RESTRICCION
(
	idRelUsuarioRestriccion INT PRIMARY KEY AUTO_INCREMENT,
	TBL_USUARIO_idUsuario INT NOT NULL,
    CAT_ALIMENTOS_RESTRINGIDOS_idAlimento INT NOT NULL,
    
    FOREIGN KEY (TBL_USUARIO_idUsuario) REFERENCES vitacorpus.TBL_USUARIO(idUsuario),
    FOREIGN KEY (CAT_ALIMENTOS_RESTRINGIDOS_idAlimento) REFERENCES vitacorpus.CAT_ALIMENTOS_RESTRINGIDOS(idAlimento)
);

# ----------------------------
# Relación REL_RUTINA_EJ
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_RUTINA_EJ
(
	idRelRutinaEjercicio INT PRIMARY KEY AUTO_INCREMENT,
	TBL_RUTINA_idRutina INT NOT NULL,
    TBL_EJERCICIO_idEjercicio INT NOT NULL,

    FOREIGN KEY (TBL_RUTINA_idRutina) REFERENCES vitacorpus.TBL_RUTINA(idRutina),
    FOREIGN KEY (TBL_EJERCICIO_idEjercicio) REFERENCES vitacorpus.TBL_EJERCICIO(idEjercicio)
);

# ----------------------------
# Relación REL_DIETA_COMIDA
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_DIETA_COMIDA
(
	idRelDietaComida INT PRIMARY KEY AUTO_INCREMENT,
	TBL_DIETA_idDieta INT NOT NULL,
    TBL_COMIDA_idComida INT NOT NULL,
    
    FOREIGN KEY (TBL_DIETA_idDieta) REFERENCES vitacorpus.TBL_DIETA(idDieta),
    FOREIGN KEY (TBL_COMIDA_idComida) REFERENCES vitacorpus.TBL_COMIDA(idComida)
);

# -----------------------------
# Relación REL_COMIDA_RESTRICCION
# -----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_COMIDA_RESTRICCION
(
	idRelComidaRestriccion INT PRIMARY KEY AUTO_INCREMENT,
    TBL_COMIDA_idComida INT NOT NULL,
    CAT_ALIMENTOS_RESTRINGIDOS_idAlimento INT NOT NULL,
    
    FOREIGN KEY (TBL_COMIDA_idComida) REFERENCES vitacorpus.TBL_COMIDA(idComida),
    FOREIGN KEY (CAT_ALIMENTOS_RESTRINGIDOS_idAlimento) REFERENCES vitacorpus.CAT_ALIMENTOS_RESTRINGIDOS(idAlimento)
);
