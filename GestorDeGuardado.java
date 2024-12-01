package utils;

import models.Liga;

import java.io.*;

public class GestorDeGuardado {
    public static void guardarLiga(Liga liga, String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(liga);
        }
    }

    public static Liga cargarLiga(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Liga) ois.readObject();
        }
    }
}
