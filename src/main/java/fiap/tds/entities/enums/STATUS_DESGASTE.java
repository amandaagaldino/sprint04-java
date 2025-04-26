package fiap.tds.entities.enums;

public enum STATUS_DESGASTE {
    BOAS_CONDINDICOES,
    DESGASTE_SUPERIOR,              //Desgaste na composições superiores dos trilhos, onde os trens movimentam efetivamente as suas rodas.
    DESGASTE_LATERAL,            //Quando sofre pequenas modificações que podem, resultar na quebra do trilho.
    DESGASTE_VERTICAL,          //Desgaste nas partes retilíneas dos trilhos
    DESGASTE_ONDULATORIO,      //FlaMbagem
    PRECISANDO_DE_MANUTENCAO
}
