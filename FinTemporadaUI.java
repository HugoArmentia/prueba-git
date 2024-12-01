package ui;

import models.Equipo;
import models.Liga;

import javax.swing.*;
import java.awt.*;

public class FinTemporadaUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Liga liga;

    public FinTemporadaUI(Liga liga) {
        this.liga = liga;

        setTitle("Fin de Temporada");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Equipo campeon = liga.getEquiposOrdenadosPorPuntos().get(0);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JLabel lblCampeon = new JLabel("¡El campeón es " + campeon.getNombre() + "!", SwingConstants.CENTER);
        lblCampeon.setFont(new Font("Arial", Font.BOLD, 18));

        JTextArea areaMejoresJugadores = new JTextArea("Mejores Jugadores:\n");
        areaMejoresJugadores.setEditable(false);

        campeon.getJugadores().stream()
                .sorted((j1, j2) -> Integer.compare(j2.getHabilidad(), j1.getHabilidad()))
                .limit(3)
                .forEach(j -> areaMejoresJugadores.append("- " + j.getNombre() + " (Habilidad: " + j.getHabilidad() + ")\n"));

        panelPrincipal.add(lblCampeon, BorderLayout.NORTH);
        panelPrincipal.add(new JScrollPane(areaMejoresJugadores), BorderLayout.CENTER);

        add(panelPrincipal);
    }
}

