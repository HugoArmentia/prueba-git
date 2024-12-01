package ui;

import models.Equipo;
import models.Liga;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RankingUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Liga liga;

    public RankingUI(Liga liga) {
        this.liga = liga;

        setTitle("Tabla de Clasificación");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnas = {"Equipo", "Puntos", "PG", "PE", "PP", "GF", "GC", "Dif"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

        for (Equipo equipo : liga.getEquiposOrdenadosPorPuntos()) {
            modeloTabla.addRow(new Object[]{
                    equipo.getNombre(),
                    equipo.getPuntosLiga(),
                    equipo.getPartidosGanados(),
                    equipo.getPartidosEmpatados(),
                    equipo.getPartidosPerdidos(),
                    equipo.getGolesAFavor(),
                    equipo.getGolesEnContra(),
                    equipo.getDiferenciaDeGoles()
            });
        }

        JTable tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);
    }
}
