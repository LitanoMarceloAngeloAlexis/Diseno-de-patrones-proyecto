package DisenoDePatrones.Modelo;

public class Tramite {
    
    private int id;
    private String dni;
    private String titulo;
    private String estado;

    public Tramite(int id, String dni, String titulo, String estado) {
        this.id = id;
        this.dni = dni;
        this.titulo = titulo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEstado() {
        return estado;
    }
}
