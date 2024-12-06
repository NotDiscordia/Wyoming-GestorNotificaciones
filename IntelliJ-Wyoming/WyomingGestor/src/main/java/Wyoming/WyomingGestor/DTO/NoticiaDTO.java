package Wyoming.WyomingGestor.DTO;

import Wyoming.WyomingGestor.Entidad.Categoria;
import java.time.LocalDate;

public class NoticiaDTO {
    private Long noticiaID;
    private String titulo;
    private String contenido;
    private LocalDate fechaCreacion;
    private Long categoriaID;  // Solo el ID de la categoría

    // Getters y Setters

    public Long getNoticiaID() {
        return noticiaID;
    }

    public void setNoticiaID(Long noticiaID) {
        this.noticiaID = noticiaID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(Long categoriaID) {
        this.categoriaID = categoriaID;
    }
}
