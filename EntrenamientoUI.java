package ui;

import models.Equipo;
import models.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrenamientoUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Equipo equipoUsuario;

    public EntrenamientoUI(Equipo equipoUsuario) {
        this.equipoUsuario = equipoUsuario;

        setTitle("Entrenamiento de Jugadores");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Lista de jugadores del equipo
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (Jugador jugador : equipoUsuario.getJugadores()) {
            modeloLista.addElement(jugador.toString());
        }
        JList<String> listaJugadores = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaJugadores);

        // Botón de entrenar jugador
        JButton btnEntrenar = new JButton("Entrenar Jugador");
        btnEntrenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaJugadores.getSelectedIndex();
                if (index >= 0) {
                    Jugador jugadorSeleccionado = equipoUsuario.getJugadores().get(index);
                    jugadorSeleccionado.entrenar(2); // Mejora arbitraria de 2 puntos
                    JOptionPane.showMessageDialog(EntrenamientoUI.this,
                            jugadorSeleccionado.getNombre() + " ha mejorado su habilidad.",
                            "Entrenamiento Exitoso", JOptionPane.INFORMATION_MESSAGE);
                    modeloLista.set(index, jugadorSeleccionado.toString());
                }
            }
        });

        panelPrincipal.add(new JLabel("Jugadores del Equipo:"), BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(btnEntrenar, BorderLayout.SOUTH);

        add(panelPrincipal);
    }
}
