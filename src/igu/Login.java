package igu;
import logica.Usuarios;
import utils.*;

public class Login {
    public static String loguear(){
        System.out.println("Intentando iniciar sesión...");
        System.out.println("Indique sus credenciales:");
        System.out.println("\nUsuario: ");
        String usuario = Utiles.leerString();
        System.out.println("\nContraseña: ");
        String contrasena = Utiles.leerString();

        String rol = Usuarios.validarCredenciales(usuario, contrasena);
        if (rol == null) {
            System.out.println("Usuario o contraseña incorrectos.");
            return null;
        }
        return rol;
    }
}
