package models;

import java.util.ArrayList;
import java.util.List;

public class MercadoDeFichajes {
    private List<Jugador> jugadoresDisponibles;

    public MercadoDeFichajes() {
        this.jugadoresDisponibles = new ArrayList<>();
        cargarJugadoresIniciales();
    }

    private void cargarJugadoresIniciales() {
        jugadoresDisponibles.add(new Jugador("Erling Haaland", "Delantero", 22, 91));
        jugadoresDisponibles.add(new Jugador("Kylian Mbappé", "Delantero", 24, 92));
        jugadoresDisponibles.add(new Jugador("Vinícius Jr.", "Delantero", 22, 89));
        jugadoresDisponibles.add(new Jugador("Jude Bellingham", "Mediocampista", 20, 86));
    }

    public List<Jugador> getJugadoresDisponibles() {
        return jugadoresDisponibles;
    }

    public boolean venderJugador(Jugador jugador, Equipo comprador) {
        if (comprador.comprarJugador(jugador)) {
            jugadoresDisponibles.remove(jugador);
            return true;
        }
        return false;
    }

    public void agregarJugadorAlMercado(Jugador jugador) {
        jugadoresDisponibles.add(jugador);
    }
}
