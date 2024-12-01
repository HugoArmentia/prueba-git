package ui;

import models.Equipo;
import models.Partido;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class SimulacionPartidoUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Partido partido;
    private JTextArea areaEventos;
    private JLabel marcador;
    private Timer timer;
    private int tiempoSimulacion; // Tiempo simulado en minutos


    public SimulacionPartidoUI(Partido partido) {
        this.partido = partido;
        this.tiempoSimulacion = 0;

        setTitle("Simulación de Partido");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
       

        // Configuración de la interfaz
        setLayout(new BorderLayout());

        // Marcador
        marcador = new JLabel(partido.getEquipoLocal().getNombre() + " 0 - 0 " + partido.getEquipoVisitante().getNombre(), SwingConstants.CENTER);
        marcador.setFont(new Font("Arial", Font.BOLD, 24));
        add(marcador, BorderLayout.NORTH);

        // Área de eventos
        areaEventos = new JTextArea();
        areaEventos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaEventos);
        add(scroll, BorderLayout.CENTER);

        // Botón de iniciar simulación
        JButton btnSimular = new JButton("Iniciar Simulación");
        btnSimular.addActionListener(e -> iniciarSimulacion());
        add(btnSimular, BorderLayout.SOUTH);
    }

    private void iniciarSimulacion() {
        partido.simular(); // Generar el resultado del partido

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tiempoSimulacion += 5;

                if (tiempoSimulacion > 90) { // Fin del partido
                    areaEventos.append("90+': Final del partido\n");
                    marcador.setText(partido.toString());
                    timer.cancel();
                } else {
                    generarEventoAleatorio();
                }
            }
        }, 0, 1000); // Actualizar cada segundo (5 minutos simulados por iteración)
    }

    private void generarEventoAleatorio() {
        int evento = (int) (Math.random() * 10);

        if (evento < 3) { // Gol
            Equipo anotador = Math.random() < 0.5 ? partido.getEquipoLocal() : partido.getEquipoVisitante();
            String jugador = anotador.getJugadores().get((int) (Math.random() * anotador.getJugadores().size())).getNombre();
            areaEventos.append(tiempoSimulacion + "': ¡Gol de " + jugador + " para el equipo " + anotador.getNombre() + "!\n");

            if (anotador == partido.getEquipoLocal()) {
                partido.setGolesLocal(partido.getGolesLocal() + 1);
            } else {
                partido.setGolesVisitante(partido.getGolesVisitante() + 1);
            }
            actualizarMarcador();
        } else if (evento < 6) { // Oportunidad fallida
            areaEventos.append(tiempoSimulacion + "': Oportunidad fallida para " +
                    (Math.random() < 0.5 ? partido.getEquipoLocal().getNombre() : partido.getEquipoVisitante().getNombre()) + "\n");
        } else { // Otro evento
            areaEventos.append(tiempoSimulacion + "': Jugada destacada sin resultado\n");
        }
    }

    private void actualizarMarcador() { 
        marcador.setText(partido.getEquipoLocal().getNombre() + " " + partido.getGolesLocal() + " - " +
                partido.getGolesVisitante() + " " + partido.getEquipoVisitante().getNombre());
    }
}
