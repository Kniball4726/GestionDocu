package igu;

import utils.Utiles;
import persistencia.UsuariosCrud;

public class MenuUsuario {
    public static void MenuU(){
        UsuariosCrud usuariosCrud = new UsuariosCrud();
        while (true) {
            System.out.println("\n--- Gestión de usuarios ---");
            System.out.println("1.- Crear usuario");
            System.out.println("2.- Listar usuarios");
            System.out.println("3.- Buscar usuario");
            System.out.println("4.- Actualizar usuario");
            System.out.println("5.- Eliminar usuario");
            System.out.println("0.- Volver");
            int opcionUsuario = Utiles.leerEntero();
               switch (opcionUsuario) {
                   case 1:
                       usuariosCrud.crearUsuarioDesdeConsola();
                        break;
                    case 2:
                        usuariosCrud.listarUsuarios();
                        break;
                    case 3:
                        usuariosCrud.buscarUsuario();
                        break;
                    case 4:
                        usuariosCrud.actualizarUsuario();
                        break;
                    case 5:
                        usuariosCrud.eliminarUsuario();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opción inválida.");
                    }
        }
    }
}
