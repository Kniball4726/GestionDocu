package persistencia;

import java.util.List;

import logica.Usuarios;
import utils.Utiles;

public class UsuariosCrud {
    public Usuarios crearUsuario(String dni, String nombre, String apellido, String email,
                                String telefono, String cargo, double salario,
                                String usuario, String contrasena) {
        if (dni == null || dni.isBlank() || nombre == null || nombre.isBlank() ||
                apellido == null || apellido.isBlank() || email == null || email.isBlank() ||
                telefono == null || telefono.isBlank() || cargo == null || cargo.isBlank() ||
                usuario == null || usuario.isBlank() || contrasena == null || contrasena.isBlank()) {
                System.out.println("Todos los campos obligatorios deben completarse.");
                return null;
        }

        String cargoNormalizado;
        try {
            cargoNormalizado = Usuarios.normalizarCargo(cargo);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }

        if (buscarPorUsuario(usuario) != null) {
            System.out.println("Ya existe un usuario con ese nombre.");
            return null;
        }

        return new Usuarios(dni, nombre, apellido, email, telefono, cargoNormalizado, salario, usuario, contrasena);
    }

    public void crearUsuarioDesdeConsola() {
        String dni = leerTexto("DNI: ");
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        String email = leerTexto("Email: ");
        String telefono = leerTexto("Teléfono: ");
        String cargo = leerCargo();
        double salario = leerDouble("Salario: ");
        String usuario = leerUsuarioDisponible();
        String contrasena = leerTexto("Contraseña: ");

        Usuarios creado = crearUsuario(dni, nombre, apellido, email, telefono, cargo, salario, usuario, contrasena);
        if (creado == null) {
            return;
        }

        System.out.println("Usuario creado correctamente:");
        creado.mostrarInformacion();
    }

    public List<Usuarios> listarUsuarios() {
        List<Usuarios> usuarios = Usuarios.getUsuariosRegistrados();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return usuarios;
        }

        for (Usuarios usuario : usuarios) {
            usuario.mostrarInformacion();
            System.out.println();
        }
        return usuarios;
    }

    public Usuarios buscarPorId(int idUsuario) {
        for (Usuarios usuario : Usuarios.getUsuariosRegistrados()) {
            if (usuario.getId_usuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    public Usuarios buscarPorUsuario(String usuario) {
        if (usuario == null || usuario.isBlank()) {
            return null;
        }

        for (Usuarios registrado : Usuarios.getUsuariosRegistrados()) {
            if (registrado.getUsuario().equalsIgnoreCase(usuario.trim())) {
                return registrado;
            }
        }
        return null;
    }

    public void buscarUsuario() {
        int id = leerEntero("Ingrese el ID del usuario a buscar: ");
        Usuarios usuario = buscarPorId(id);
        if (usuario == null) {
            System.out.println("No existe un usuario con ese ID.");
            return;
        }
        usuario.mostrarInformacion();
    }

    public void actualizarUsuario() {
        int id = leerEntero("Ingrese el ID del usuario a actualizar: ");
        Usuarios usuario = buscarPorId(id);
        if (usuario == null) {
            System.out.println("No existe un usuario con ese ID.");
            return;
        }

        int opcion;
        do {
            System.out.println("1.- Modificar nombre");
            System.out.println("2.- Modificar apellido");
            System.out.println("3.- Modificar email");
            System.out.println("4.- Modificar teléfono");
            System.out.println("5.- Modificar cargo");
            System.out.println("6.- Modificar salario");
            System.out.println("7.- Modificar usuario");
            System.out.println("8.- Modificar contraseña");
            System.out.println("0.- Terminar");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    usuario.setNombre(leerTexto("Nuevo nombre: "));
                    break;
                case 2:
                    usuario.setApellido(leerTexto("Nuevo apellido: "));
                    break;
                case 3:
                    usuario.setEmail(leerTexto("Nuevo email: "));
                    break;
                case 4:
                    usuario.setTelefono(leerTexto("Nuevo teléfono: "));
                    break;
                case 5:
                    actualizarCargo(usuario);
                    break;
                case 6:
                    usuario.setSalario(leerDouble("Nuevo salario: "));
                    break;
                case 7:
                    String nuevoUsuario = leerTexto("Nuevo nombre de usuario: ");
                    if (buscarPorUsuario(nuevoUsuario) != null && !nuevoUsuario.equalsIgnoreCase(usuario.getUsuario())) {
                        System.out.println("Ya existe un usuario con ese nombre.");
                        break;
                    }
                    usuario.setUsuario(nuevoUsuario);
                    break;
                case 8:
                    usuario.setContrasena(leerTexto("Nueva contraseña: "));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        System.out.println("Usuario actualizado:");
        usuario.mostrarInformacion();
    }

    public void eliminarUsuario() {
        int id = leerEntero("Ingrese el ID del usuario a eliminar: ");
        Usuarios usuario = buscarPorId(id);
        if (usuario == null) {
            System.out.println("No existe un usuario con ese ID.");
            return;
        }

        if (usuario.getCargo().equalsIgnoreCase("admin") && Usuarios.getUsuariosRegistrados().stream().filter(u -> u.getCargo().equalsIgnoreCase("admin")).count() == 1) {
            System.out.println("No se puede eliminar el único administrador activo.");
            return;
        }

        Usuarios.getUsuariosRegistrados().remove(usuario);
        System.out.println("Usuario eliminado correctamente.");
    }

    public void eliminarUsuario(int idUsuario) {
        Usuarios usuario = buscarPorId(idUsuario);
        if (usuario == null) {
            System.out.println("No existe un usuario con ese ID.");
            return;
        }
        if (usuario.getCargo().equalsIgnoreCase("admin") && Usuarios.getUsuariosRegistrados().stream().filter(u -> u.getCargo().equalsIgnoreCase("admin")).count() == 1) {
            System.out.println("No se puede eliminar el único administrador activo.");
            return;
        }
        Usuarios.getUsuariosRegistrados().remove(usuario);
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

    private double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(Utiles.leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor numérico válido.");
            }
        }
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

    private String leerUsuarioDisponible() {
        String usuario;
        do {
            usuario = leerTexto("Usuario: ");
            if (buscarPorUsuario(usuario) != null) {
                System.out.println("Ese nombre de usuario ya está registrado. Ingrese otro.");
            }
        } while (buscarPorUsuario(usuario) != null);
        return usuario;
    }

    private String leerCargo() {
        while (true) {
            String cargo = leerTexto("Cargo (admin/operario/logistica): ");
            try {
                return Usuarios.normalizarCargo(cargo);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void actualizarCargo(Usuarios usuario) {
        String nuevoCargo = leerCargo();
        try {
            usuario.setCargo(nuevoCargo);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
