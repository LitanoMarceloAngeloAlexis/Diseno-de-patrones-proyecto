package DisenoDePatrones.Modelo;

public class AgentePublico {
    private String DNI;
    private String nombre;
    private String cargo;
    private int edad;
    private String procedencia;

    public AgentePublico(String DNI, String nombre, String cargo, int edad, String procedencia) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.cargo = cargo;
        this.edad = edad;
        this.procedencia = procedencia;
    }
    
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }   
    
    
    
}
