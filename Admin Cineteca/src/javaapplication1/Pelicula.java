package javaapplication1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pelicula {

    private String titulo;
    private String genero;
    private int year;
    private int visitas;
    private final List<Float> calificaciones; // Thread-safe access below

    public Pelicula(String titulo, String genero, int year, int visitas) {
        this.titulo = titulo;
        this.genero = genero;
        this.year = year;
        this.visitas = visitas;
        this.calificaciones = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized String getTitulo() {
        return titulo;
    }

    public synchronized void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public synchronized String getGenero() {
        return genero;
    }

    public synchronized void setGenero(String genero) {
        this.genero = genero;
    }

    public synchronized int getYear() {
        return year;
    }

    public synchronized void setYear(int year) {
        this.year = year;
    }

    public synchronized int getVisitas() {
        return visitas;
    }

    public synchronized void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    // Concurrency-safe: Add rating
    public void agregarCalificacion(float rating) {
        if (rating >= 0 && rating <= 10) {
            calificaciones.add(rating);
        }
    }

    // Get average rating
    public float getPromedioCalificaciones() {
        synchronized (calificaciones) {
            if (calificaciones.isEmpty()) return 0;
            float suma = 0;
            for (float r : calificaciones) {
                suma += r;
            }
            return suma / calificaciones.size();
        }
    }

    public int getCantidadCalificaciones() {
        synchronized (calificaciones) {
            return calificaciones.size();
        }
    }

    // For UI
    public String getDetalle() {
        return "Título: " + titulo + "\n" +
               "Género: " + genero + "\n" +
               "Año: " + year + "\n" +
               "Visitas: " + visitas + "\n" +
               "Rating promedio: " + String.format("%.2f", getPromedioCalificaciones()) +
               " (" + getCantidadCalificaciones() + " calificaciones)";
    }

    @Override
    public String toString() {
        return titulo + " (" + year + ")";
    }
}

    


