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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String contraseña = request.getParameter("contraseña");

        EntityManager em = emf.createEntityManager();

        try {
            // Buscar al administrador por correo
            Administrador admin = em.createQuery("SELECT a FROM Administrador a WHERE a.correo = :correo", Administrador.class)
                    .setParameter("correo", correo)
                    .getResultList().stream().findFirst().orElse(null);

            if (admin != null) {
                // Verificar la contraseña en texto plano
                if (admin.getContraseña().equals(contraseña)) {
                    // Credenciales correctas
                    System.out.println("Usuario autenticado correctamente. Redirigiendo a principal.html...");
                    response.sendRedirect(request.getContextPath() + "/principal.html");
                } else {
                    // Credenciales incorrectas
                    System.out.println("Credenciales incorrectas.");
                    response.sendRedirect(request.getContextPath() + "/login.html?error=1");
                }
            } else {
                // Usuario no encontrado
                System.out.println("Usuario no encontrado.");
                response.sendRedirect(request.getContextPath() + "/login.html?error=1");
            }
        } catch (Exception e) {
            // Error genérico
            System.out.println("Error durante la autenticación: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/login.html?error=1");
        } finally {
            em.close();
        }
    }
}
