package Wyoming.WyomingGestor.Servicio;

import Wyoming.WyomingGestor.Entidad.Noticia;
import Wyoming.WyomingGestor.Repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public Noticia registrarNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public List<Noticia> obtenerTodasLasNoticias() {
        return noticiaRepository.findAll();
    }

    public Noticia obtenerNoticiaPorId(Long id) {
        return noticiaRepository.findById(id).orElse(null);
    }

    public Noticia editarNoticia(Long id, Noticia noticiaActualizada) {
        return noticiaRepository.findById(id).map(noticia -> {
            noticia.setTitulo(noticiaActualizada.getTitulo());
            noticia.setContenido(noticiaActualizada.getContenido());
            noticia.setCategoria(noticiaActualizada.getCategoria());
            noticia.setAutor(noticiaActualizada.getAutor());
            return noticiaRepository.save(noticia);
        }).orElse(null);
    }

    public boolean eliminarNoticia(Long id) {
        if (noticiaRepository.existsById(id)) {
            noticiaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
