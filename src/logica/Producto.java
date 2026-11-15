package logica;

public class Producto {
    private int codigo;
    private String nombre;
    private int cantidad;
    

    public Producto() {};
    
    public Producto(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = 0; // Inicializa la cantidad a 0
    }

    public Producto(int codigo, String nombre, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    


    @Override 
    public String toString(){
        return "\nCódigo: " + codigo + "\nNombre: " + nombre + "\nCantidad: " + cantidad;
    }

    

}
