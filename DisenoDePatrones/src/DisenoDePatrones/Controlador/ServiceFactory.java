
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Controlador.AgentePublicoService;
import DisenoDePatrones.Controlador.CiudadanoService;
import DisenoDePatrones.Controlador.FuerzaOrdenService;
import DisenoDePatrones.Controlador.ProxyAgentePublicoService;
import DisenoDePatrones.Controlador.ProxyFuerzaOrdenService;
import DisenoDePatrones.Modelo.AgentePublico;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.FuerzaOrden;
import DisenoDePatrones.Modelo.Habitante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public static List<Ciudadano> cargarCiudadanosSinDuplicados(ExecProcedures exec) {
        Map<String, Ciudadano> ciudadanosPorDni = new HashMap<>();

        for (Ciudadano c : exec.obtenerRegistrosHumanos("FUERZAORDEN")) {
            ciudadanosPorDni.put(c.getDNI(), c);
        }

        for (Ciudadano c : exec.obtenerRegistrosHumanos("AGENTEPUBLICO")) {
            ciudadanosPorDni.putIfAbsent(c.getDNI(), c);
        }

        for (Ciudadano c : exec.obtenerRegistrosHumanos("CIUDADANOCOMUN")) {
            ciudadanosPorDni.putIfAbsent(c.getDNI(), c);
        }

        for (Ciudadano c : exec.obtenerRegistrosHumanos("CIUDADANO")) {
            ciudadanosPorDni.putIfAbsent(c.getDNI(), c);
        }

        return new ArrayList<>(ciudadanosPorDni.values());
    }
}
