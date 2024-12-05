package Wyoming.WyomingGestor.Entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "autor_noticia")
public class AutorNoticia {
    @EmbeddedId
    private AutorNoticiaId id;

    @MapsId("autorid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autorid", nullable = false)
    private Autor autorid;

    @MapsId("noticiaid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "noticiaid", nullable = false)
    private Noticia noticiaid;

}