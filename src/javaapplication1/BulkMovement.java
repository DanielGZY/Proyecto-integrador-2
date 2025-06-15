package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BulkMovement extends Thread {
    private final Cineteca cineteca;

    public BulkMovement(Cineteca cineteca) {
        this.cineteca = cineteca;
    }

    @Override
    public void run() {
        String sql = "INSERT OR REPLACE INTO peliculas (titulo, genero, year, visitas, rating) VALUES (?, ?, ?, ?, ?)";

        while (!Thread.currentThread().isInterrupted()) {
            try (Connection conn = DatabaseManager.getInstance().getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                conn.setAutoCommit(false);

                // Always get the latest list of movies from cineteca
                for (Pelicula p : cineteca.getPeliculas()) {
                    pstmt.setString(1, p.getTitulo());
                    pstmt.setString(2, p.getGenero());
                    pstmt.setString(3, p.getYear());
                    pstmt.setInt(4, p.getVisitas());
                    pstmt.setFloat(5, p.getPromedioCalificaciones());
                    pstmt.addBatch();
                }

                pstmt.executeBatch();
                conn.commit();

                System.out.println("Bulk save completed successfully at " + java.time.LocalTime.now());

                // Sleep to avoid continuous DB hammering
                Thread.sleep(5000);

            } catch (SQLException e) {
                System.err.println("Error during bulk movement: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("BulkMovement thread interrupted, stopping.");
                Thread.currentThread().interrupt(); // preserve interrupt status
                break;
            }
        }
    }
}
