package persistencia;

import java.util.ArrayList;
import java.util.List;

import logica.Despacho;
import logica.Mostrable;
import logica.Remito;

public class DocumentoCrud {
     private final DespachoCrud despachoCrud;

    public DocumentoCrud(DespachoCrud despachoCrud) {
        if (despachoCrud == null) {
            System.out.println("El CRUD de despachos no puede ser nulo.");
        }
        this.despachoCrud = despachoCrud;
    }

    public List<Mostrable> obtenerDocumentos() {
        List<Mostrable> documentos = new ArrayList<>();
        documentos.addAll(despachoCrud.getDespachos());
        return List.copyOf(documentos);
    }

    public void listarDocumentos() {
        List<Mostrable> documentos = obtenerDocumentos();
        if (documentos.isEmpty()) {
            System.out.println("No hay documentos cargados.");
            return;
        }

        for (Mostrable documento : documentos) {
            String tipo = null;
            if (documento instanceof Despacho) {
                tipo = "DESPACHO";
            } else if (documento instanceof Remito) {
                tipo = "REMITO";
            } else {
                System.out.println("No se encuentra ningun tipo de documento");
            }

            System.out.println("Tipo de documento: " + tipo);
            documento.mostrarInformacion();
            System.out.println("------------------------------");
        }
    }
}
