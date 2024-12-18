package Wyoming.WyomingGestor.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Wyoming.WyomingGestor.Repository.AdministradorRepository;
import Wyoming.WyomingGestor.Entidad.Administrador;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    public Administrador guardar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public Administrador buscarPorId(Integer id) {
        return administradorRepository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        administradorRepository.deleteById(id);
    }
}
