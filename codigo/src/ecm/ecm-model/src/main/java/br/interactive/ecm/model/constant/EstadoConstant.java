package br.interactive.ecm.model.constant;

import br.interactive.ecm.util.StringUtil;

/**
 * @author adriana.nascimento
 */
public enum EstadoConstant {

    /**
     *
     */
    ACRE(0, "Acre", "AC"),
    /**
     *
     */
    ALAGOAS(1, "Alagoas", "AL"),
    /**
     *
     */
    AMAPA(2, "Amapá", "AP"),
    /**
     *
     */
    AMAZONAS(3, "Amazonas", "AM"),
    /**
     *
     */
    BAHIA(4, "Bahia", "BA"),
    /**
     *
     */
    CEARA(5, "Ceará", "CE"),
    /**
     *
     */
    DISTRITO_FEDERAL(6, "Distrito Federal", "DF"),
    /**
     *
     */
    ESPIRITO_SANTO(7, "Espírito Santo", "ES"),
    /**
     *
     */
    GOIAS(8, "Goiás", "GO"),
    /**
     *
     */
    MARANHAO(9, "Maranhão", "MA"),
    /**
     *
     */
    MATO_GROSSO(10, "Mato Grosso", "MT"),
    /**
     *
     */
    MATO_GROSSO_DO_SUL(11, "Mato Grosso do Sul", "MS"),
    /**
     *
     */
    MINAS_GERAIS(12, "Minas Gerais", "MG"),
    /**
     *
     */
    PARA(13, "Pará ", "PA"),
    /**
     *
     */
    PARAIBA(14, "Paraíba", "PB"),
    /**
     *
     */
    PARANA(15, "Paraná", "PR"),
    /**
     *
     */
    PERNAMBUCO(16, "Pernambuco", "PE"),
    /**
     *
     */
    PIAUI(17, "Piauí", "PI"),
    /**
     *
     */
    RIO_DE_JANEIRO(18, "Rio de Janeiro", "RJ"),
    /**
     *
     */
    RIO_GRANDE_DO_NORTE(19, "Rio Grande do Norte", "RN"),
    /**
     *
     */
    RIO_GRANDE_DO_SUL(20, "Rio Grande do Sul", "RS"),
    /**
     *
     */
    RONDONIA(21, "Rondônia", "RO"),
    /**
     *
     */
    RORAIMA(22, "Roraima", "RR"),
    /**
     *
     */
    SANTA_CATARINA(23, "Santa Catarina", "SC"),
    /**
     *
     */
    SAO_PAULO(24, "São Paulo", "SP"),
    /**
     *
     */
    SERGIPE(25, "Sergipe", "SE"),
    /**
     *
     */
    TOCANTINS(26, "Tocantins", "TO");

    /**
     * Atributo id.
     */
    private int id;

    /**
     * Atributo descrição.
     */
    private String sigla;

    /**
     * Atributo descrição.
     */
    private String descricao;

    /**
     * @param peso
     * @param descricao
     */
    private EstadoConstant(int id, String descricao, String sigla) {
        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public String getSigla() {
        return this.sigla;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public EstadoConstant getEnum(int id) {
        for (EstadoConstant u : EstadoConstant.values()) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public static EstadoConstant getEnum(String desc) {
        for (EstadoConstant u : EstadoConstant.values()) {
            if (StringUtil.removerAcento(u.getDescricao()).equalsIgnoreCase(StringUtil.removerAcento(desc))) {
                return u;
            }
        }
        return null;
    }

    public static String getDescricaoById(int id) {
        for (EstadoConstant item : EstadoConstant.values()) {
            if (item.getId() == id) {
                return item.getDescricao();
            }
        }
        return null;
    }

    public static String getSiglaById(Integer id) {
        if (id != null) {
            for (EstadoConstant item : EstadoConstant.values()) {
                if (item.getId() == id) {
                    return item.getSigla();
                }
            }
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

}
