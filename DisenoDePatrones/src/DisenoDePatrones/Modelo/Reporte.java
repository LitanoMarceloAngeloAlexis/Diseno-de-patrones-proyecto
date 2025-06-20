package DisenoDePatrones.Modelo;

public class Reporte implements Cloneable{
    
    private Ciudadano autor;
    private String hora;
    private String fecha;
    private String motivo;
    private String descripcion;

    public Reporte(Ciudadano autor, String hora, String fecha, String motivo, String descripcion) {
        this.autor = autor;
        this.hora = hora;
        this.fecha = fecha;
        this.motivo = motivo;
        this.descripcion = descripcion;
    }

    public Ciudadano getAutor() {
        return autor;
    }

    public String getHora() {
        return hora;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public Reporte clone() {
        try {
            return (Reporte) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clonación no soportada", e);
        }
    }
    
    
}
