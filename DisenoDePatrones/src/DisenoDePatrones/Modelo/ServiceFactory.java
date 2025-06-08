
package DisenoDePatrones.Modelo;

import DisenoDePatrones.Controlador.AgentePublicoService;
import DisenoDePatrones.Controlador.CiudadanoService;
import DisenoDePatrones.Controlador.FuerzaOrdenService;
import DisenoDePatrones.Controlador.ProxyAgentePublicoService;
import DisenoDePatrones.Controlador.ProxyFuerzaOrdenService;
import java.util.List;

public class ServiceFactory {
    public static CiudadanoService crearService(List<Ciudadano> lista, String dni) {
        Ciudadano usuario = buscarUsuario(lista, dni);

        if (usuario instanceof FuerzaOrden) {
            FuerzaOrdenService real = new FuerzaOrdenService(lista, usuario);
            return new ProxyFuerzaOrdenService(lista, usuario, real);
        } else if (usuario instanceof AgentePublico) {
            AgentePublicoService real = new AgentePublicoService(lista, usuario);
            return new ProxyAgentePublicoService(lista, usuario, real);
        } else {
            return new CiudadanoService(lista, usuario);
        }
    }

    private static Ciudadano buscarUsuario(List<Ciudadano> lista, String DNIActual) {
        for (Ciudadano i : lista) {
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
        throw new IllegalArgumentException("Usuario no encontrado");
    }
}
