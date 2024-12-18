package Wyoming.WyomingGestor.Web.Servlet;

import Wyoming.WyomingGestor.Entidad.Noticia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/noticias/*")
public class BuscarNoticiaServlet extends HttpServlet {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));
        EntityManager em = emf.createEntityManager();
        try {
            Noticia noticia = em.find(Noticia.class, id);
            if (noticia != null) {
                response.setContentType("application/json");
                response.getWriter().write("{\"id\":" + noticia.getNoticiaID() +
                        ", \"titulo\":\"" + noticia.getTitulo() +
                        "\", \"autor\":\"" + noticia.getAutor() +
                        "\", \"contenido\":\"" + noticia.getContenido() +
                        "\", \"categoria\":\"" + noticia.getCategoria() + "\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } finally {
            em.close();
        }
    }
}
