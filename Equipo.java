package models;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores;
    private int presupuesto;
    private int puntosLiga;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAFavor;
    private int golesEnContra;
    

    public Equipo(String nombre, int presupuesto) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.presupuesto = presupuesto;
        this.puntosLiga = 0; // Comienza en 0
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
    }
    
    public void registrarVictoria(int golesAFavor, int golesEnContra) {
        this.partidosGanados++;
        this.golesAFavor += golesAFavor;
        this.golesEnContra += golesEnContra;
        this.puntosLiga += 3; // 3 puntos por victoria
    }

    public void registrarEmpate(int golesAFavor, int golesEnContra) {
        this.partidosEmpatados++;
        this.golesAFavor += golesAFavor;
        this.golesEnContra += golesEnContra;
        this.puntosLiga += 1; // 1 punto por empate
    }

    public void registrarDerrota(int golesAFavor, int golesEnContra) {
        this.partidosPerdidos++;
        this.golesAFavor += golesAFavor;
        this.golesEnContra += golesEnContra;
        // No se suman puntos en caso de derrota
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void venderJugador(Jugador jugador) {
        jugadores.remove(jugador);
        presupuesto += jugador.getValor();
    }

    public boolean comprarJugador(Jugador jugador) {
        if (presupuesto >= jugador.getValor()) {
            jugadores.add(jugador);
            presupuesto -= jugador.getValor();
            return true;
        }
        return false;
    }

    public void ajustarPuntosLiga(int puntos) {
        this.puntosLiga += puntos;
    }

    public int calcularValorEquipo() {
        return jugadores.stream().mapToInt(Jugador::getValor).sum();
    }
    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public int getDiferenciaDeGoles() {
        return golesAFavor - golesEnContra; // Diferencia de goles
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public List<Jugador> getJugadores() { return jugadores; }
    public int getPresupuesto() { return presupuesto; }
    public int getPuntosLiga1() { return puntosLiga; }
    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getPuntosLiga() { return puntosLiga; }

    @Override
    public String toString() {
        return "Equipo: " + nombre + " | Presupuesto: $" + presupuesto + " | Puntos: " + puntosLiga;
    }
}



