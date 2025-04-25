CREATE DATABASE vitacorpus;

USE vitacorpus;

# ----------------------------
# CREACIÓN DE TABLAS
# ----------------------------

# ----------------------------
# Tabla TBL_USUARIO
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_USUARIO
(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    contrasena VARCHAR(20) NOT NULL,
    fechaRegistro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

# ----------------------------
# Catálogo CAT_OBJETIVO
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.CAT_OBJETIVO
(
	idObjetivos INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL
);

# ----------------------------
# Catálogo CAT_PREFERENCIA
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.CAT_PREFERENCIA
(
	idPreferencia INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(45) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

# ----------------------------
# Tabla TBL_CALCULADORA
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_CALCULADORA
(
	idCalculadora INT PRIMARY KEY AUTO_INCREMENT,
    horaDespertar TIME NOT NULL,
    horaDormir TIME NOT NULL
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
    rutina VARCHAR(45) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precauciones VARCHAR(45),
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
    caloriasTotales DECIMAL(6,2),
    restricMonetarias VARCHAR(255),
    CAT_OBJETIVO_idObjetivos INT NOT NULL,
    
		FOREIGN KEY (CAT_OBJETIVO_idObjetivos) REFERENCES vitacorpus.CAT_OBJETIVO(idObjetivos)
);

# ----------------------------
# Tabla TBL_EJERCICIO
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_EJERCICIO
(
	idEjercicio INT PRIMARY KEY AUTO_INCREMENT,
    ejercicio VARCHAR(45) NOT NULL,
    duracion TIME NOT NULL,
    instrucciones VARCHAR(500) NOT NULL,
    urlVideo VARCHAR(255)
);

# ----------------------------
# Tabla TBL_COMIDA
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.TBL_COMIDA
(
	idComida INT PRIMARY KEY AUTO_INCREMENT,
    comida VARCHAR(45) NOT NULL,
    numCalorias DECIMAL(5,2) NOT NULL,
    restricAlimenticias VARCHAR(45)
);

# ----------------------------
# CREACIÓN DE TABLAS TRANSITIVAS
# ----------------------------

# ----------------------------
# Relación REL_USUARIO_REGISTRO
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_USUARIO_REGISTRO
(
	TBL_USUARIO_idUsuario INT NOT NULL,
    TBL_REGISTRO_DATOS_idRegistro INT NOT NULL,
    
    PRIMARY KEY (TBL_USUARIO_idUsuario, TBL_REGISTRO_DATOS_idRegistro),
    FOREIGN KEY (TBL_USUARIO_idUsuario) REFERENCES vitacorpus.TBL_USUARIO(idUsuario),
    FOREIGN KEY (TBL_REGISTRO_DATOS_idRegistro) REFERENCES vitacorpus.TBL_REGISTRO_DATOS(idRegistro)
);

# ----------------------------
# Relación REL_USUARIO_PREF
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_USUARIO_PREF
(
	TBL_USUARIO_idUsuario INT NOT NULL,
    CAT_PREFERENCIA_idPreferencia INT NOT NULL,
    
	PRIMARY KEY (TBL_USUARIO_idUsuario, CAT_PREFERENCIA_idPreferencia),
    FOREIGN KEY (TBL_USUARIO_idUsuario) REFERENCES vitacorpus.TBL_USUARIO(idUsuario),
    FOREIGN KEY (CAT_PREFERENCIA_idPreferencia) REFERENCES vitacorpus.CAT_PREFERENCIA(idPreferencia)
);

# ----------------------------
# Relación REL_USUARIO_OBJ
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_USUARIO_OBJ
(
	TBL_USUARIO_idUsuario INT NOT NULL,
    CAT_OBJETIVO_idObjetivos INT NOT NULL,
    
	PRIMARY KEY (TBL_USUARIO_idUsuario, CAT_OBJETIVO_idObjetivos),
    FOREIGN KEY (TBL_USUARIO_idUsuario) REFERENCES vitacorpus.TBL_USUARIO(idUsuario),
    FOREIGN KEY (CAT_OBJETIVO_idObjetivos) REFERENCES vitacorpus.CAT_OBJETIVO(idObjetivos)
);

# ----------------------------
# Relación REL_USUARIO_CALC
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_USUARIO_CALC
(
	TBL_USUARIO_idUsuario INT NOT NULL,
    TBL_CALCULADORA_idCalculadora INT NOT NULL,
    
	PRIMARY KEY (TBL_USUARIO_idUsuario, TBL_CALCULADORA_idCalculadora),
    FOREIGN KEY (TBL_USUARIO_idUsuario) REFERENCES vitacorpus.TBL_USUARIO(idUsuario),
    FOREIGN KEY (TBL_CALCULADORA_idCalculadora) REFERENCES vitacorpus.TBL_CALCULADORA(idCalculadora)
);

# ----------------------------
# Relación REL_RUTINA_EJ
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_RUTINA_EJ
(
	TBL_RUTINA_idRutina INT NOT NULL,
    TBL_EJERCICIO_idEjercicio INT NOT NULL,
    TBL_RUTINA_CAT_OBJETIVO_idObjetivos INT NOT NULL,
    series INT NOT NULL,
    repeticiones INT NOT NULL,
    
    PRIMARY KEY (TBL_RUTINA_idRutina, TBL_EJERCICIO_idEjercicio),
    FOREIGN KEY (TBL_RUTINA_idRutina) REFERENCES vitacorpus.TBL_RUTINA(idRutina),
    FOREIGN KEY (TBL_EJERCICIO_idEjercicio) REFERENCES vitacorpus.TBL_EJERCICIO(idEjercicio),
    FOREIGN KEY (TBL_RUTINA_CAT_OBJETIVO_idObjetivos) REFERENCES vitacorpus.CAT_OBJETIVO(idObjetivos)
);

# ----------------------------
# Relación REL_DIETA_COMIDA
# ----------------------------
CREATE TABLE IF NOT EXISTS vitacorpus.REL_DIETA_COMIDA
(
	TBL_DIETA_idDieta INT NOT NULL,
    TBL_COMIDA_idComida INT NOT NULL,
    TBL_DIETA_CAT_OBJETIVO_idObjetivos INT NOT NULL,
    cantidad DECIMAL(5,2) NOT NULL,
    
    PRIMARY KEY (TBL_DIETA_idDieta, TBL_COMIDA_idComida),
    FOREIGN KEY (TBL_DIETA_idDieta) REFERENCES vitacorpus.TBL_DIETA(idDieta),
    FOREIGN KEY (TBL_COMIDA_idComida) REFERENCES vitacorpus.TBL_COMIDA(idComida),
    FOREIGN KEY (TBL_DIETA_CAT_OBJETIVO_idObjetivos) REFERENCES vitacorpus.CAT_OBJETIVO(idObjetivos)
);

