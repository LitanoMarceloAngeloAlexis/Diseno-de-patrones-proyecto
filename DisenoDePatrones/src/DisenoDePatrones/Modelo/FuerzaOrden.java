package DisenoDePatrones.Modelo;

public class FuerzaOrden extends Ciudadano {

    private String rango;
    private String cargo;

    public FuerzaOrden(String DNI, String nombre, String apellido, int edad, String rango, String cargo, String procedencia) {
        super(DNI, nombre, apellido, edad, procedencia);
        this.rango = rango;
        this.cargo = cargo;
    }

    public String getRango() {
        return rango;
    }

    public String getCargo() {
        return cargo;
    }
    
}
