
package DisenoDePatrones.Modelo;

public class Ciudadano{
    
    private String DNI;
    private String nombre;
    private String apellido;
    private int edad;
    private String procedencia;

    public Ciudadano(String DNI, String nombre, String apellido, int edad, String procedencia) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.procedencia = procedencia;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getProcedencia() {
        return procedencia;
    }

}
