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
@Table(name = "administrador")
public class Administrador {
    @Id
    @ColumnDefault("nextval('administrador_administradorid_seq')")
    @Column(name = "administradorid", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 50)
    @NotNull
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @Size(max = 50)
    @NotNull
    @Column(name = "\"contraseña\"", nullable = false, length = 50)
    private String contraseña;

    @NotNull
    @Column(name = "permisos", nullable = false)
    private Boolean permisos = false;

    @NotNull
    @Column(name = "permisopublicar", nullable = false)
    private Boolean permisopublicar = false;

}