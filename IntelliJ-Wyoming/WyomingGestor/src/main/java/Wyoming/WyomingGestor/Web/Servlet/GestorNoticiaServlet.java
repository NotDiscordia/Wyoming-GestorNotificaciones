package Wyoming.WyomingGestor.Web.Servlet;

import Wyoming.WyomingGestor.DTO.NoticiaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GestorNoticias")
public class GestorNoticiaServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:2276/WyomingGestor";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configurar la respuesta en formato JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Consulta a la base de datos
        List<NoticiaDTO> noticias = obtenerNoticias();

        // Convertir la lista de noticias a JSON utilizando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(noticias);

        // Enviar la respuesta como JSON
        response.getWriter().write(jsonResponse);
    }

    private List<NoticiaDTO> obtenerNoticias() {
        List<NoticiaDTO> noticias = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT noticiaID, titulo, contenido, fechaCreacion, categoriaID FROM noticia")) {

            while (rs.next()) {
                NoticiaDTO noticiaDTO = new NoticiaDTO();
                noticiaDTO.setNoticiaID(rs.getLong("noticiaID"));
                noticiaDTO.setTitulo(rs.getString("titulo"));
                noticiaDTO.setContenido(rs.getString("contenido"));
                noticiaDTO.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
                noticiaDTO.setCategoriaID(rs.getLong("categoriaID"));
                noticias.add(noticiaDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return noticias;
    }
}
