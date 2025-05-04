package DisenoDePatrones.Modelo;

public class AgentePublico extends Ciudadano{
    
    private String cargo;

    public AgentePublico(String DNI, String nombre, String apellido, String cargo, int edad, String procedencia) {
        super(DNI, nombre, apellido, edad, procedencia);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
     
}
