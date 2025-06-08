package DisenoDePatrones.Controlador;

import DisenoDePatrones.Modelo.AgentePublico;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.FuerzaOrden;
import DisenoDePatrones.Modelo.Habitante;
import DisenoDePatrones.Modelo.ICiudadano;
import DisenoDePatrones.Modelo.Reporte;
import DisenoDePatrones.Modelo.Tramite;
import java.util.HashMap;
import java.util.List;

public class CiudadanoService implements ICiudadano {
    
    private List<Ciudadano> ciudadanos;
    private Ciudadano ciudadanoActual;
    private String historialInteraccion = "////////////////////////////////\nHISTORIAL DE INTERACCION:";

    public CiudadanoService(List<Ciudadano> ciudadanos, Ciudadano ciudadanoActual) {
        this.ciudadanos = ciudadanos;
        //this.ciudadanoActual = locateCiudadanoActual(DNIActual);
        this.ciudadanoActual = ciudadanoActual;
    }

    public Ciudadano getCiudadanoActual() {
        return ciudadanoActual;
    }
    
    public HashMap<String, String> getHashMapInfo(){
        HashMap<String, String> infoHashMap = new HashMap<>();
        infoHashMap.put("dni",ciudadanoActual.getDNI());
        infoHashMap.put("nombres",ciudadanoActual.getNombre());
        infoHashMap.put("apellidos",ciudadanoActual.getApellido());
        infoHashMap.put("edad",String.valueOf(ciudadanoActual.getEdad()));
        infoHashMap.put("procedencia",ciudadanoActual.getProcedencia());
        infoHashMap.put("correo","CORREO SIN ASIGNAR");
        
        return infoHashMap;
    }

    public String getHistorialInteraccion() {
        return historialInteraccion;
    }

    public void setHistorialInteraccion(String historialInteraccion) {
        this.historialInteraccion = this.historialInteraccion + historialInteraccion;
    }
    

    private Ciudadano locateCiudadanoActual(String DNIActual) {
        for (Ciudadano i : ciudadanos) {
            if (i.getDNI().equals(DNIActual)) {
                if (i instanceof Habitante) {
                    System.out.println("-> HABITANTE ENCONTRADO <-");
                } else if(i instanceof AgentePublico){
                   System.out.println("-> AGENTE PUBLICO ENCONTRADO <-");
                } else if(i instanceof FuerzaOrden){
                   System.out.println("-> FUERZA DEL ORDEN ENCONTRADO <-");
                } else {
                    System.out.println("-> CIUDADANO ENCONTRADO <-");
                }
                return i;
            }
        }
    return null;
    }
    
    public Reporte crearReporte(String hora, String fecha, String motivo, String descripcion) {
        return new Reporte(ciudadanoActual, hora, fecha, motivo, descripcion);
    }
    
    @Override
    public void GeneracionReportes(Reporte reporte) {
        String reporteProspect = """
                                 =====================================
                                           REPORTE CIUDADANO
                                 =====================================
                                    DATOS DEL REPORTANTE:
                                        DNI - %S
                                        NOMBRE - %S
                                        APELLIDO - %S
                                        EDAD - %d
                                        PROCEDENCIA - %s
                                 -------------------------------------
                                    HORA:%S         FECHA:%S
                                 
                                    MOTIVO DEL REPORTE:
                                    %s
                                 
                                    DESCRIPCION:
                                    %s
                                 =====================================  
                                 """;
        
        System.out.printf(reporteProspect,reporte.getAutor().getDNI(),
                reporte.getAutor().getNombre(),
                reporte.getAutor().getApellido(),
                reporte.getAutor().getEdad(),
                reporte.getAutor().getProcedencia(),
                reporte.getHora(),
                reporte.getFecha(),
                reporte.getMotivo(),
                reporte.getDescripcion()
        );
        
        historialInteraccion = historialInteraccion + "\n - Genero un nuevo reporte" ;

    }

    @Override
    public void ConsultaEstadoTramites(List<Tramite> tramites) {
                
        for(Tramite i: tramites){
            if(i.getDni().equals(ciudadanoActual.getDNI())){
                System.out.println("_____________________________");
                System.out.println("ID DE TRAMITE: " + i.getId() + "\n | TITULO: " + i.getTitulo() + "\n | ESTADO: " + i.getEstado());
                System.out.println("_____________________________");

            }
        }
        
        historialInteraccion = historialInteraccion + "\n - Consulto estado de tramites" ;

    }

    @Override
    public void VisualizarDatosPersonales() {
        String personalInfProspect = """
                                 =====================================
                                           DATOS PERSONALES
                                 =====================================
                                        DNI - %S
                                        NOMBRE - %S
                                        APELLIDO - %S
                                        EDAD - %d
                                        PROCEDENCIA - %s
                                 =====================================
                                 """;
        
        System.out.printf(personalInfProspect,
                ciudadanoActual.getDNI(),
                ciudadanoActual.getNombre(),
                ciudadanoActual.getApellido(),
                ciudadanoActual.getEdad(),
                ciudadanoActual.getProcedencia());
        
        historialInteraccion = historialInteraccion + "\n - Visualizo datos personales" ;
    }

    @Override
    public void HistorialInteraccion() {
        System.out.println(historialInteraccion);
        System.out.println("////////////////////////////////");
    }
    
}
