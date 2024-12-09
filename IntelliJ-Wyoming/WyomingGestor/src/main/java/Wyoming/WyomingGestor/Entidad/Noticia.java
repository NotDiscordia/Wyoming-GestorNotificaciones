package Wyoming.WyomingGestor.Entidad;

import Wyoming.WyomingGestor.Entidad.Categoria;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Noticia")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticiaID;

    @Column(nullable = false, length = 80)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "categoriaID", nullable = true)
    private Categoria categoria;

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
