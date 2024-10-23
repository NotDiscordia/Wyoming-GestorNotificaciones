Select * FROM categorias

-- Crear la tabla Autor (almacena autores de noticias)
CREATE TABLE Autor (
    AutorID SERIAL PRIMARY KEY,           -- Clave primaria única para cada autor
    Nombre VARCHAR(50) NOT NULL
);

-- Crear la tabla Categoria (almacena las diferentes categorías de noticias)
CREATE TABLE Categoria (
    CategoriaID SERIAL PRIMARY KEY,       -- Clave primaria única para cada categoría
    Nombre VARCHAR(50) UNIQUE NOT NULL    -- Nombre de la categoría (ej: 'Recordatorios', 'Eventos', 'Anuncios', etc.)
);

-- Insertar categorías predefinidas
INSERT INTO Categoria (Nombre) VALUES 
('Recordatorios'), 
('Eventos'), 
('Anuncios'), 
('Alertas');

-- Crear la tabla Noticia (almacena noticias)
CREATE TABLE Noticia (
    NoticiaID SERIAL PRIMARY KEY,         -- Clave primaria única para cada noticia
    Titulo VARCHAR(80) NOT NULL,          -- Título de la noticia
    AutorID INT NOT NULL,                 -- Clave foránea referenciando al autor
    Cuerpo TEXT NOT NULL,                 -- Cuerpo de la noticia
    CategoriaID INT NOT NULL,             -- Clave foránea referenciando a la categoría
    FechaCreacion DATE NOT NULL,          -- Fecha en la que la noticia fue creada
    FechaPublicacion DATE,                -- Fecha en la que la noticia será publicada (puede ser posterior a la de creación)
    CONSTRAINT fk_autor FOREIGN KEY (AutorID) REFERENCES Autor(AutorID),
    CONSTRAINT fk_categoria FOREIGN KEY (CategoriaID) REFERENCES Categoria(CategoriaID)
);

-- Crear la tabla Notificacion (almacena notificaciones vinculadas a noticias)
CREATE TABLE Notificacion (
    NotificacionID SERIAL PRIMARY KEY,    -- Clave primaria única para cada notificación
    NoticiaID INT NOT NULL,               -- Clave foránea referenciando la noticia
    FechaNotificacion DATE NOT NULL,      -- Fecha de la notificación
    CONSTRAINT fk_noticia FOREIGN KEY (NoticiaID) REFERENCES Noticia(NoticiaID)
);

-- Crear la tabla Administrador (almacena los administradores del sistema)
CREATE TABLE Administrador (
    AdministradorID SERIAL PRIMARY KEY,   -- Clave primaria única para cada administrador
    Nombre VARCHAR(50) NOT NULL,          -- Nombre del administrador
    Contraseña VARCHAR(50) NOT NULL,      -- Contraseña del administrador
    Correo VARCHAR(50) UNIQUE NOT NULL,   -- Correo electrónico del administrador (único)
    Permisos BOOLEAN NOT NULL             -- Indica si el administrador tiene permisos elevados
);

-- Crear la tabla Administrador (almacena los administradores del sistema)
ALTER TABLE Administrador               -- Indica si el administrador tiene permisos de administración generales
    ADD COLUMN PermisoPublicar BOOLEAN NOT NULL DEFAULT FALSE;  -- Indica si el administrador tiene permiso para publicar noticias

-- Modificar la tabla Noticia para incluir la referencia a Administrador
ALTER TABLE Noticia 
    ADD COLUMN PublicadoPor INT,                         -- Nueva columna para almacenar el administrador que publica
    ADD CONSTRAINT fk_publicador FOREIGN KEY (PublicadoPor) 
    REFERENCES Administrador(AdministradorID);


-- Eliminación de la tabla autor_noticia si no es necesaria
DROP TABLE IF EXISTS autor_noticia;

-- Agregar restricción para asegurar que la fecha de publicación sea mayor o igual a la de creación
ALTER TABLE Noticia
ADD CONSTRAINT chk_fechas CHECK (fechapublicacion >= fechacreacion);

-- Renombrar la columna publicadopor para mantener consistencia
ALTER TABLE Noticia
RENAME COLUMN publicadopor TO AdministradorID;

Insert into autor (Nombre)
Values ('Coordinación General de Servicios Estudiantiles y Gestión Escolar'), ('Cultura UABC'), ('Facultad de Ingeniería, Mexicali FIM')

Select * from autor

ALTER TABLE Notificacion
ADD COLUMN publicadopor INT
