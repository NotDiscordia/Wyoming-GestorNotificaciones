-- Incrementar el rango del ID de Administradores
ALTER TABLE administrador
    ALTER COLUMN administradorid SET DEFAULT nextval('administrador_administradorid_seq');

CREATE SEQUENCE administrador_administradorid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 922337
    CACHE 1;


-- 1. Eliminar las restricciones de claves foráneas que ya no necesitas
ALTER TABLE Noticia
DROP CONSTRAINT fk_administrador_noticia; -- Si necesitas eliminar la FK de Administrador

ALTER TABLE Noticia
DROP CONSTRAINT fk_categoria_noticia; -- Eliminar FK de Categoria

-- 2. Eliminar las columnas que hacen referencia a las otras tablas
ALTER TABLE Noticia
DROP COLUMN CategoriaID;  -- Eliminar la columna de la FK para Categoria

-- Si también quieres eliminar la columna de Autor (si existía), lo harías así:
-- ALTER TABLE Noticia
-- DROP COLUMN AutorID;  -- Eliminar la columna de la FK para Autor (si la tenías)

-- 1. Agregar la columna sin la restricción NOT NULL
ALTER TABLE Noticia
ADD COLUMN Autor VARCHAR(100);

-- 2. Actualizar los valores de la columna 'Autor' (puedes poner valores predeterminados, como 'Desconocido' o 'Sin Autor')
UPDATE Noticia
SET Autor = 'Desconocido'
WHERE Autor IS NULL;

-- 3. Cambiar la columna para que no permita valores NULL (si lo deseas)
ALTER TABLE Noticia
ALTER COLUMN Autor SET NOT NULL;
