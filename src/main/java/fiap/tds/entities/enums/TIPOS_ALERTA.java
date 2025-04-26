package fiap.tds.entities.enums;

public enum TIPOS_ALERTA {
    NIVEL_1,   //Monitoramento Regular: Tudo dentro do normal, sem ação específica.
    NIVEL_2,   //Alerta Preventivo: Pequenas variações, avisando a equipe para monitoramento mais próximo.
    NIVEL_3,   //Alerta de Atenção: Agendar manutenção preventiva.
    NIVEL_4,    //Alerta Crítico: Próximo ao limite de segurança, inspeção urgente necessária
    NIVEL_5     //Alerta de Emergência: Estado critico, bloquear trecho e acionar emergência.

}
