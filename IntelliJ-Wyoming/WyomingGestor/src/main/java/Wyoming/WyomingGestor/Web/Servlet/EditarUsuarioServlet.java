package Wyoming.WyomingGestor.Web.Servlet;

import Wyoming.WyomingGestor.Entidad.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editarUsuario/*")
public class EditarUsuarioServlet extends HttpServlet {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));
        EntityManager em = emf.createEntityManager();
        try {
            Administrador admin = em.find(Administrador.class, id);
            if (admin != null) {
                // Actualizar datos del administrador
                admin.setNombre(request.getParameter("nombre"));
                admin.setCorreo(request.getParameter("correo"));
                admin.setContraseña(request.getParameter("contrasena"));
                admin.setPermisos(request.getParameter("permisos") != null);
                admin.setPermisopublicar(request.getParameter("permisopublicar") != null);

                em.getTransaction().begin();
                em.merge(admin);
                em.getTransaction().commit();

                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            em.close();
        }
    }
}
