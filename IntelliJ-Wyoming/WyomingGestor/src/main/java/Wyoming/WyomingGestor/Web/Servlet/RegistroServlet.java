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

@WebServlet("/registro")  // Aquí se define la URL para el servlet
public class RegistroServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        boolean permisos = request.getParameter("permisos") != null;  // Validar permisos
        boolean permisopublicar = request.getParameter("permisopublicar") != null; // Validar permiso de publicar

        // Validación básica de campos
        if (nombre == null || correo == null || contrasena == null) {
            response.sendRedirect(request.getContextPath() + "/registro.html?error=1"); // Redirige si hay error
            return;
        }

        EntityManager em = emf.createEntityManager();

        try {
            // Crear objeto Administrador
            Administrador admin = new Administrador();
            admin.setNombre(nombre);
            admin.setCorreo(correo);
            admin.setContraseña(contrasena);  // Recuerda que deberías encriptar la contraseña
            admin.setPermisos(permisos);
            admin.setPermisopublicar(permisopublicar);

            // Iniciar transacción para guardar en la base de datos
            em.getTransaction().begin();
            em.persist(admin); // Guardar el nuevo administrador
            em.getTransaction().commit();

            // Redirigir al login o mostrar éxito
            response.sendRedirect(request.getContextPath() + "/login.html?registro=exitoso");

        } catch (Exception e) {
            // Si ocurre un error, hacer rollback de la transacción
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/registro.html?error=1"); // Redirige si error
        } finally {
            em.close(); // Cerrar el EntityManager
        }
    }
}
