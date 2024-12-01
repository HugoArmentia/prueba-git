package models;

import java.util.Random;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;

    public Partido(Equipo local, Equipo visitante) {
        this.equipoLocal = local;
        this.equipoVisitante = visitante;
        this.golesLocal = 0;  // Inicializamos los goles en 0
        this.golesVisitante = 0;  // Inicializamos los goles en 0
    }
    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    // Métodos setters para actualizar los goles
    public void setGolesLocal1(int golesLocal) {
        this.golesLocal = golesLocal;
    }
    public void setGolesVisitante1(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public void simular() {
        Random random = new Random();
        int factorLocal = equipoLocal.calcularValorEquipo() + random.nextInt(100);
        int factorVisitante = equipoVisitante.calcularValorEquipo() + random.nextInt(100);

        golesLocal = factorLocal / 1000;
        golesVisitante = factorVisitante / 1000;

        equipoLocal.ajustarPuntosLiga(golesLocal > golesVisitante ? 3 : golesLocal == golesVisitante ? 1 : 0);
        equipoVisitante.ajustarPuntosLiga(golesVisitante > golesLocal ? 3 : golesLocal == golesVisitante ? 1 : 0);
        this.golesLocal = (int) (Math.random() * 5);  // Goles aleatorios (0-4)
        this.golesVisitante = (int) (Math.random() * 5);  // Goles aleatorios (0-4)
    }

    @Override
    public String toString() {
        return equipoLocal.getNombre() + " " + golesLocal + " - " + golesVisitante + " " + equipoVisitante.getNombre();
    }
    public void setGolesLocal(int goles) {
        this.golesLocal = goles;
    }

    public void setGolesVisitante(int goles) {
        this.golesVisitante = goles;
    }

}
