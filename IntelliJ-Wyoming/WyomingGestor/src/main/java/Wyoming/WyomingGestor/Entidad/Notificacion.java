package Wyoming.WyomingGestor.Entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "notificacion")
public class Notificacion {
    @Id
    @ColumnDefault("nextval('notificacion_notificacionid_seq')")
    @Column(name = "notificacionid", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticiaid")
    private Noticia noticiaid;

    @NotNull
    @Column(name = "fechanotificacion", nullable = false)
    private LocalDate fechanotificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicadopor")
    private Administrador publicadopor;

}