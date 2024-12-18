package Wyoming.WyomingGestor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Wyoming.WyomingGestor.Servicio.AdministradorService;
import Wyoming.WyomingGestor.Entidad.Administrador;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<Administrador> obtenerTodos() {
        return administradorService.obtenerTodos();
    }

    @PostMapping
    public Administrador guardar(@RequestBody Administrador administrador) {
        return administradorService.guardar(administrador);
    }

    @GetMapping("/{id}")
    public Administrador buscarPorId(@PathVariable Integer id) {
        return administradorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Administrador actualizar(@PathVariable Integer id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        return administradorService.guardar(administrador);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        administradorService.eliminar(id);
    }
}
