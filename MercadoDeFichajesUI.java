package ui;

import models.Equipo;
import models.Jugador;
import models.MercadoDeFichajes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MercadoDeFichajesUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MercadoDeFichajes mercado;
    private Equipo equipoUsuario;

    public MercadoDeFichajesUI(MercadoDeFichajes mercado, Equipo equipoUsuario) {
        this.mercado = mercado;
        this.equipoUsuario = equipoUsuario;

        setTitle("Mercado de Fichajes");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Lista de jugadores en el mercado
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (Jugador jugador : mercado.getJugadoresDisponibles()) {
            modeloLista.addElement(jugador.toString());
        }
        JList<String> listaJugadores = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaJugadores);

        // Botón de comprar jugador
        JButton btnComprar = new JButton("Comprar Jugador");
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaJugadores.getSelectedIndex();
                if (index >= 0) {
                    Jugador jugadorSeleccionado = mercado.getJugadoresDisponibles().get(index);
                    if (mercado.venderJugador(jugadorSeleccionado, equipoUsuario)) {
                        JOptionPane.showMessageDialog(MercadoDeFichajesUI.this,
                                "Has comprado a " + jugadorSeleccionado.getNombre(),
                                "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        modeloLista.remove(index);
                    } else {
                        JOptionPane.showMessageDialog(MercadoDeFichajesUI.this,
                                "No tienes suficiente presupuesto para este jugador.",
                                "Compra Fallida", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        panelPrincipal.add(new JLabel("Jugadores Disponibles:"), BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(btnComprar, BorderLayout.SOUTH);

        add(panelPrincipal);
    }
}

