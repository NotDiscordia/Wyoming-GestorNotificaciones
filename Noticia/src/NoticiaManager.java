import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NoticiaManager {
    // Información de conexión a la base de datos
    private static final String URL = "jdbc:postgresql://localhost:5432/WyomingGestor";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    /**
     * Método para registrar una noticia en la base de datos
     * @param titulo Título de la noticia
     * @param autor Autor de la noticia
     * @param contenido Contenido de la noticia
     * @param fecha Fecha de creación de la noticia
     * @param categoria Categoría de la noticia
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean registrarNoticia(String titulo, String autor, String contenido, String fecha, String categoria) {
        boolean success = false;
        String query = "INSERT INTO Noticia (titulo, autor, contenido, fecha, categoria) VALUES (?, ?, ?, ?, ?)";

        // Convertir la fecha en formato String a un Timestamp si es necesario
        Timestamp fechaTimestamp = null;
        try {
            fechaTimestamp = Timestamp.valueOf(fecha);  // Asegúrate de que el formato sea correcto
        } catch (IllegalArgumentException e) {
            System.err.println("Fecha inválida: " + fecha);
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexión exitosa");

                // Configurar la consulta SQL
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, titulo);
                    statement.setString(2, autor);
                    statement.setString(3, contenido);
                    statement.setTimestamp(4, fechaTimestamp);  // Usar Timestamp para la fecha
                    statement.setString(5, categoria);

                    // Ejecutar la inserción
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        success = true;
                    }
                }
            } else {
                System.err.println("Error al conectar con la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar la noticia: " + e.getMessage());
            e.printStackTrace();
        }

        return success;
    }
}
