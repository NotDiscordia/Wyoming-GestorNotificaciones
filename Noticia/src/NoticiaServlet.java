import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/NoticiaServlet")
public class NoticiaServlet extends HttpServlet {

    private void setCORSHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configura los encabezados CORS
        setCORSHeaders(response);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Recuperar parámetros de la solicitud
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String contenido = request.getParameter("contenido");
        String fecha = request.getParameter("fecha");
        String categoria = request.getParameter("categoria");

        if (titulo == null || autor == null || contenido == null || fecha == null || categoria == null) {
            out.write("{\"success\": false, \"message\": \"Faltan datos en la solicitud.\"}");
            return;
        }

        // Conexión a la base de datos e inserción de datos
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/WyomingGestor", "postgres", "1234")) {
            String sql = "INSERT INTO Noticia (titulo, autor, contenido, fecha, categoria) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, titulo);
            statement.setString(2, autor);
            statement.setString(3, contenido);
            statement.setString(4, fecha);
            statement.setString(5, categoria);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                out.write("{\"success\": true, \"message\": \"Noticia registrada exitosamente.\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código de error 500
                out.write("{\"success\": false, \"message\": \"No se pudo registrar la noticia.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"success\": false, \"message\": \"Error en la base de datos.\"}");
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configura los encabezados CORS para solicitudes OPTIONS
        setCORSHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}