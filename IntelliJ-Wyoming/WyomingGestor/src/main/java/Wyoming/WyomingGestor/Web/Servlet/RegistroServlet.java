package Wyoming.WyomingGestor.Web.Servlet;

import Wyoming.WyomingGestor.Entidad.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ResgistroUsuarios")
public class RegistroServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        boolean permisos = request.getParameter("permisos") != null;
        boolean permisopublicar = request.getParameter("permisopublicar") != null;

        if (nombre == null || correo == null || contrasena == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error: Faltan campos obligatorios");
            return;
        }

        EntityManager em = emf.createEntityManager();
        try {
            Administrador admin = new Administrador();
            admin.setNombre(nombre);
            admin.setCorreo(correo);
            admin.setContraseña(contrasena);
            admin.setPermisos(permisos);
            admin.setPermisopublicar(permisopublicar);

            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("success");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error en el servidor");
        } finally {
            em.close();
        }
    }
}
