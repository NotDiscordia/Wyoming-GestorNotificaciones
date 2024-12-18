package Wyoming.WyomingGestor.Entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "administrador_seq")
    @SequenceGenerator(name = "administrador_seq", sequenceName = "administrador_administradorid_seq", allocationSize = 1)
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
    @Column(name = "contraseña", nullable = false, length = 50)
    private String contraseña;

    @NotNull
    @Column(name = "permisos", nullable = false)
    private Boolean permisos = false;

    @NotNull
    @Column(name = "permisopublicar", nullable = false)
    private Boolean permisopublicar = false;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Boolean getPermisos() {
        return permisos;
    }

    public void setPermisos(Boolean permisos) {
        this.permisos = permisos;
    }

    public Boolean getPermisopublicar() {
        return permisopublicar;
    }

    public void setPermisopublicar(Boolean permisopublicar) {
        this.permisopublicar = permisopublicar;
    }
}
