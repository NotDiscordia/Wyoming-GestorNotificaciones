import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;

@WebServlet("/NoticiaServlet")
public class NoticiaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Leer datos del cuerpo de la solicitud
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            stringBuilder.append(line);
        }

        // Convertir a JSON
        JSONObject jsonData = new JSONObject(stringBuilder.toString());
        String titulo = jsonData.getString("titulo");
        String autor = jsonData.getString("autor");
        String contenido = jsonData.getString("contenido");
        String fecha = jsonData.getString("fecha");
        String categoria = jsonData.getString("categoria");

        // Conectar a la base de datos e insertar noticia
        NoticiaManager noticiaManager = new NoticiaManager();
        boolean success = noticiaManager.registrarNoticia(titulo, autor, contenido, fecha, categoria);

        // Respuesta JSON
        JSONObject jsonResponse = new JSONObject();
        if (success) {
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Noticia registrada exitosamente");
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error al registrar la noticia");
        }

        // Escribir la respuesta JSON
        out.print(jsonResponse.toString());
        out.flush();
    }
}
