/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.Modelo.AgentePublico;
import DisenoDePatrones.Modelo.Ciudadano;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class ProxyAgentePublicoService extends AgentePublicoService {
    private AgentePublicoService servicioReal;
    private Ciudadano usuario;
    
    public ProxyAgentePublicoService(List<Ciudadano> lista, Ciudadano ciudadanoActual, AgentePublicoService servicioReal) {
        super(lista, ciudadanoActual);
        this.servicioReal = servicioReal;
        this.usuario = getCiudadanoActual();
    }
    
    @Override
    public void VisualizarReglamentos() {
        if (usuario instanceof AgentePublico) {
            servicioReal.VisualizarReglamentos();
        } else {
            throw new SecurityException("Acceso denegado: no es un funcionario.");
        }
    }
    
}
