-- Crear la tabla de Administradores
CREATE TABLE Administradores (
    ID SERIAL PRIMARY KEY,             -- ID será el identificador único con incremento automático
    Nombre VARCHAR(50) NOT NULL,
    Contraseña VARCHAR(50) NOT NULL,
    Correo VARCHAR(50) UNIQUE NOT NULL,
    Permisos BOOLEAN NOT NULL
);

-- Crear la tabla de Noticias
CREATE TABLE Noticias (
    ID SERIAL PRIMARY KEY,             -- ID como clave primaria de cada noticia
    Titulo VARCHAR(80) NOT NULL,
    Autor VARCHAR(50) NOT NULL,
    Cuerpo TEXT NOT NULL,              -- En PostgreSQL, "TEXT" soporta grandes textos como MEDIUMTEXT en MySQL
    Categoria VARCHAR(50) NOT NULL,
    Fecha DATE NOT NULL
);

-- Crear la tabla de Notificaciones
CREATE TABLE Notificaciones (
    ID SERIAL PRIMARY KEY,             -- ID como clave primaria de cada notificación
    Titulo VARCHAR(50) NOT NULL,
    Fecha DATE NOT NULL
);
