USE vitacorpus;
# ---------------------------------
# Inserción a la tabla de dietas
# ---------------------------------

INSERT INTO vitacorpus.TBL_DIETA (dieta, CAT_OBJETIVO_idObjetivos) VALUES
("Dieta 1 para bajar grasa",1),
("Dieta 2 para bajar grasa",1),
("Dieta 3 para bajar grasa",1),
("Dieta 4 para bajar grasa",1),
("Dieta 1 para subir músculo",2),
("Dieta 2 para subir músculo",2),
("Dieta 3 para subir músculo",2),
("Dieta 4 para subir músculo",2),
("Dieta 1 para definición corporal",3),
("Dieta 2 para definición corporal",3),
("Dieta 3 para definición corporal",3),
("Dieta 4 para definición corporal",3),
("Dieta 1 para aumento de resistencia",4),
("Dieta 2 para aumento de resistencia",4),
("Dieta 3 para aumento de resistencia",4),
("Dieta 4 para aumento de resistencia",4),
("Dieta 1 para ejercicio por salud",5),
("Dieta 2 para ejercicio por salud",5),
("Dieta 3 para ejercicio por salud",5),
("Dieta 4 para ejercicio por salud",5);

# ---------------------------------
# Inserción a la relación dietas-comidas
# ---------------------------------
INSERT INTO vitacorpus.REL_DIETA_COMIDA (TBL_DIETA_idDieta, TBL_COMIDA_idComida) VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10),
(11,11),
(12,12),
(13,13),
(14,14),
(15,15),
(16,16),
(17,17),
(18,18),
(19,19),
(20,20);

# ---------------------------------
# Inserción a la relación comidas-restricción
# ---------------------------------
INSERT INTO vitacorpus.REL_COMIDA_RESTRICCION (TBL_COMIDA_idComida, CAT_ALIMENTOS_RESTRINGIDOS_idAlimento) VALUES
# -------------------------
# Objetivo 1 - Dieta 1
# -------------------------
(1, 1),   -- Avena
(1, 2),   -- Plátano
(1, 4),   -- Leche vegetal
(1, 5),   -- Arroz integral
(1, 6),   -- Pollo
(1, 7),   -- Brócoli
(1, 10),  -- Rodajas de pepino
(1, 11),  -- Tomate
(1, 12),  -- Ensalada de atún
(1, 14),  -- Yogur vegetal
(1, 16),  -- Tostadas integrales (Pan integral)
(1, 17),  -- Aguacate
(1, 20),  -- Pescado
(1, 21),  -- Zanahorias
(1, 23),  -- Manzana
(1, 24),  -- Espinacas
(1, 25),  -- Garbanzos
(1, 27),  -- Huevo
# -------------------------
# Objetivo 1 - Dieta 2
# -------------------------
(2, 2),   -- Plátano
(2, 3),   -- Nueces
(2, 5),   -- Arroz integral (presente con lentejas)
(2, 6),   -- Pollo (ensalada de pollo)
(2, 10),  -- Rodajas de pepino (pepino con limón)
(2, 11),  -- Tomate (en ensalada mixta si se infiere)
(2, 12),  -- Ensalada de atún
(2, 13),  -- Arroz basmati (si asumimos que "arroz" es este tipo)
(2, 14),  -- Yogur vegetal (si aplica por yogur natural)
(2, 16),  -- Tostadas integrales (pan integral)
(2, 17),  -- Aguacate
(2, 20),  -- Pescado
(2, 21),  -- Zanahorias
(2, 23),  -- Manzana
(2, 24),  -- Espinacas
(2, 25),  -- Garbanzos
(2, 27),  -- Huevo
(2, 28),  -- Crema de cacahuate
(2, 29),  -- Pimiento (si se infiere en ensalada mixta)
# -------------------------
# Objetivo 1 - Dieta 3
# -------------------------
(3, 1),   -- Avena
(3, 2),   -- Plátano
(3, 3),   -- Nueces
(3, 5),   -- Arroz integral
(3, 6),   -- Pollo
(3, 9),   -- Frutos secos
(3, 10),  -- Rodajas de pepino
(3, 11),  -- Tomate
(3, 12),  -- Ensalada de atún
(3, 13),  -- Arroz basmati (si arroz ≡ este en tu base)
(3, 14),  -- Yogur vegetal
(3, 16),  -- Tostadas integrales
(3, 17),  -- Aguacate (si crees que aplica en alguna ensalada, no explícito aquí)
(3, 20),  -- Pescado (no aparece aquí, se omite)
(3, 21),  -- Zanahorias
(3, 23),  -- Manzana
(3, 24),  -- Espinacas (no aparece, se omite)
(3, 25),  -- Garbanzos
(3, 27),  -- Huevo
(3, 28),  -- Crema de cacahuate
(3, 29),  -- Pimiento
# -------------------------
# Objetivo 1 - Dieta 4
# -------------------------
(4, 1),    -- Avena
(4, 2),    -- Plátano
(4, 3),    -- Nueces
(4, 5),    -- Arroz integral
(4, 9),    -- Lentejas
(4, 10),   -- Rodajas de pepino
(4, 11),   -- Tomate
(4, 12),   -- Ensalada de atún
(4, 14),   -- Yogur vegetal
(4, 15),   -- Hamburguesa de pollo
(4, 16),   -- Tostadas integrales
(4, 17),   -- Aguacate
(4, 18),   -- Frutos secos
(4, 19),   -- Quinoa
(4, 20),   -- Pescado
(4, 21),   -- Zanahorias
(4, 24),   -- Espinacas
(4, 27);   -- Huevo

INSERT INTO vitacorpus.REL_COMIDA_RESTRICCION (TBL_COMIDA_idComida, CAT_ALIMENTOS_RESTRINGIDOS_idAlimento) VALUES
# -------------------------
# Objetivo 2 - Dieta 1
# -------------------------
(5, 1),    -- Avena
(5, 2),    -- Plátano
(5, 3),    -- Nueces
(5, 5),    -- Arroz integral
(5, 9),    -- Lentejas
(5, 24),   -- Espinacas
(5, 20),   -- Tofu
(5, 18),   -- Frutos secos
(5, 17),   -- Aguacate
(5, 19),   -- Quinoa
(5, 6),    -- Pollo
(5, 7),    -- Brócoli
(5, 12),   -- Ensalada de atún
(5, 16),   -- Tostadas integrales
(5, 15),   -- Hamburguesa de pollo
(5, 23),   -- Manzana
(5, 28),   -- Crema de cacahuate
(5, 21),   -- Zanahorias
# -------------------------
# Objetivo 2 - Dieta 2
# -------------------------
(6, 2),   -- Plátano
(6, 25),  -- Garbanzos
(6, 5),   -- Arroz integral
(6, 11),  -- Tomate
(6, 9),   -- Lentejas
(6, 18),  -- Frutos secos
(6, 1),   -- Avena
(6, 6),   -- Pollo
(6, 17),  -- Aguacate
(6, 20),  -- Tofu
(6, 24),  -- Espinacas
(6, 4),   -- Leche vegetal
(6, 19),  -- Quinoa
(6, 23),  -- Manzana
(6, 28),  -- Crema de cacahuate
# -------------------------
# Objetivo 2 - Dieta 3
# -------------------------
(7, 1),   -- Avena
(7, 2),   -- Plátano
(7, 3),   -- Nueces
(7, 4),   -- Leche vegetal
(7, 5),   -- Arroz integral
(7, 6),   -- Pollo
(7, 7),   -- Brócoli
(7, 9),   -- Lentejas
(7, 11),  -- Tomate
(7, 17),  -- Aguacate
(7, 18),  -- Frutos secos
(7, 19),  -- Quinoa
(7, 20),  -- Tofu
(7, 21),  -- Zanahorias
(7, 23),  -- Manzana
(7, 24),  -- Espinacas
(7, 25),  -- Garbanzos
(7, 28),  -- Crema de cacahuate
# -------------------------
# Objetivo 2 - Dieta 4
# -------------------------
(8, 1),   -- Avena
(8, 2),   -- Plátano
(8, 3),   -- Nueces
(8, 5),   -- Arroz integral
(8, 6),   -- Pollo
(8, 9),   -- Lentejas
(8, 11),  -- Tomate
(8, 16),  -- Tostadas integrales
(8, 17),  -- Aguacate
(8, 18),  -- Frutos secos
(8, 19),  -- Quinoa
(8, 20),  -- Tofu
(8, 21),  -- Zanahorias
(8, 23),  -- Manzana
(8, 24),  -- Espinacas
(8, 25),  -- Garbanzos
(8, 28);  -- Crema de cacahuate

INSERT INTO vitacorpus.REL_COMIDA_RESTRICCION (TBL_COMIDA_idComida, CAT_ALIMENTOS_RESTRINGIDOS_idAlimento) VALUES
# -------------------------
# Objetivo 3 - Dieta 1
# -------------------------
(9, 1),   -- Avena
(9, 2),   -- Plátano
(9, 5),   -- Arroz integral
(9, 6),   -- Pollo
(9, 8),   -- Sopa de zanahoria
(9, 9),   -- Lentejas
(9, 10),  -- Rodajas de pepino
(9, 11),  -- Tomate
(9, 12),  -- Ensalada de atún
(9, 15),  -- Hamburguesa de pollo
(9, 16),  -- Tostadas integrales
(9, 17),  -- Aguacate
(9, 18),  -- Frutos secos
(9, 19),  -- Quinoa
(9, 20),  -- Tofu
(9, 21),  -- Zanahorias
(9, 23),  -- Manzana
(9, 24),  -- Espinacas
(9, 25),  -- Garbanzos
(9, 28),  -- Crema de cacahuate
# -------------------------
# Objetivo 3 - Dieta 2
# -------------------------
(10, 1),   -- Avena
(10, 2),   -- Plátano
(10, 3),   -- Nueces
(10, 5),   -- Arroz integral
(10, 6),   -- Pollo
(10, 8),   -- Sopa de zanahoria
(10, 9),   -- Lentejas
(10, 10),  -- Rodajas de pepino
(10, 11),  -- Tomate
(10, 12),  -- Ensalada de atún
(10, 16),  -- Tostadas integrales
(10, 17),  -- Aguacate
(10, 18),  -- Frutos secos
(10, 19),  -- Quinoa
(10, 20),  -- Tofu
(10, 21),  -- Zanahorias
(10, 23),  -- Manzana
(10, 24),  -- Espinacas
(10, 25),  -- Garbanzos
# -------------------------
# Objetivo 3 - Dieta 3
# -------------------------
(11, 1),   -- Avena
(11, 2),   -- Plátano
(11, 5),   -- Arroz integral
(11, 6),   -- Pollo
(11, 8),   -- Sopa de zanahoria
(11, 9),   -- Lentejas
(11, 10),  -- Rodajas de pepino
(11, 11),  -- Tomate
(11, 12),  -- Ensalada de atún
(11, 15),  -- Hamburguesa de pollo
(11, 16),  -- Tostadas integrales
(11, 17),  -- Aguacate
(11, 18),  -- Frutos secos
(11, 19),  -- Quinoa
(11, 21),  -- Zanahorias
(11, 23),  -- Manzana
(11, 24),  -- Espinacas
(11, 25),  -- Garbanzos
# -------------------------
# Objetivo 3 - Dieta 4
# -------------------------
(12, 1),   -- Avena
(12, 2),   -- Plátano
(12, 5),   -- Arroz integral
(12, 6),   -- Pollo
(12, 8),   -- Sopa de zanahoria
(12, 9),   -- Lentejas
(12, 10),  -- Rodajas de pepino
(12, 11),  -- Tomate
(12, 12),  -- Ensalada de atún
(12, 16),  -- Tostadas integrales
(12, 17),  -- Aguacate
(12, 18),  -- Frutos secos
(12, 19),  -- Quinoa
(12, 21),  -- Zanahorias
(12, 23),  -- Manzana
(12, 24),  -- Espinacas
(12, 25),  -- Garbanzos
(12, 20);  -- Pescado

INSERT INTO vitacorpus.REL_COMIDA_RESTRICCION (TBL_COMIDA_idComida, CAT_ALIMENTOS_RESTRINGIDOS_idAlimento) VALUES
# -------------------------
# Objetivo 4 - Dieta 1
# -------------------------
(13, 1),   -- Avena
(13, 2),   -- Plátano
(13, 5),   -- Arroz integral
(13, 6),   -- Pollo
(13, 7),   -- Brócoli
(13, 8),   -- Sopa de zanahoria
(13, 9),   -- Lentejas
(13, 10),  -- Rodajas de pepino
(13, 11),  -- Tomate
(13, 12),  -- Ensalada de atún
(13, 16),  -- Tostadas integrales
(13, 17),  -- Aguacate
(13, 18),  -- Almendras
(13, 19),  -- Quinoa
(13, 25),  -- Garbanzos
(13, 29),  -- Pimientos
(13, 21),  -- Zanahorias
(13, 24),  -- Espinacas
(13, 23),  -- Manzana
(13, 18),  -- Frutos secos
# -------------------------
# Objetivo 4 - Dieta 2
# -------------------------
(14, 1),   -- Avena
(14, 2),   -- Plátano
(14, 3),   -- Nueces
(14, 6),   -- Pollo
(14, 5),   -- Arroz integral
(14, 7),   -- Brócoli
(14, 9),   -- Lentejas
(14, 10),  -- Rodajas de pepino
(14, 18),  -- Almendras
(14, 16),  -- Tostadas integrales
(14, 19),  -- Quinoa
(14, 25),  -- Garbanzos
(14, 11),  -- Tomate
(14, 24),  -- Espinacas
(14, 20),  -- Pescado
(14, 21),  -- Zanahorias
(14, 14),  -- Yogur vegetal
(14, 18),  -- Frutos secos
(14, 8),   -- Sopa de zanahoria
(14, 6),   -- Ensalada de pollo
# -------------------------
# Objetivo 4 - Dieta 3
# -------------------------
(15, 1),   -- Avena
(15, 2),   -- Plátano
(15, 3),   -- Nueces
(15, 6),   -- Pollo
(15, 5),   -- Arroz integral
(15, 24),  -- Espinacas
(15, 9),   -- Lentejas
(15, 18),  -- Almendras
(15, 19),  -- Quinoa
(15, 10),  -- Rodajas de pepino
(15, 12),  -- Ensalada de atún
(15, 11),  -- Tomate
(15, 18),  -- Frutos secos
(15, 8),   -- Sopa de zanahoria
(15, 20),  -- Pescado
(15, 21),  -- Zanahorias
(15, 23),  -- Manzana
(15, 14),  -- Yogur vegetal
(15, 15),  -- Hamburguesa de pollo
# -------------------------
# Objetivo 4 - Dieta 4
# -------------------------
(16, 1),   -- Avena
(16, 2),   -- Plátano
(16, 3),   -- Nueces
(16, 6),   -- Pollo
(16, 5),   -- Arroz integral
(16, 7),   -- Brócoli
(16, 8),   -- Sopa de zanahoria
(16, 9),   -- Lentejas
(16, 18),  -- Almendras
(16, 19),  -- Quinoa
(16, 12),  -- Ensalada de atún
(16, 11),  -- Tomate
(16, 18),  -- Frutos secos
(16, 25),  -- Garbanzos
(16, 21),  -- Zanahorias
(16, 23),  -- Manzana
(16, 24),  -- Espinacas
(16, 20),  -- Pescado
(16, 16),  -- Tostadas integrales
(16, 17);  -- Aguacate

INSERT INTO vitacorpus.REL_COMIDA_RESTRICCION (TBL_COMIDA_idComida, CAT_ALIMENTOS_RESTRINGIDOS_idAlimento) VALUES
# -------------------------
# Objetivo 5 - Dieta 1
# -------------------------
(17, 1),   -- Avena
(17, 2),   -- Plátano
(17, 3),   -- Nueces
(17, 6),   -- Pollo
(17, 5),   -- Arroz integral
(17, 7),   -- Brócoli
(17, 8),   -- Sopa de zanahoria
(17, 9),   -- Lentejas
(17, 18),  -- Almendras
(17, 19),  -- Quinoa
(17, 12),  -- Ensalada de atún
(17, 11),  -- Tomate
(17, 16),  -- Tostadas integrales
(17, 17),  -- Aguacate
(17, 21),  -- Zanahorias
(17, 24),  -- Espinacas
(17, 20),  -- Pescado
(17, 10),  -- Rodajas de pepino
(17, 15),  -- Hamburguesas de pollo
# -------------------------
# Objetivo 5 - Dieta 2
# -------------------------
(18, 1),   -- Avena
(18, 2),   -- Plátano
(18, 3),   -- Nueces
(18, 6),   -- Pollo
(18, 5),   -- Arroz integral
(18, 7),   -- Brócoli
(18, 8),   -- Sopa de zanahoria
(18, 9),   -- Lentejas
(18, 18),  -- Almendras
(18, 19),  -- Quinoa
(18, 12),  -- Ensalada de atún
(18, 11),  -- Tomate
(18, 16),  -- Tostadas integrales
(18, 17),  -- Aguacate
(18, 21),  -- Zanahorias
(18, 24),  -- Espinacas
(18, 20),  -- Pescado
(18, 10),  -- Rodajas de pepino
(18, 15),  -- Hamburguesas de pollo
# -------------------------
# Objetivo 5 - Dieta 3
# -------------------------
(19, 1),   -- Avena
(19, 2),   -- Plátano
(19, 3),   -- Nueces
(19, 6),   -- Pollo
(19, 5),   -- Arroz integral
(19, 24),  -- Espinacas
(19, 8),   -- Sopa de zanahoria
(19, 9),   -- Lentejas
(19, 18),  -- Almendras
(19, 19),  -- Quinoa
(19, 12),  -- Ensalada de atún
(19, 11),  -- Tomate
(19, 16),  -- Tostadas integrales
(19, 17),  -- Aguacate
(19, 21),  -- Zanahorias
(19, 20),  -- Pescado
(19, 10),  -- Rodajas de pepino
(19, 15),  -- Hamburguesas de pollo
# -------------------------
# Objetivo 5 - Dieta 4
# -------------------------
(20, 1),   -- Avena
(20, 23),  -- Manzana
(20, 3),   -- Nueces
(20, 6),   -- Pollo
(20, 5),   -- Arroz integral
(20, 7),   -- Brócoli
(20, 8),   -- Sopa de zanahoria
(20, 9),   -- Lentejas
(20, 18),  -- Almendras
(20, 19),  -- Quinoa
(20, 12),  -- Ensalada de atún
(20, 11),  -- Tomate
(20, 16),  -- Tostadas integrales
(20, 17),  -- Aguacate
(20, 21),  -- Zanahorias
(20, 20),  -- Pescado
(20, 10),  -- Rodajas de pepino
(20, 15),  -- Hamburguesas de pollo
(20, 24),  -- Espinacas
(20, 25),  -- Garbanzos
(20, 14),  -- Yogur vegetal
(20, 2);   -- Plátano