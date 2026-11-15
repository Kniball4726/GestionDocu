package logica;

import java.time.LocalDate;

public class Despacho extends Remito {
    private String operario;
    private int bultos;
    private String nota;
    private boolean entregado;

    public Despacho(int numeroRemito, LocalDate fecha_creacion, String operario, int bultos, String nota) {
        super(numeroRemito, fecha_creacion);
        this.tipo = "Despacho";
        if (bultos < 0) {
            System.out.println("La cantidad de bultos no puede ser negativa.");
        }
        this.operario = operario;
        this.bultos = bultos;
        this.nota = nota == null ? "" : nota;
        marcarComoPreparado();
    }

    public String getOperario() {
        return operario;
    }

    public void setOperario(String operario) {
        if (operario == null || operario.isBlank()) {
            System.out.println("El operario no puede estar vacío.");
            return;
        }
        this.operario = operario;
    }

    public int getBultos() {
        return bultos;
    }

    public void setBultos(int bultos) {
        if (bultos < 0) {
            System.out.println("La cantidad de bultos no puede ser negativa.");
            return;
        }
        this.bultos = bultos;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota == null ? "" : nota;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public void marcarComoEntregado() {
        this.entregado = true;
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("\nDespacho ID: " + getId_documento());
        System.out.println("Fecha de creación: " + getFecha_creacion());
        System.out.println("Número de remito: " + getNum_remito());
        System.out.println("Operario: " + operario);
        System.out.println("Bultos: " + bultos);
        System.out.println("Nota: " + nota);
        System.out.println("Entregado: " + (entregado ? "Sí" : "No"));
        System.out.println("Productos:");
        for (Detalle detalle : getDetalles()) {
            System.out.println(detalle);
        }
    }

}
