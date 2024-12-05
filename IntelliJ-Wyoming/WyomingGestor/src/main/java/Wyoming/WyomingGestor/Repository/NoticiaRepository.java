package Wyoming.WyomingGestor.Repository;

import Wyoming.WyomingGestor.Entidad.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
}
