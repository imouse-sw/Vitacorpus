-- Rutinas para Bajar grasa (Objetivo 1)
INSERT INTO vitacorpus.TBL_RUTINA (rutina, descripcion, precauciones, gradoDificultad, CAT_OBJETIVO_idObjetivos) VALUES
("Semana 1: Activación inicial para bajar grasa", "Rutina de iniciación para comenzar a bajar grasa, incluye cardio y pesas de baja intensidad.", "Evitar sobrecargar el cuerpo al principio", 2, 1),
("Semana 2: Aumento de volumen para bajar grasa", "Rutina intermedia con más volumen y HIIT, ideal para seguir quemando grasa.", "Mantener descanso adecuado entre series", 3, 1),
("Semana 3: HIIT avanzado para bajar grasa", "Rutina avanzada con HIIT y pesas para maximizar la quema de grasa.", "Realizar calentamiento previo", 4, 1),
("Semana 4: Rutina avanzada para bajar grasa", "Rutina avanzada para tonificar y bajar grasa, con un enfoque en cardio intenso y pesas.", "Mantener postura adecuada", 3, 1);

-- Rutinas para Subir músculo (Objetivo 2)
INSERT INTO vitacorpus.TBL_RUTINA (rutina, descripcion, precauciones, gradoDificultad, CAT_OBJETIVO_idObjetivos) VALUES
("Semana 1: Rutina de fuerza básica para subir músculo", "Rutina para principiantes, centrada en fuerza para la musculación básica.", "Uso progresivo de pesos", 2, 2),
("Semana 2: Rutina intermedia de musculación", "Rutina para principiantes que trabaja los grupos musculares grandes, con más énfasis en pesas.", "Mantener control en cada repetición", 3, 2),
("Semana 3: Rutina avanzada de musculación", "Rutina avanzada centrada en aumentar la fuerza máxima, con ejercicios de pesas.", "Asegurarse de realizar estiramientos adecuados", 4, 2),
("Semana 4: Rutina de hipertrofia para subir músculo", "Rutina de hipertrofia que combina pesas con entrenamiento de fuerza.", "No exceder el peso recomendado", 3, 2);

-- Rutinas para Definición corporal (Objetivo 3)
INSERT INTO vitacorpus.TBL_RUTINA (rutina, descripcion, precauciones, gradoDificultad, CAT_OBJETIVO_idObjetivos) VALUES
("Semana 1: Rutina básica para definición corporal", "Rutina combinada de resistencia con pesas y cardio para inicio de definición corporal.", "Realizar movimientos controlados", 3, 3),
("Semana 2: Rutina intermedia para definición corporal", "Rutina para quemar grasa y mantener masa muscular con entrenamientos combinados.", "Evitar descanso excesivo entre series", 3, 3),
("Semana 3: Rutina avanzada para definición corporal", "Rutina avanzada con ejercicios mixtos para maximizar la definición muscular.", "Escuchar al cuerpo y no forzar el rango de movimiento", 4, 3),
("Semana 4: Rutina avanzada de tonificación para definición corporal", "Rutina de tonificación muscular combinada con ejercicios para reducción de grasa.", "Mantener técnica correcta", 2, 3);

-- Rutinas para Aumento de resistencia (Objetivo 4)
INSERT INTO vitacorpus.TBL_RUTINA (rutina, descripcion, precauciones, gradoDificultad, CAT_OBJETIVO_idObjetivos) VALUES
("Semana 1: Rutina básica para aumento de resistencia", "Rutina de circuito para mejorar la resistencia cardiovascular con enfoque general.", "Hidratarse frecuentemente", 2, 4),
("Semana 2: Rutina intermedia para aumento de resistencia", "Rutina para mejorar la resistencia aeróbica y anaeróbica con combinaciones de HIIT.", "Realizar buen calentamiento antes de empezar", 3, 4),
("Semana 3: Rutina avanzada para aumento de resistencia", "Rutina avanzada para aumentar resistencia en carreras largas y ejercicios aeróbicos.", "Escuchar al cuerpo y no excederse", 4, 4),
("Semana 4: Rutina avanzada para resistencia", "Rutina con intervalos de alta resistencia, ideal para mejorar el rendimiento físico general.", "Evitar sobrecarga al principio", 3, 4);

-- Rutinas para Ejercicio por salud (Objetivo 5)
INSERT INTO vitacorpus.TBL_RUTINA (rutina, descripcion, precauciones, gradoDificultad, CAT_OBJETIVO_idObjetivos) VALUES
("Semana 1: Rutina ligera para ejercicio por salud", "Rutina ligera de activación con enfoque en movilidad y mantenimiento de una vida activa.", "Consultar con médico antes de comenzar", 1, 5),
("Semana 2: Rutina de bajo impacto para ejercicio por salud", "Entrenamiento de bajo impacto para mejorar fuerza, movilidad y flexibilidad.", "Tomarse descansos cuando sea necesario", 2, 5),
("Semana 3: Rutina para mejorar postura y elasticidad", "Rutina de movilidad y estiramientos para mejorar la postura y elasticidad muscular.", "Realizar estiramientos para evitar lesiones", 2, 5),
("Semana 4: Rutina de tonificación para mantener un peso saludable", "Rutina de tonificación muscular que también ayuda a mantener un peso saludable.", "Escuchar a tu cuerpo y ajustarse a su ritmo", 2, 5);

# ---------------------------------
# Inserción a la relación rutinas-ejercicios
# ---------------------------------
INSERT INTO vitacorpus.REL_RUTINA_EJ (TBL_RUTINA_idRutina, TBL_EJERCICIO_idEjercicio) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20);
