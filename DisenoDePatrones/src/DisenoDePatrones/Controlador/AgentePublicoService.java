package DisenoDePatrones.Controlador;

import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.IAgentePublico;
import java.util.List;

public class AgentePublicoService extends CiudadanoService implements IAgentePublico{

    public AgentePublicoService(List<Ciudadano> ciudadanos, Ciudadano ciudadanoActual) {
        super(ciudadanos, ciudadanoActual);
    }

    @Override
    public void VisualizarReglamentos() {
        String reglamento = """
        ================================================
                   REGLAMENTO DEL AGENTE PÚBLICO
        ================================================

        1. Cumplimiento de la Ley:
           Todo agente público deberá cumplir y hacer cumplir la
           legislación vigente, actuando siempre con apego a los
           principios constitucionales.

        2. Ética Profesional:
           El agente debe actuar con honestidad, integridad y
           responsabilidad, evitando cualquier tipo de corrupción,
           favoritismo o abuso de poder.

        3. Confidencialidad:
           Está prohibido divulgar información confidencial o sensible
           obtenida en el ejercicio de sus funciones, salvo autorización
           legal.

        4. Trato Digno:
           Los ciudadanos deben ser tratados con respeto, igualdad y sin
           discriminación alguna, garantizando siempre un servicio justo
           y eficiente.

        5. Uso Responsable de Recursos:
           Se deberá hacer uso adecuado y responsable de los bienes
           públicos asignados, evitando su utilización para fines
           personales.

        6. Transparencia:
           Toda actuación del agente público debe ser clara y
           justificada, permitiendo la supervisión y el control
           ciudadano.

        7. Responsabilidad Disciplinaria:
           El incumplimiento de este reglamento podrá conllevar sanciones
           administrativas, civiles o penales según la gravedad de la falta.

        ================================================
              Ministerio de Asuntos Públicos - 2025
        ================================================
        """;

        System.out.println(reglamento);
        
        setHistorialInteraccion(" - VISUALIZO EL REGLAMENTO");

    }
    
}
