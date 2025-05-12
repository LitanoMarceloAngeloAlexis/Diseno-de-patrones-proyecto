package DisenoDePatrones.Modelo;

public class DelincuenciaDistritos {
    private int id;
    private String departamento;
    private String distrito;
    private double tasaDelincuencia;
    private String nivelRiesgo;
    private int poblacionAproximada;

    public DelincuenciaDistritos(int id, String departamento, String distrito,
                                 double tasaDelincuencia, String nivelRiesgo, int poblacionAproximada) {
        this.id = id;
        this.departamento = departamento;
        this.distrito = distrito;
        this.tasaDelincuencia = tasaDelincuencia;
        this.nivelRiesgo = nivelRiesgo;
        this.poblacionAproximada = poblacionAproximada;
    }

    public int getId() { return id; }

    public String getDepartamento() { return departamento; }

    public String getDistrito() { return distrito; }

    public double getTasaDelincuencia() { return tasaDelincuencia; }

    public String getNivelRiesgo() { return nivelRiesgo; }

    public int getPoblacionAproximada() { return poblacionAproximada; }

}
