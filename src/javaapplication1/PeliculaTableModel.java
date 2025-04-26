package javaapplication1;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 *
 * @author Genos
 */
public class PeliculaTableModel extends AbstractTableModel {
    
    // COSO ARRAY PARA SETEAR NOMBRES DE LAS COLUMNAS
    private final String[] columnas = {"Título", "Género", "Año", "Visitas", "Promedio Rating"};
    private final Cineteca cineteca; 

    // OBJETO PARA LLAMAR A LA TABLA
    public PeliculaTableModel(Cineteca cineteca) {
        this.cineteca = cineteca;
    }

    @Override
    public int getRowCount() {
        return cineteca.getPeliculas().size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    // Entendi hasta aqui lo de aca abajo no se como funciona pero lo hace Copy paste for the win
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Pelicula> peliculas = cineteca.getPeliculas(); // obtenemos siempre la lista actualizada
        Pelicula p = peliculas.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getTitulo();
            case 1 -> p.getGenero();
            case 2 -> p.getYear();
            case 3 -> p.getVisitas();
            case 4 -> String.format("%.2f", p.getPromedioCalificaciones());
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
