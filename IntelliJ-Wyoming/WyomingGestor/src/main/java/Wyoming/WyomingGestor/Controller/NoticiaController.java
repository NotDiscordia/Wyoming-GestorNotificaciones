package Wyoming.WyomingGestor.Controller;

import Wyoming.WyomingGestor.Entidad.Noticia;
import Wyoming.WyomingGestor.Servicio.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/noticias")
@CrossOrigin(origins = "*")  // Permite que cualquier dominio haga peticiones a este controlador
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarNoticia(@RequestBody Noticia noticia) {
        try {
            Noticia noticiaGuardada = noticiaService.registrarNoticia(noticia);
            return ResponseEntity.ok("Noticia registrada exitosamente con ID: " + noticiaGuardada.getNoticiaID());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar la noticia: " + e.getMessage());
        }
    }
}
