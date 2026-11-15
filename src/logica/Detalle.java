package logica;

public class Detalle {
    private final Producto producto;

    public Detalle(Producto producto) {
        this.producto = producto;
    }
    
    public Producto getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        return "\n" + producto.getNombre()
                + "\nCódigo: " + producto.getCodigo()
                + "\nCantidad enviada: " + producto.getCantidad();
    }
}
