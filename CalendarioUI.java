package ui;

import models.Liga;
import models.Partido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarioUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Liga liga;

    public CalendarioUI(Liga liga) {
        this.liga = liga;

        setTitle("Calendario de Jornadas");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Lista de partidos
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (Partido partido : liga.getPartidos()) {
            modeloLista.addElement(partido.toString());
        }
        JList<String> listaPartidos = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaPartidos);

        // Botones
        JButton btnSimularPartido = new JButton("Simular Partido Seleccionado");
        JButton btnSimularTodos = new JButton("Simular Toda la Liga");

        btnSimularPartido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaPartidos.getSelectedIndex();
                if (index >= 0) {
                    Partido partidoSeleccionado = liga.getPartidos().get(index);
                    partidoSeleccionado.simular();
                    modeloLista.set(index, partidoSeleccionado.toString());
                    JOptionPane.showMessageDialog(CalendarioUI.this,
                            "Partido simulado: " + partidoSeleccionado,
                            "Simulación Completa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnSimularTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liga.simularLigaConEventos();
                modeloLista.clear();
                for (Partido partido : liga.getPartidos()) {
                    modeloLista.addElement(partido.toString());
                }
                JOptionPane.showMessageDialog(CalendarioUI.this,
                        "Todos los partidos de la liga han sido simulados.",
                        "Simulación Completa", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnSimularPartido);
        panelBotones.add(btnSimularTodos);

        panelPrincipal.add(new JLabel("Calendario de Partidos"), BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }
}
