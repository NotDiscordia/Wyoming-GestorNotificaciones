package Wyoming.WyomingGestor.Web.Servlet;

import Wyoming.WyomingGestor.Entidad.Administrador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper; // Importación de Jackson
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GestorUsuarios")
public class GestorUsuariosServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:2276/WyomingGestor";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configurar la respuesta en formato JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Consulta a la base de datos
        List<Administrador> administradores = obtenerAdministradores();

        // Convertir la lista de administradores a JSON utilizando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(administradores);

        // Imprimir el JSON en la consola para asegurarte de que se está generando correctamente
        System.out.println(jsonResponse);

        // Enviar la respuesta como JSON
        response.getWriter().write(jsonResponse);
    }

    private List<Administrador> obtenerAdministradores() {
        List<Administrador> administradores = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT administradorid, nombre, correo, permisos, permisopublicar FROM administrador")) {

            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setId(rs.getInt("administradorid"));
                admin.setNombre(rs.getString("nombre"));
                admin.setCorreo(rs.getString("correo"));
                admin.setPermisos(rs.getBoolean("permisos"));
                admin.setPermisopublicar(rs.getBoolean("permisopublicar"));
                administradores.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administradores;
    }
}
