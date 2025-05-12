package DisenoDePatrones.Controlador;

import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.IHabitante;
import java.util.List;

public class HabitanteService extends CiudadanoService implements IHabitante{
    
    public HabitanteService(List<Ciudadano> ciudadanos, String DNIActual) {
        super(ciudadanos, DNIActual);
    }
    
    @Override
    public void ParticiparEncuestas() {
        System.out.println("------------ ENTRANDO AL PORTAL DE ENCUESTAS: ------------");
        System.out.println("    NO HAY NUEVAS ENCUESTAS DISPONIBLES PARA :" + this.getCiudadanoActual().getNombre());
        System.out.println("----------------------------------------------------------");
        
        setHistorialInteraccion(" - VISUALIZO DATOS POBLACIONALES");

    }
    
}
