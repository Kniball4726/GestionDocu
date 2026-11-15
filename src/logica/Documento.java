package logica;

import java.time.LocalDate;

public abstract class Documento implements Mostrable {
    private static int siguienteId = 1;

    protected final int id_documento;
    protected String tipo;
    protected String cliente;
    protected LocalDate fecha_creacion;

    public Documento(String tipo, LocalDate fecha_creacion) {
        this.id_documento = siguienteId++;
        this.tipo = tipo;
        this.fecha_creacion = fecha_creacion;
    }


    public int getId_documento() {
        return id_documento;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public LocalDate getFecha() {
        return fecha_creacion;
    }

    @Override 
    public String toString(){
        return "\nID: " + id_documento + "\nTipo: " + tipo + "\nCliente: " + cliente +  "\nCreaciòn: " + fecha_creacion;
    }

    public abstract void mostrarInformacion();

}