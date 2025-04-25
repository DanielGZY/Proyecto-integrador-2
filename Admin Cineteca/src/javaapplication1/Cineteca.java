
package javaapplication1;


import java.util.ArrayList;
import java.util.List;

public class Cineteca {
    private List<Pelicula> peliculas;

    public Cineteca() {
        peliculas = new ArrayList<>();
    }

    // Agregar película
    public void agregarPelicula(Pelicula pelicula) {
        if (pelicula != null) {
            peliculas.add(pelicula);
        }
    }

    // Eliminar película por referencia
    public boolean eliminarPelicula(Pelicula pelicula) {
        return peliculas.remove(pelicula);
    }

    // Eliminar película por título (opcional)
    public boolean eliminarPeliculaPorTitulo(String titulo) {
        return peliculas.removeIf(p -> p.getTitulo().equalsIgnoreCase(titulo));
    }

    // Obtener todas las películas (para UI)
    public List<Pelicula> getPeliculas() {
        return new ArrayList<>(peliculas); // Return a copy to protect internal list
    }

    // Buscar película por título (útil para mostrar en UI detalles)
    public Pelicula buscarPorTitulo(String titulo) {
        for (Pelicula p : peliculas) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        return null; // No encontrada
    }

    // Generar detalles de todas las películas (para JTextArea, por ejemplo)
    public String getCatalogoDetallado() {
        if (peliculas.isEmpty()) return "No hay películas registradas.";

        StringBuilder sb = new StringBuilder();
        for (Pelicula p : peliculas) {
            sb.append(p.getDetalle()).append("\n-----\n");
        }
        return sb.toString();
    }
}


