package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import utils.EventoAleatorio;

public class Liga {
    private String nombre;
    private List<Equipo> equipos;
    private List<Partido> partidos;
    

    public Liga() {
        equipos = new ArrayList<>();
        partidos = new ArrayList<>();
    }

    public List<Equipo> getEquipos1() {
        return equipos;
    }

    public List<Partido> getPartidos1() {
        return partidos;
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public void generarCalendario() {
        partidos.clear();
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                partidos.add(new Partido(equipos.get(i), equipos.get(j)));
                partidos.add(new Partido(equipos.get(j), equipos.get(i))); // Ida y vuelta
            }
        }
        Collections.shuffle(partidos); // Mezclar partidos para un cronograma aleatorio
    }

    public void simularLiga() {
        for (Partido partido : partidos) {
            partido.simular();
        }
    }

    public List<String> obtenerTablaPosiciones() {
        equipos.sort((e1, e2) -> e2.getPuntosLiga() - e1.getPuntosLiga());
        List<String> tabla = new ArrayList<>();
        for (Equipo equipo : equipos) {
            tabla.add(equipo.getNombre() + " - Puntos: " + equipo.getPuntosLiga());
        }
        return tabla;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Liga: " + nombre + "\n");
        for (String linea : obtenerTablaPosiciones()) {
            builder.append(linea).append("\n");
        }
        return builder.toString();
    }
    public void simularLigaConEventos() {
        for (Partido partido : partidos) {
            partido.simular();
            EventoAleatorio.generarEvento(equipos); // Evento después de cada partido
        }
    }
    public List<Equipo> getEquiposOrdenadosPorPuntos() {
        List<Equipo> equiposOrdenados = new ArrayList<>(equipos);
        equiposOrdenados.sort(Comparator.comparingInt(Equipo::getPuntosLiga).reversed());
        return equiposOrdenados;
    }
    public List<Equipo> getEquipos() {
        return this.equipos;
}
    public static Liga crearLigaDePrueba() {
        Liga liga = new Liga();

        // Crear equipos con presupuestos
        Equipo equipo1 = new Equipo("Equipo A", 100000);
        Equipo equipo2 = new Equipo("Equipo B", 100000);
        Equipo equipo3 = new Equipo("Equipo C", 100000);
        Equipo equipo4 = new Equipo("Equipo D", 100000);
        
        // Crear jugadores para cada equipo
        Jugador jugador1A = new Jugador("Jugador 1A", 80, 1000);
        Jugador jugador2A = new Jugador("Jugador 2A", 75, 800);
        Jugador jugador1B = new Jugador("Jugador 1B", 78, 900);
        Jugador jugador2B = new Jugador("Jugador 2B", 76, 850);
        Jugador jugador1C = new Jugador("Jugador 1C", 85, 1200);
        Jugador jugador2C = new Jugador("Jugador 2C", 82, 1100);
        Jugador jugador1D = new Jugador("Jugador 1D", 79, 950);
        Jugador jugador2D = new Jugador("Jugador 2D", 77, 920);

        // Agregar jugadores a los equipos
        equipo1.agregarJugador(jugador1A);
        equipo1.agregarJugador(jugador2A);
        equipo2.agregarJugador(jugador1B);
        equipo2.agregarJugador(jugador2B);
        equipo3.agregarJugador(jugador1C);
        equipo3.agregarJugador(jugador2C);
        equipo4.agregarJugador(jugador1D);
        equipo4.agregarJugador(jugador2D);

        // Agregar equipos a la liga
        liga.equipos.add(equipo1);
        liga.equipos.add(equipo2);
        liga.equipos.add(equipo3);
        liga.equipos.add(equipo4);

        // Crear partidos entre los equipos
        Partido partido1 = new Partido(equipo1, equipo2);
        Partido partido2 = new Partido(equipo3, equipo4);

        // Agregar partidos a la liga
        liga.partidos.add(partido1);
        liga.partidos.add(partido2);

        return liga;
    }
}
