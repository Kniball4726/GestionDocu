package gestiondocu;
import igu.Login;
import igu.MenuPrincipal;
public class Main {
    public static void main(String[] args) {
        try {
            String rol = Login.loguear();
            if (rol != null) {
                MenuPrincipal.MenuP(rol);
            }else{
                System.out.println("Inicio de sesión fallido.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}

