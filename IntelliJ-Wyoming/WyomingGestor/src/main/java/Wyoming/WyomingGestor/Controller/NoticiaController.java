package Wyoming.WyomingGestor.Controller;

import Wyoming.WyomingGestor.Entidad.Noticia;
import Wyoming.WyomingGestor.Servicio.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/noticias")
@CrossOrigin(origins = "*")  // Permite que cualquier dominio haga peticiones a este controlador
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    // Endpoint para registrar una noticia
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarNoticia(@RequestBody Noticia noticia) {
        try {
            if (noticia.getFechaCreacion() == null) {
                noticia.setFechaCreacion(LocalDate.now());
            }

            Noticia nuevaNoticia = noticiaService.registrarNoticia(noticia);
            return ResponseEntity.ok(nuevaNoticia);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la noticia: " + e.getMessage());
        }
    }

    // Nuevo endpoint para obtener todas las noticias
    @GetMapping
    public ResponseEntity<?> obtenerNoticias() {
        try {
            List<Noticia> noticias = noticiaService.obtenerTodasLasNoticias();
            return ResponseEntity.ok(noticias);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las noticias: " + e.getMessage());
        }
    }
}
