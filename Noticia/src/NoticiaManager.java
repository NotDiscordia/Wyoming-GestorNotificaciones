import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NoticiaManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/WyomingGestor";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para insertar una noticia
    public void insertarNoticia(String titulo, String autor, String contenido, String fechaCreacion, String fechaPublicacion, int categoriaID) {
        String sql = "INSERT INTO Noticia (Titulo, Contenido, FechaCreacion, FechaPublicacion, CategoriaID) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, contenido);
            pstmt.setDate(3, java.sql.Date.valueOf(fechaCreacion)); // formato YYYY-MM-DD
            pstmt.setDate(4, java.sql.Date.valueOf(fechaPublicacion)); // formato YYYY-MM-DD
            pstmt.setInt(5, categoriaID);
            pstmt.executeUpdate();
            System.out.println("Noticia insertada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar la noticia: " + e.getMessage());
        }
    }

    // Método para mostrar todas las noticias
    public void mostrarNoticias() {
        String sql = "SELECT NoticiaID, Titulo, Contenido, FechaCreacion, FechaPublicacion FROM Noticia";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int noticiaID = rs.getInt("NoticiaID");
                String titulo = rs.getString("Titulo");
                String contenido = rs.getString("Contenido");
                String fechaCreacion = rs.getDate("FechaCreacion").toString();
                String fechaPublicacion = rs.getDate("FechaPublicacion") != null ? rs.getDate("FechaPublicacion").toString() : "N/A";
                System.out.println("ID: " + noticiaID + ", Título: " + titulo + ", Contenido: " + contenido +
                        ", Fecha de Creación: " + fechaCreacion + ", Fecha de Publicación: " + fechaPublicacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las noticias: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        NoticiaManager manager = new NoticiaManager();
        Scanner scanner = new Scanner(System.in);

        // Solicitar información para registrar una nueva noticia
        System.out.print("Ingrese el título de la noticia: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el autor de la noticia: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el contenido de la noticia: ");
        String contenido = scanner.nextLine();

        System.out.print("Ingrese la fecha de creación (YYYY-MM-DD): ");
        String fechaCreacion = scanner.nextLine();

        System.out.print("Ingrese la fecha de publicación (YYYY-MM-DD): ");
        String fechaPublicacion = scanner.nextLine();

        System.out.print("Ingrese el ID de la categoría: ");
        int categoriaID = scanner.nextInt();

        // Insertar la noticia en la base de datos
        manager.insertarNoticia(titulo, autor, contenido, fechaCreacion, fechaPublicacion, categoriaID);

        // Mostrar todas las noticias
        System.out.println("\nTodas las noticias registradas:");
        manager.mostrarNoticias();

        scanner.close();
    }
}
