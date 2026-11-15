package persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logica.Despacho;
import logica.Producto;
import utils.Utiles;

public class DespachoCrud {
    private final List<Despacho> despachos = new ArrayList<>();

    public Despacho crearDespacho(int numeroRemito, List<Producto> productos, String operario, int bultos, String nota) {
        if (productos == null) {
            System.out.println("La lista de productos no puede ser nula.");
            return null;
        }
        if (buscarPorNumeroRemito(numeroRemito) != null) {
            System.out.println("Ya existe un despacho con ese número de remito.");
            return null;
        }

        Despacho despacho = new Despacho(numeroRemito, LocalDate.now(), operario, bultos, nota);
        for (Producto producto : productos) {
            if (producto == null) {
                System.out.println("La lista no puede contener productos nulos.");
                return null;
            }
            despacho.agregarProducto(new Producto(producto.getCodigo(), producto.getNombre(), producto.getCantidad()));
        }
        despachos.add(despacho);
        return despacho;
    }

    public void crearDespacho() {
        int numeroRemito;
        do {
            numeroRemito = leerEntero("Número del remito: ");
            if (buscarPorNumeroRemito(numeroRemito) != null) {
                System.out.println("Ya existe un despacho con ese número de remito.");
            }
        } while (buscarPorNumeroRemito(numeroRemito) != null);

        int cantidadProductos = leerNoNegativo("¿Cuántos productos desea cargar? ");
        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println("Producto " + (i + 1) + ":");
            productos.add(leerProducto());
        }

        String operario = leerTexto("Operario: ");
        int bultos = leerNoNegativo("Cantidad de bultos: ");
        String nota = leerTexto("Nota: ");

        Despacho despacho = crearDespacho(numeroRemito, productos, operario, bultos, nota);
        System.out.println("Despacho creado:");
        despacho.mostrarInformacion();
    }

    public List<Despacho> getDespachos() {
        return List.copyOf(despachos);
    }

    public void listarDespachos() {
        if (despachos.isEmpty()) {
            System.out.println("No hay despachos cargados.");
            return;
        }

        for (Despacho despacho : despachos) {
            despacho.mostrarInformacion();
            System.out.println();
        }
    }

    public Despacho buscarPorId(int idDespacho) {
        for (Despacho despacho : despachos) {
            if (despacho.getId_documento() == idDespacho) {
                return despacho;
            }
        }
        return null;
    }

    public Despacho buscarPorNumeroRemito(int numeroRemito) {
        for (Despacho despacho : despachos) {
            if (despacho.getNum_remito() == numeroRemito) {
                return despacho;
            }
        }
        return null;
    }

    public void buscarDespacho() {
        int idDespacho = leerEntero("Ingrese el ID del despacho a buscar: ");
        Despacho despacho = buscarPorId(idDespacho);

        if (despacho == null) {
            System.out.println("No se encontró un despacho con ese ID.");
            return;
        }

        despacho.mostrarInformacion();
    }

    public void actualizarDespacho() {
        int idDespacho = leerEntero("Ingrese el ID del despacho a actualizar: ");
        Despacho despacho = buscarPorId(idDespacho);

        if (despacho == null) {
            System.out.println("No se encontró un despacho con ese ID.");
            return;
        }

        int opcion;
        do {
            System.out.println("1.- Modificar operario");
            System.out.println("2.- Modificar bultos");
            System.out.println("3.- Modificar nota");
            System.out.println("4.- Marcar como entregado");
            System.out.println("0.- Terminar");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    despacho.setOperario(leerTexto("Nuevo operario: "));
                    break;
                case 2:
                    despacho.setBultos(leerNoNegativo("Cantidad de bultos: "));
                    break;
                case 3:
                    despacho.setNota(leerTexto("Nueva nota: "));
                    break;
                case 4:
                    despacho.marcarComoEntregado();
                    System.out.println("El despacho fue marcado como entregado.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        System.out.println("Despacho actualizado:");
        despacho.mostrarInformacion();
    }

    public void actualizarDespacho(int idDespacho, String operario, int bultos, String nota) {
        Despacho despacho = buscarPorId(idDespacho);
        if (despacho == null) {
            System.out.println("No existe un despacho con el ID indicado.");
            return;
        }

        despacho.setOperario(operario);
        despacho.setBultos(bultos);
        despacho.setNota(nota);
    }

    public void eliminarDespacho() {
        int idDespacho = leerEntero("Ingrese el ID del despacho a eliminar: ");
        Despacho despacho = buscarPorId(idDespacho);

        if (despacho == null) {
            System.out.println("No se encontró un despacho con ese ID.");
            return;
        }

        despachos.remove(despacho);
        System.out.println("Despacho eliminado.");
    }

    public void eliminarDespacho(Despacho despacho) {
        if (despacho == null) {
            System.out.println("El despacho no puede ser nulo.");
            return;
        }
        despachos.remove(despacho);
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(Utiles.leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private int leerNoNegativo(String mensaje) {
        int valor;
        do {
            valor = leerEntero(mensaje);
            if (valor < 0) {
                System.out.println("El valor no puede ser negativo.");
            }
        } while (valor < 0);
        return valor;
    }

    private String leerTexto(String mensaje) {
        String valor;
        do {
            System.out.print(mensaje);
            valor = Utiles.leerString().trim();
            if (valor.isEmpty()) {
                System.out.println("El texto no puede estar vacío.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    private Producto leerProducto() {
        int codigo = leerNoNegativo("Código: ");
        String nombre = leerTexto("Nombre: ");
        int cantidad = leerNoNegativo("Cantidad: ");
        return new Producto(codigo, nombre, cantidad);
    }
}
