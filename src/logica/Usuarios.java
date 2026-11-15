package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class Usuarios extends Trabajador {

    public static final Set<String> CARGOS_VALIDOS = Set.of("admin", "operario", "logistica");

    private static int siguienteId = 1;
    private static final List<Usuarios> usuariosRegistrados = new ArrayList<>();

    static {
        new Usuarios("10000001", "Admin", "Sistema", "admin@example.com",
                "1111111111", "admin", 0, "admin", "admin123");
        new Usuarios("10000003", "Operario", "Demo", "operario@example.com",
                "3333333333", "operario", 0, "operario", "oper123");
        new Usuarios("10000004", "Logistica", "Demo", "logistica@example.com",
                "4444444444", "logistica", 0, "logistica", "log123");
    }

    private final int id_usuario;
    private String usuario;
    private String contrasena;

    public Usuarios(String dni, String nombre, String apellido, String email, String telefono, String cargo, double salario, String usuario, String contrasena) {
        super(dni, nombre, apellido, email, telefono, normalizarCargo(cargo), salario);
        this.id_usuario = siguienteId++;
        this.usuario = usuario;
        this.contrasena = contrasena;
        usuariosRegistrados.add(this);
    }

    public static String normalizarCargo(String cargo) {
        if (cargo == null) {
            throw new IllegalArgumentException("El cargo no puede ser nulo.");
        }

        String normalizado = cargo.trim().toLowerCase(Locale.ROOT);
        if (!CARGOS_VALIDOS.contains(normalizado)) {
            throw new IllegalArgumentException("Cargo inválido. Use admin, operario o logistica.");
        }
        return normalizado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public static List<Usuarios> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        String nuevoCargo = normalizarCargo(cargo);
        if ("admin".equalsIgnoreCase(this.cargo)
                && !"admin".equals(nuevoCargo)
                && contarAdministradores() == 1) {
            throw new IllegalStateException("No se puede quitar el cargo de admin al único administrador activo.");
        }
        super.setCargo(nuevoCargo);
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public static String validarCredenciales(String usuario, String contrasena) {
        if (usuario == null || contrasena == null) {
            System.out.println("El usuario o la contraseña no pueden ser nulos.");
            return null;
        }

        for (Usuarios registrado : usuariosRegistrados) {
            if (Objects.equals(registrado.usuario, usuario) && Objects.equals(registrado.contrasena, contrasena)) {
                return registrado.cargo;
            }
        }
        System.out.println("Usuario o contraseña incorrectos.");
        return null;
    }

    private static long contarAdministradores() {
        return usuariosRegistrados.stream()
                .filter(registrado -> "admin".equalsIgnoreCase(registrado.getCargo()))
                .count();
    }

    @Override 
    public String toString() {
        return super.toString() +
                "\nID Usuario: " + id_usuario +
                "\nUsuario: " + usuario;
    }

    @Override 
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

}
