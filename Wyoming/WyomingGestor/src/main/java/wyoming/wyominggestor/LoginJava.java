package wyoming.wyominggestor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginJava extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Actualizamos el nombre del campo de la contraseña en la consulta
            String sql = "SELECT * FROM administrador WHERE correo = ? AND contraseña = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, correo);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Usuario autenticado correctamente
                    response.sendRedirect("principal.html");
                } else {
                    // Credenciales incorrectas
                    response.sendRedirect("error.html");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html"); // Redirige a una página de error en caso de fallo de conexión
        }
    }
}
