package DisenoDePatrones.Controlador;

import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.IAgentePublico;
import DisenoDePatrones.Modelo.Reglamento;
import java.util.List;

public class AgentePublicoService extends CiudadanoService implements IAgentePublico{
    
    Reglamento reglamento = Reglamento.getInstancia();

    protected AgentePublicoService(List<Ciudadano> ciudadanos, Ciudadano ciudadanoActual) {
        super(ciudadanos, ciudadanoActual);
    }

    @Override
    public void VisualizarReglamentos() {
        System.out.println(reglamento.getContenido());
        setHistorialInteraccion(" - VISUALIZO EL REGLAMENTO");
    }
    
    @Override
    public void modificarReglamento(String nuevoContenido) {
        reglamento.modificar(nuevoContenido);
        setHistorialInteraccion(" - MODIFICO EL REGLAMENTO");
    }
    
}
