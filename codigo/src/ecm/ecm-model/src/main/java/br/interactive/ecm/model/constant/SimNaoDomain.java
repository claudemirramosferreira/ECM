package br.interactive.ecm.model.constant;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Tipo enumerado para a representa��o dos estados padr�es a serem utilizados
 * dentro de combobox que represente campo "csStatus".
 *
 * @author dfernandes
 */
public enum SimNaoDomain {

    /**
     * Item do enum.
     */
    TODOS(0, "todos", null),
    /**
     * Item do enum.
     */
    SIM(1, "sim", true),
    /**
     * Item do enum.
     */
    NAO(2, "nao", false);

    /**
     * Identificador do enumarado.
     */
    private int id;

    /**
     * Descri��o do enumerado.
     */
    private String descricao;

    /**
     * Valor boleano do enumerado.
     */
    private Boolean boleano;

    /**
     * Constru��o de lista a ser utiliza em p�ginas de pesquisa.
     */
    private static final SimNaoDomain[] VALORES_PESQUISA = {SimNaoDomain.TODOS, SimNaoDomain.SIM, SimNaoDomain.NAO};

    /**
     * Constru��o de lista a ser utilizada em p�ginas de inser��o e atualiza��o.
     */
    private static final SimNaoDomain[] VALORES_EDICAO = {SimNaoDomain.SIM, SimNaoDomain.NAO};

    /**
     * Construtor do enum onde o identificador, a descri��o e o valor boleano
     * s�o informados.
     *
     * @param id
     * @param descricao
     * @param b
     */
    private SimNaoDomain(int id, String descricao, Boolean b) {
        this.id = id;
        this.descricao = descricao;
        this.boleano = b;
    }

    /**
     * Obt�m o status do enumerado cujo id � passado por par�metro.
     *
     * @param id
     * @return tipo enumerado.
     */
    public static SimNaoDomain getStatus(int id) {
        for (SimNaoDomain t : SimNaoDomain.values()) {
            if (t.id == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * Obt�m o status do enumerado cujo id � passado por par�metro.
     *
     * @param id
     * @return tipo enumerado.
     */
    public static SimNaoDomain getStatus(String descricao) {
        for (SimNaoDomain t : SimNaoDomain.values()) {
            if (t.getDescricao().equals(descricao)) {
                return t;
            }
        }
        return null;
    }

    /**
     * @param boleano
     * @return Enum
     */
    public static SimNaoDomain getEnumFromBoolean(Boolean boleano) {
        for (SimNaoDomain t : SimNaoDomain.getValoresEdicao()) {
            if (t.boleano.equals(boleano)) {
                return t;
            }
        }
        return null;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        ResourceBundle r = ResourceBundle.getBundle("br.fpf.ehs.properties.sim_nao.msgSimNao", Locale.getDefault());
        return r.getString(this.descricao);
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getBoleano() {
        return this.boleano;
    }

    public void setBoleano(Boolean boleano) {
        this.boleano = boleano;
    }

    public static SimNaoDomain[] getValoresPesquisa() {
        return SimNaoDomain.VALORES_PESQUISA;
    }

    public static SimNaoDomain[] getValoresEdicao() {
        return SimNaoDomain.VALORES_EDICAO;
    }

    public boolean isSim() {
        return this == SIM;
    }

    public boolean isNao() {
        return this == NAO;
    }

    public boolean isTodos() {
        return this == TODOS;
    }

    public String getDescricaoEmail() {
        ResourceBundle r = ResourceBundle.getBundle("br.fpf.ehs.properties.sim_nao.msgSimNao", new Locale("br"));
        return r.getString(this.descricao);
    }
}
