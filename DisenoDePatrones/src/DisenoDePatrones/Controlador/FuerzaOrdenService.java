package DisenoDePatrones.Controlador;

import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.DelincuenciaDistritos;
import DisenoDePatrones.Modelo.IFuerzaOrden;
import java.util.List;

public class FuerzaOrdenService extends AgentePublicoService implements IFuerzaOrden{

    public FuerzaOrdenService(List<Ciudadano> ciudadanos, Ciudadano ciudadanoActual) {
        super(ciudadanos, ciudadanoActual);
    }

    @Override
    public void SIGDelincuencial(List<DelincuenciaDistritos> listaDelincuenciaDistritoses, String departamento) {
         System.out.println("---- TASA DELINCUENCIAL SEGUN DEPARTAMENTO ----");
        for(DelincuenciaDistritos i:listaDelincuenciaDistritoses){
            if(i.getDepartamento().equalsIgnoreCase(departamento)){
                System.out.println("DEPARTAMENTO DE " + departamento);
                System.out.println("ID: " + i.getId() + 
                        "\nDISTRITO: " + i.getDistrito()+
                        "\nTASA DE DELINCUENCIA: " + i.getTasaDelincuencia() + 
                        "\nPOBLACION APROXIMADA: " + i.getPoblacionAproximada() +
                        "\nNIVEL DE RIESGO: " + i.getNivelRiesgo() 
                );
            }
        }
        
        setHistorialInteraccion(" - SOLICITO UN SIG DELINCUENCIAL");
    }

    @Override
    public void VisualizarDatosPoblacionales(List<Ciudadano> ciudadanos) {
        System.out.println("---- MOSTRANDO INFORMACION DE TODA LA POBLACION ----");
        for(Ciudadano i:ciudadanos){
            System.out.println("| DNI:" + i.getDNI() + 
                    "\nNombre: " + i.getNombre() + 
                    "\nApellido: " + i.getApellido() +
                    "\nEdad: " + i.getEdad() +
                    "\nProcedencia: " + i.getProcedencia());
        }
        
        setHistorialInteraccion(" - VISUALIZO DATOS POBLACIONALES");

    }
 
}
