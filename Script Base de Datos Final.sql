-- Crear tabla Administrador
CREATE TABLE Administrador (
    AdministradorID SERIAL PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Correo VARCHAR(50) UNIQUE NOT NULL,
    Contraseña VARCHAR(50) NOT NULL,
    Permisos BOOLEAN NOT NULL,
    PermisoPublicar BOOLEAN NOT NULL
);

-- Crear tabla Categoria
CREATE TABLE Categoria (
    CategoriaID SERIAL PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL
);

-- Crear tabla Autor
CREATE TABLE Autor (
    AutorID SERIAL PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL
);

-- Crear tabla Noticia
CREATE TABLE Noticia (
    NoticiaID SERIAL PRIMARY KEY,
    Titulo VARCHAR(80) NOT NULL,
    Cuerpo TEXT NOT NULL,
    FechaCreacion DATE NOT NULL,
    FechaPublicacion DATE,
    AdministradorID INT,
    CategoriaID INT,
    CONSTRAINT fk_administrador_noticia FOREIGN KEY (AdministradorID) REFERENCES Administrador(AdministradorID),
    CONSTRAINT fk_categoria_noticia FOREIGN KEY (CategoriaID) REFERENCES Categoria(CategoriaID)
);

-- Crear tabla Autor_Noticia para la relación muchos a muchos entre Autor y Noticia
CREATE TABLE Autor_Noticia (
    AutorID INT,
    NoticiaID INT,
    PRIMARY KEY (AutorID, NoticiaID),
    CONSTRAINT fk_autor FOREIGN KEY (AutorID) REFERENCES Autor(AutorID),
    CONSTRAINT fk_noticia FOREIGN KEY (NoticiaID) REFERENCES Noticia(NoticiaID)
);

-- Crear tabla Notificacion con una relación uno a uno con Noticia
CREATE TABLE Notificacion (
    NotificacionID SERIAL PRIMARY KEY,
    NoticiaID INT UNIQUE,  -- Clave única para asegurar que cada noticia tenga solo una notificación
    FechaNotificacion DATE NOT NULL,
    PublicadoPor INT,
    CONSTRAINT fk_noticia_notificacion FOREIGN KEY (NoticiaID) REFERENCES Noticia(NoticiaID),
    CONSTRAINT fk_publicado_por FOREIGN KEY (PublicadoPor) REFERENCES Administrador(AdministradorID)
);

-- Insertar un administrador predeterminado para evitar errores de clave foránea
INSERT INTO Administrador (Nombre, Correo, Contraseña, Permisos, PermisoPublicar)
VALUES ('Admin Default', 'admin@default.com', 'default123', true, true);

-- Insertar una categoría predeterminada para evitar errores de clave foránea
INSERT INTO Categoria (Nombre)
VALUES ('General');
