package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private String dbPath;

    private DatabaseManager() { }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public synchronized void setDatabasePath(String dbPath) {
        this.dbPath = dbPath;
    }

    public Connection getConnection() throws SQLException {
        if (dbPath == null || dbPath.isEmpty()) {
            throw new SQLException("Database path not set.");
        }

        String url = "jdbc:sqlite:file:" + dbPath + "?mode=rwc&cache=shared&threadsafe=1";
        return DriverManager.getConnection(url);
    }

    public void insertarPelicula(Pelicula p) {
        String sql = "INSERT OR REPLACE INTO peliculas(titulo, genero, year, visitas, promedioCalificaciones) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString (1, p.getTitulo());
            pstmt.setString (2, p.getGenero());
            pstmt.setString (3, p.getYear());
            pstmt.setInt    (4, p.getVisitas());
            pstmt.setFloat  (5, p.getPromedioCalificaciones());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error inserting pelicula: " + e.getMessage());
        }
    }
    
    public boolean eliminarPeliculaPorTitulo(String titulo) {
    String sql = "DELETE FROM peliculas WHERE titulo = ?";

    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, titulo);
        int filasAfectadas = pstmt.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException e) {
        System.err.println("Error al eliminar película de la base de datos: " + e.getMessage());
        return false;
    }
}


}
