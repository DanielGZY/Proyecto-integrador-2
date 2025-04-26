/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Genos
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
    Cineteca cineteca = new Cineteca();

    // --- Agregar películas de prueba ---
    Pelicula pelicula1 = new Pelicula("Inception", "Ciencia Ficción", "2010", 500);
    Pelicula pelicula2 = new Pelicula("The Dark Knight", "Acción", "2008", 800);
    Pelicula pelicula3 = new Pelicula("Interstellar", "Ciencia Ficción", "2014", 600);
    
    cineteca.agregarPelicula(pelicula1);
    cineteca.agregarPelicula(pelicula2);
    cineteca.agregarPelicula(pelicula3);
    // -----------------------------------
    
    
   UsuarioSim usuario1 = new UsuarioSim("BigTex", cineteca);
   UsuarioSim usuario2 = new UsuarioSim("Sgt Iwan", cineteca);
   UsuarioSim usuario3 = new UsuarioSim("ZackReaver", cineteca);
   UsuarioSim usuario4 = new UsuarioSim("Ugarte", cineteca);
   UsuarioSim usuario5 = new UsuarioSim("Brendo", cineteca);
   
   
   new Thread(usuario1).start();
   new Thread(usuario2).start();
   new Thread(usuario3).start();
   new Thread(usuario4).start();
   new Thread(usuario5).start(); 
   
    GUI ventana = new GUI(cineteca);
    ventana.setVisible(true);

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // No crear otra GUI aquí, ya la creaste arriba.
            // Este bloque lo podemos eliminar si quieres.
        }
    });
}

    
}
