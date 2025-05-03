package DisenoDePatrones.Modelo;

public class FuerzaOrden {
    private String DNI;
    private String nombre;
    private int edad;
    private String rango;
    private String cargo;
    private String procedencia;

    public FuerzaOrden(String DNI, String nombre, int edad, String rango, String cargo, String procedencia) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.edad = edad;
        this.rango = rango;
        this.cargo = cargo;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
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
    
    
}
