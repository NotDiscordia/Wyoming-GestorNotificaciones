package Wyoming.WyomingGestor.Entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @ColumnDefault("nextval('autor_autorid_seq')")
    @Column(name = "autorid", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 50)
    @Column(name = "\"organización\"", length = 50)
    private String organización;

    @Size(max = 50)
    @Column(name = "cargo", length = 50)
    private String cargo;

}