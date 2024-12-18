package Wyoming.WyomingGestor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Wyoming.WyomingGestor.Entidad.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    // Puedes agregar métodos personalizados aquí si los necesitas
}

