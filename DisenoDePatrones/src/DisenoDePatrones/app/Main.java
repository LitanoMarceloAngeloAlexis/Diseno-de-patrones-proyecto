package DisenoDePatrones.app;

import DisenoDePatrones.BaseDeDatos.DatabaseConnection;
import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.AgentePublico;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.Reporte;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        
        System.out.println("AREA DE PRUEBA==============================================================");
        System.out.println("-----Conexion a base de datos (SINGLETON)------------------");

        DatabaseConnection dbc = DatabaseConnection.getInstance();
        Connection conn = dbc.getConnection();
        
        ExecProcedures exec = new ExecProcedures(conn);
        
        List<Object> listaAgentes = exec.obtenerRegistrosHumanos("AGENTEPUBLICO");
        List<Object> listaCiudadanos = exec.obtenerRegistrosHumanos("CIUDADANO");
        List<Object> listaCiudadanosComunes = exec.obtenerRegistrosHumanos("CIUDADANOCOMUN");


        System.out.println("-----Mostrando Agentes Publicos---------------------------");

        for(Object i : listaAgentes){
                AgentePublico agentePublico = (AgentePublico) i;
                System.out.println("Nombre :" + agentePublico.getNombre());               
                System.out.println("Procedencia :" + agentePublico.getProcedencia());
                System.out.println("Cargo :" + agentePublico.getCargo());
                System.out.println("-");
        }
        
        System.out.println("-----Mostrando Ciudadanos--------------------------------");

        for(Object i : listaCiudadanos){
                Ciudadano ciudadano = (Ciudadano) i;
                System.out.println("Nombre :" + ciudadano.getNombre());               
                System.out.println("Procedencia :" + ciudadano.getProcedencia());
                System.out.println("Edad :" + ciudadano.getEdad());
                System.out.println("-");
                
        }
        
        System.out.println("-----Mostrando Ciudadanos Comunes--------------------------------");

        for(Object i : listaCiudadanosComunes){
                Ciudadano ciudadano = (Ciudadano) i;
                System.out.println("Nombre :" + ciudadano.getNombre());               
                System.out.println("Procedencia :" + ciudadano.getProcedencia());
                System.out.println("Edad :" + ciudadano.getEdad());
                System.out.println("-");
                
        }
        
        System.out.println("-----CLONANDO REPORTES (PROTOTYPE)------------------");

        Reporte original = new Reporte((Ciudadano) listaCiudadanos.get(1), "5pm","4/5/12","ROBO","Robo en la avenida" );
        Reporte copia = original.clone();

        copia.setDescripcion("Corrupcion distrital");
        
        System.out.println("REPORTE ORIGINAL| Autor:" + original.getAutor().getNombre() + ", Descripcion: " +original.getDescripcion() );
        System.out.println("REPORTE COPIA| Autor:" + copia.getAutor().getNombre() + ", Descripcion: " + copia.getDescripcion() );
    }
}
