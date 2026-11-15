package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Remito extends Documento {
	private int num_remito;
	private LocalDate fecha_entrega;
	private ArrayList<Detalle> detalles = new ArrayList<>();
	private boolean preparado;

	protected Remito(int num_remito) {
		this(num_remito, LocalDate.now());
	}

	protected Remito(int num_remito, LocalDate fecha_creacion) {
		super("Remito", fecha_creacion);
		this.num_remito = num_remito;
		this.fecha_entrega = fecha_creacion.plusDays(1);
	}

	public void agregarProducto(Producto producto) {
		detalles.add(new Detalle(producto));
	}

	public List<Detalle> getDetalles() {
		return List.copyOf(detalles);
	}

	public void actualizarProducto(int indice, Producto producto) {
		detalles.set(indice, new Detalle(producto));
	}

	public void eliminarProducto(int indice) {
		detalles.remove(indice);
	}

	public int getNum_remito() {
		return num_remito;
	}

	public void setNum_remito(int num_remito) {
		this.num_remito = num_remito;
	}

	public LocalDate getFecha_entrega() {
		return fecha_entrega;
	}

	public boolean isPreparado() {
		return preparado;
	}

	public void marcarComoPreparado() {
		if (preparado) {
			System.out.println("Ya se creó un despacho para este remito.");
		}
		this.preparado = true;
	}

	public void desmarcarComoPreparado() {
		this.preparado = false;
	}

	
	@Override
	public String toString() {
		return super.toString()+"Remito:\nNum_remito: " + num_remito + "\nFecha_entrega: " + fecha_entrega + "\nProductos: " + detalles
				+ "\nPreparado: " + preparado;
	}

	@Override
	public void mostrarInformacion() {
		System.out.println("Número: " + num_remito);
		System.out.println("Fecha de creación: " + getFecha_creacion());
		System.out.println("Fecha de entrega: " + fecha_entrega);
		System.out.println("Preparado: " + (preparado ? "Sí" : "No"));
		System.out.println("Productos:");
		for (Detalle detalle : detalles) {
			System.out.println(detalle);
		}
	}

	
}

