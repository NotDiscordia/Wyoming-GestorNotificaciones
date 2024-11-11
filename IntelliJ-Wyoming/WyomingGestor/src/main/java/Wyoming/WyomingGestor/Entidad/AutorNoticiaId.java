package Wyoming.WyomingGestor.Entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AutorNoticiaId implements Serializable {
    private static final long serialVersionUID = -8810975830407383083L;
    @NotNull
    @Column(name = "autorid", nullable = false)
    private Integer autorid;

    @NotNull
    @Column(name = "noticiaid", nullable = false)
    private Integer noticiaid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AutorNoticiaId entity = (AutorNoticiaId) o;
        return Objects.equals(this.autorid, entity.autorid) &&
                Objects.equals(this.noticiaid, entity.noticiaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autorid, noticiaid);
    }

}