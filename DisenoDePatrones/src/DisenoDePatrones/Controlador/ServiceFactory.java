
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.AgentePublico;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.FuerzaOrden;
import DisenoDePatrones.Modelo.Habitante;
import DisenoDePatrones.Vista.AuthVista;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class ServiceFactory {
    public static CiudadanoService crearService(List<Ciudadano> lista, String dni, AuthVista.Roles rol) {
        Ciudadano usuario = buscarUsuario(lista, dni);
        
        switch (rol) {
            case FUERZA_ORDEN:
                if (usuario instanceof FuerzaOrden) {
                    FuerzaOrdenService real = new FuerzaOrdenService(lista, usuario);
                    System.out.println("|| ACCEDIENDO COMO FUERZA DEL ORDEN");
                    return new ProxyFuerzaOrdenService(lista, usuario, real);
                }
                break;

            case AGENTE_PUBLICO:
                if (usuario instanceof AgentePublico) {
                    AgentePublicoService real = new AgentePublicoService(lista, usuario);
                    System.out.println("|| ACCEDIENDO COMO AGENTE PUBLICO");
                    return new ProxyAgentePublicoService(lista, usuario, real);
                }
                break;

            case CIUDADANO:
                System.out.println("|| ACCEDIENDO COMO CIUDADANO");
                return new CiudadanoService(lista, usuario);
        }
        
        JOptionPane.showMessageDialog(null, "El modo seleccionado no es compatible con el tipo de usuario", "Advertencia", JOptionPane.WARNING_MESSAGE);
        throw new IllegalArgumentException("El modo seleccionado no es compatible con el tipo de usuario.");
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
