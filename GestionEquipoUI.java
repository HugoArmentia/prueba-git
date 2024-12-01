package ui;

import models.Equipo;

import javax.swing.*;
import java.awt.*;

public class GestionEquipoUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Equipo equipo;

    public GestionEquipoUI(Equipo equipo) {
        this.equipo = equipo;

        setTitle("Gestionar Equipo - " + equipo.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel lblEquipo = new JLabel("Gestionando equipo: " + equipo.getNombre(), SwingConstants.CENTER);
        panel.add(lblEquipo, BorderLayout.NORTH);

        JTextArea areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setText(equipo.toString());
        panel.add(new JScrollPane(areaInfo), BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar, BorderLayout.SOUTH);

        add(panel);
    }
}
