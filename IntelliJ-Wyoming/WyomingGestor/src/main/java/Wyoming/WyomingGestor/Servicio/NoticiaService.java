package Wyoming.WyomingGestor.Servicio;

import Wyoming.WyomingGestor.Entidad.Noticia;
import Wyoming.WyomingGestor.Repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public Noticia registrarNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }
}
