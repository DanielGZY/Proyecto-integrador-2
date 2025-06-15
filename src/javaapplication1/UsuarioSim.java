package javaapplication1;
import java.util.Random;

public class UsuarioSim implements Runnable {

    private final String nombre;
    private final Cineteca cineteca;
    private final Random rand = new Random();
    

    public UsuarioSim(String nombre, Cineteca cineteca) {
        this.nombre = nombre;
        this.cineteca = cineteca;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //Randomizador
                Pelicula p = cineteca.obtenerPeliculaAleatoria();
                if (p != null) {
                    float nuevaCalificacion = 1.0f + rand.nextFloat() * 9.0f;
                    p.agregarCalificacion(nuevaCalificacion);
                   int newVista = p.getVisitas();
                   newVista++;
                   p.setVisitas(newVista);
                   
                    
                 //   System.out.println("DEBUG: " + nombre + " calificó \"" + p.getTitulo() + "\" con " + nuevaCalificacion + "con la vista " + newVista);
                }

                //tiempo de CD
                Thread.sleep(1000 + rand.nextInt(2000));

                //TODO: Incrementar las visitas + 1 cada vez que el usuario simulado pasa por la pelicula 
                
            } catch (InterruptedException e) {
                
                //Por ahora no existe la interrupcion si sale este mensaje es por que algo salio realmente mal
                System.out.println("DEBUG: " + nombre + " ha terminado.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
