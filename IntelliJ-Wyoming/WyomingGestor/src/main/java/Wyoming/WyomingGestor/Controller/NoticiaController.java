package Wyoming.WyomingGestor.Controller;

import Wyoming.WyomingGestor.Entidad.Noticia;
import Wyoming.WyomingGestor.Servicio.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/noticias")
@CrossOrigin(origins = "*")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @PostMapping("/registrar")
    public Noticia registrarNoticia(@RequestBody Noticia noticia) {
        return noticiaService.registrarNoticia(noticia);
    }
}
