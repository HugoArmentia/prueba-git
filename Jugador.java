package models;

public class Jugador {
    private String nombre;
    private String posicion;
    private int edad;
    private int overall;  // Habilidad general
    private int moral;    // Estado de ánimo (0-100)
    private int estadoFisico;  // Estado físico (0-100)
    private int valor;     // Valor en el mercado de transferencias
    private int habilidad;

    public Jugador(String nombre, String posicion, int edad, int overall) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.edad = edad;
        this.overall = overall;
        this.moral = 80;  // Valor inicial
        this.estadoFisico = 100; // Valor inicial
        this.valor = calcularValor();
        
    }
    public Jugador(String nombre, int habilidad, int valor) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.valor = valor;
    }

    private int calcularValor() {
        return overall * 100000 + edad * 5000;
    }

    public void entrenar(int mejora) {
        this.overall += mejora;
        this.valor = calcularValor();
    }

    public void ajustarEstadoFisico(int cambio) {
        this.estadoFisico = Math.max(0, Math.min(100, this.estadoFisico + cambio));
    }

    public void ajustarMoral(int cambio) {
        this.moral = Math.max(0, Math.min(100, this.moral + cambio));
    }
    public int getHabilidad() {
        return habilidad;
    }
    

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getPosicion() { return posicion; }
    public int getEdad() { return edad; }
    public int getOverall() { return overall; }
    public int getMoral() { return moral; }
    public int getEstadoFisico() { return estadoFisico; }
    public int getValor() { return valor; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHabilidad(int habilidad) {
        this.habilidad = habilidad;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    

    @Override
    public String toString() {
        return nombre + " (" + posicion + ") - Overall: " + overall + ", Valor: $" + valor;
    }
   
}
