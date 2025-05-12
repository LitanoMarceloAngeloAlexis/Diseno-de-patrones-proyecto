
package DisenoDePatrones.Modelo;

public class RegistroFactory {

    public static Object crearRegistro(String tableName, String dni, String nombre, String apellido, int edad, String procedencia, String cargo, String rango) {
        switch (tableName.toUpperCase()) {
            case "CIUDADANO":
                return new Ciudadano(dni, nombre, apellido, edad, procedencia);
            case "AGENTEPUBLICO":
                return new AgentePublico(dni, nombre, apellido, cargo, edad, procedencia);
            case "FUERZAORDEN":
                return new FuerzaOrden(dni, nombre, apellido, edad, rango, cargo, procedencia);
            case "CIUDADANOCOMUN":
                return new Habitante(dni, nombre, apellido, edad, procedencia);
            default:
                throw new IllegalArgumentException("Tipo de registro no reconocido: " + tableName);
        }
    }
}
