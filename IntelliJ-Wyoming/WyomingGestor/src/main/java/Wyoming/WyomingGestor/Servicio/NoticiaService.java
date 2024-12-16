package Wyoming.WyomingGestor.Servicio;

import Wyoming.WyomingGestor.Entidad.Noticia;
import Wyoming.WyomingGestor.Repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public Noticia registrarNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    // Nuevo método para obtener todas las noticias
    public List<Noticia> obtenerTodasLasNoticias() {
        return noticiaRepository.findAll();
    }
}
