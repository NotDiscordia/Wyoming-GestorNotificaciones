-- Crear tabla de Categorías para normalizar el campo de categoría en la tabla Noticias
CREATE TABLE Categorias (
    ID SERIAL PRIMARY KEY,             -- Clave primaria para las categorías
    Nombre VARCHAR(50) UNIQUE NOT NULL  -- Nombre único de cada categoría, ej: 'Recordatorios', 'Eventos'
);

-- Insertar categorías predefinidas
INSERT INTO Categorias (Nombre) VALUES 
('Recordatorios'), 
('Eventos'), 
('Anuncios'), 
('Alertas');

-- Modificar la tabla Noticias para referenciar la tabla Categorias
ALTER TABLE Noticias 
    ADD COLUMN Categoria_ID INT,                          -- Nueva columna para almacenar la clave foránea
    ADD CONSTRAINT fk_categoria FOREIGN KEY (Categoria_ID) -- Clave foránea que referencia a la tabla Categorias
    REFERENCES Categorias(ID);

-- Quitar la columna de texto libre de Categoría en la tabla Noticias
ALTER TABLE Noticias 
    DROP COLUMN Categoria;

-- Modificar la tabla Notificaciones para relacionar el título con Noticias
ALTER TABLE Notificaciones 
    ADD COLUMN Noticia_ID INT,                           -- Nueva columna para almacenar la clave foránea
    ADD CONSTRAINT fk_noticia FOREIGN KEY (Noticia_ID)   -- Clave foránea que referencia a la tabla Noticias
    REFERENCES Noticias(ID);

-- Quitar la columna de Titulo en la tabla Notificaciones
ALTER TABLE Notificaciones 
    DROP COLUMN Titulo;
