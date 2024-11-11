package Wyoming.WyomingGestor.Entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "noticia")
public class Noticia {
    @Id
    @ColumnDefault("nextval('noticia_noticiaid_seq')")
    @Column(name = "noticiaid", nullable = false)
    private Integer id;

    @Size(max = 80)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 80)
    private String titulo;

    @NotNull
    @Column(name = "contenido", nullable = false, length = Integer.MAX_VALUE)
    private String contenido;

    @NotNull
    @Column(name = "fechacreacion", nullable = false)
    private LocalDate fechacreacion;

    @Column(name = "fechapublicacion")
    private LocalDate fechapublicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administradorid")
    private Administrador administradorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriaid")
    private Categoria categoriaid;

}