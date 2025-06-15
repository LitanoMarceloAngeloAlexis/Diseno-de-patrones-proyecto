package DisenoDePatrones.Modelo;

public class FuerzaOrden extends Ciudadano implements Observador{

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

    @Override
    public void recibirNotificacion(String mensaje) {
        System.out.println(" => Fuerza del orden " + getNombre() + " ha recibido una notificacion: " + mensaje);
    }
    
}
