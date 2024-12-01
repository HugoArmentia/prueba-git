package ui;

import models.Liga;
import utils.GestorDeGuardado;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private Liga liga; // Aquí sigue siendo la liga como variable miembro

    public VentanaPrincipal(Liga liga) {
        this.liga = liga;  // La liga es asignada al objeto de la ventana

        setTitle("Modo Carrera - FIFA");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel("Modo Carrera - FIFA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 3, 10, 10));

        // Botones
        JButton btnGestionarEquipo = new JButton("Gestionar Equipo");
        JButton btnSimularPartido = new JButton("Simular Partido");
        JButton btnCalendario = new JButton("Ver Calendario");
        JButton btnRanking = new JButton("Tabla de Clasificación");
        JButton btnFinTemporada = new JButton("Fin de Temporada");
        JButton btnGuardar = new JButton("Guardar Progreso");
        JButton btnCargar = new JButton("Cargar Progreso");
        JButton btnSalir = new JButton("Salir");

        // Acción para gestionar el equipo
        btnGestionarEquipo.addActionListener(e -> {
            // Usamos la liga directamente ya que no estamos modificándola
            if (!liga.getEquipos().isEmpty()) {
                GestionEquipoUI gestionarUI = new GestionEquipoUI(liga.getEquipos().get(0)); // Ejemplo: Gestiona el primer equipo
                gestionarUI.setVisible(true);
            }
        });

        // Acción para simular un partido
        btnSimularPartido.addActionListener(e -> {
            if (!liga.getPartidos().isEmpty()) {
                SimulacionPartidoUI simulacionUI = new SimulacionPartidoUI(liga.getPartidos().get(0)); // Simula el primer partido
                simulacionUI.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No hay partidos disponibles para simular.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción para ver el calendario
        btnCalendario.addActionListener(e -> {
            CalendarioUI calendarioUI = new CalendarioUI(liga);
            calendarioUI.setVisible(true);
        });

        // Acción para ver el ranking
        btnRanking.addActionListener(e -> {
            RankingUI rankingUI = new RankingUI(liga);
            rankingUI.setVisible(true);
        });

        // Acción para finalizar la temporada
        btnFinTemporada.addActionListener(e -> {
            FinTemporadaUI finTemporadaUI = new FinTemporadaUI(liga);
            finTemporadaUI.setVisible(true);
        });

        // Acción para guardar progreso
        btnGuardar.addActionListener(e -> {
            try {
                GestorDeGuardado.guardarLiga(liga, "ligaGuardada.dat");
                JOptionPane.showMessageDialog(this, "Progreso guardado con éxito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el progreso.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        

        // Acción para salir
        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Agregar botones al panel
        panelBotones.add(btnGestionarEquipo);
        panelBotones.add(btnSimularPartido);
        panelBotones.add(btnCalendario);
        panelBotones.add(btnRanking);
        panelBotones.add(btnFinTemporada);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCargar);
        panelBotones.add(btnSalir);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        // Agregar panel principal a la ventana
        add(panelPrincipal);
    }

    public static void main(String[] args) {
        // Crear una liga de prueba
        Liga liga = new Liga(); // Crear una instancia de liga, se podría haber hecho de otra manera también
        // Aquí puedes agregar los equipos y jugadores a la liga como antes, con los métodos que tengas implementados

        // Llamamos a la ventana principal con la liga creada
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(liga);
        ventanaPrincipal.setVisible(true);
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
}




