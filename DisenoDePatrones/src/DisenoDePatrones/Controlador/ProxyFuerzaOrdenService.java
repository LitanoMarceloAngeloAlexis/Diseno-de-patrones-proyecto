package DisenoDePatrones.Controlador;

import DisenoDePatrones.Modelo.AgentePublico;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.DelincuenciaDistritos;
import DisenoDePatrones.Modelo.FuerzaOrden;
import java.util.List;

public class ProxyFuerzaOrdenService extends FuerzaOrdenService{
    private FuerzaOrdenService servicioReal;
    private Ciudadano usuario;

    public ProxyFuerzaOrdenService(List<Ciudadano> ciudadanos, Ciudadano ciudadanoActual,FuerzaOrdenService servicioReal) {
        super(ciudadanos, ciudadanoActual);
        this.servicioReal = servicioReal;
        this.usuario = getCiudadanoActual();
    }
    
    @Override
    public void VisualizarReglamentos() {
        if (usuario instanceof FuerzaOrden) {
            servicioReal.VisualizarReglamentos();
        } else {
            throw new SecurityException("Acceso denegado: no es una fuerza del orden.");
        }
    }
    
    @Override
    public void SIGDelincuencial(List<DelincuenciaDistritos> listaDelincuenciaDistritoses, String departamento) {
        if (usuario instanceof FuerzaOrden) {
            servicioReal.SIGDelincuencial(listaDelincuenciaDistritoses, departamento);
        } else {
            throw new SecurityException("Acceso denegado: no es una fuerza del orden.");
        }
    }
    
    @Override
    public void VisualizarDatosPoblacionales(List<Ciudadano> ciudadanos) { 
        if (usuario instanceof FuerzaOrden) {
            servicioReal.VisualizarDatosPoblacionales(ciudadanos);
        } else {
            throw new SecurityException("Acceso denegado: no es una fuerza del orden.");
        }
    }
}
