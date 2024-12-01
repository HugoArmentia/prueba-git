package utils;

import models.Equipo;
import models.Jugador;

import java.util.List;
import java.util.Random;

public class EventoAleatorio {
    private static final Random random = new Random();

    public static void generarEvento(List<Equipo> equipos) {
        int tipoEvento = random.nextInt(3); // 3 tipos de eventos
        Equipo equipoAfectado = equipos.get(random.nextInt(equipos.size()));

        switch (tipoEvento) {
            case 0: // Lesión
                if (!equipoAfectado.getJugadores().isEmpty()) {
                    Jugador lesionado = equipoAfectado.getJugadores().get(random.nextInt(equipoAfectado.getJugadores().size()));
                    lesionado.entrenar(-5); // Reducir habilidad temporalmente
                    System.out.println("¡Mala suerte! " + lesionado.getNombre() + " se ha lesionado. Habilidad reducida.");
                }
                break;
            case 1: // Mejoras temporales
                if (!equipoAfectado.getJugadores().isEmpty()) {
                    Jugador mejorado = equipoAfectado.getJugadores().get(random.nextInt(equipoAfectado.getJugadores().size()));
                    mejorado.entrenar(3); // Incrementar habilidad temporalmente
                    System.out.println("¡Suerte! " + mejorado.getNombre() + " ha mejorado sus habilidades en entrenamiento.");
                }
                break;
            case 2: // Presupuesto extra
                int bono = random.nextInt(50000) + 10000;
                equipoAfectado.setPresupuesto(equipoAfectado.getPresupuesto() + bono);
                System.out.println("¡Gran noticia! " + equipoAfectado.getNombre() + " ha recibido un bono de $" + bono);
                break;
        }
    }
}
