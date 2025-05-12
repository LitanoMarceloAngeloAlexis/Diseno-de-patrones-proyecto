package DisenoDePatrones.Modelo;

import java.util.List;

public interface ICiudadano {
    void GeneracionReportes(Reporte reporte);
    void ConsultaEstadoTramites(List<Tramite> tramites);
    void VisualizarDatosPersonales();
    void HistorialInteraccion();
}
