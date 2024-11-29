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
}
