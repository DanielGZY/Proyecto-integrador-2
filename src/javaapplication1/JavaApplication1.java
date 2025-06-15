package javaapplication1;


import java.util.ArrayList;

public class JavaApplication1 {

    public static void main(String[] args) {
        // Establecer la ruta de la base de datos
        
        /* Se que esto no es la solucion ideal y basicamente estoy mostrando donde esta la basse de datos o el token de loggin pero por ahora por falta de mayor conocimiento en como elaborarlo
        lo dejo aqui. pero hey funciona ☜(ﾟヮﾟ☜)
        */
        DatabaseManager.getInstance().setDatabasePath("data/peliculas.db");

        Cineteca cineteca = new Cineteca();

        // Cargar películas desde la base de datos
        Cineteca.cargarPeliculasDesdeDB(cineteca);

        //Esto puede ser removido lo dejo para evidencia solo se uso una vez para crear nuevas peliculas y probar CRUD
        
        /*
        if (cineteca.getPeliculas().isEmpty()) {
            cineteca.agregarPelicula(new Pelicula("Inception", "Ciencia Ficción", "2010", 500));
            cineteca.agregarPelicula(new Pelicula("The Dark Knight", "Acción", "2008", 800));
            cineteca.agregarPelicula(new Pelicula("Interstellar", "Ciencia Ficción", "2014", 600));
        } */

        // Crear hilos de usuarios simulados
        UsuarioSim[] usuarios = {
            new UsuarioSim("BigTex", cineteca),
            new UsuarioSim("Sgt Iwan", cineteca),
            new UsuarioSim("ZackReaver", cineteca),
            new UsuarioSim("Ugarte", cineteca),
            new UsuarioSim("Brendo", cineteca)
        };

        for (UsuarioSim u : usuarios) {
            new Thread(u).start();
        }

        BulkMovement bulk = new BulkMovement(cineteca);
        bulk.start();


        // Mostrar interfaz gráfica
        GUI ventana = new GUI(cineteca);
        ventana.setVisible(true);
    }
}
