package igu;

import persistencia.DespachoCrud;
import utils.Utiles;


public class MenuPrincipal {
    
    public static void MenuP(String rol){
    
    	DespachoCrud despachoCrud = new DespachoCrud();
    	try {
            while (true) {
                System.out.println("Bienvenido al Menú Principal");
                
                if (rol.equals("admin")) {
                    System.out.println("1.- Ver despachos");
                    System.out.println("2.- Eliminar despacho");
                    System.out.println("3.- Gestionar usuarios");
                    System.out.println("0.- Salir");
                } else if (rol.equals("operario")) {
                	System.out.println("1.- Cargar despacho");
                    System.out.println("2.- Ver despachos");
                    System.out.println("3.- Modificar despacho");
                    System.out.println("0.- Salir");
				} else {
                    System.out.println("1- Ver despachos");
                    System.out.println("0.- Salir");
				}
                int seleccion = Utiles.leerEntero();

                
                switch (seleccion) {
                    case 1:
                        if (rol.equals("admin") || rol.equals("logistica")) {
                            despachoCrud.listarDespachos();
                        } else if (rol.equals("operario")) {
                            despachoCrud.crearDespacho();
                        } else {
                            despachoCrud.listarDespachos();
                        }
                        break;
                    case 2:
                    	if (rol.equals("admin")) {
                            despachoCrud.eliminarDespacho();
                        } else if (rol.equals("operario")) {
                            despachoCrud.listarDespachos();
                        } else {
                            despachoCrud.listarDespachos();
                        }
                    	break;
                    case 3:
                    	if (rol.equals("admin")) {
                            MenuUsuario.MenuU();
                        } else if (rol.equals("operario")) {
                            despachoCrud.actualizarDespacho();
                        } else {
                            System.out.println("Opción no disponible para este rol.");
                        }
                    	break;
                    case 0:
                        System.exit(0);
                        return;
                    default:
                    	System.out.println("Opción inválida. Seleccione una opción del menú.");
                        break;
                }


            }
        } catch (Exception e) {
        	System.out.println("Debe introducir un número y no una cadena de texto");
        }
        
    }
}
