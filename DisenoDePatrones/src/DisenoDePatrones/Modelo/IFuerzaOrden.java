package DisenoDePatrones.Modelo;

import java.util.List;

public interface IFuerzaOrden {
    void SIGDelincuencial(List<DelincuenciaDistritos> listaDelincuenciaDistritoses, String departamento);
    void VisualizarDatosPoblacionales(List<Ciudadano> ciudadanos);
}
