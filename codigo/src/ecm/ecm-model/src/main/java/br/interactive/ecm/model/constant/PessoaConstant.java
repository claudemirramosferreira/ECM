package br.interactive.ecm.model.constant;

/**
 * Classe enumerada que define o csPessoa de pessoa.
 * 
 * @author paulo.sena
 */
public enum PessoaConstant {


    /**
     * Item do enum.
     */
    MASTER( (short) 1, "Master" ),

    ;

    /**
     * Atributo de identifica��o do domain.
     */
    private short id;

    /**
     * Atributo de descri��o do domain.
     */
    private String descricao;

    /**
     * Lista de valores para utilizar nos comboboxes em telas de edicao/inser��o.
     */
    private static final PessoaConstant[] VALORES_EDICAO = { PessoaConstant.values()[0], PessoaConstant.values()[1] };

    /**
     * Construtor.
     * 
     * @param id
     * @param descricao
     * @param numero
     */
    private PessoaConstant( short id, String descricao ) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * M�todo que pesquisa um tipo de Pessoa.
     * 
     * @param id
     *            .
     * @return item do tipo PessoaDomain.
     */
    public PessoaConstant getEnum( int id ) {
        for ( PessoaConstant u : PessoaConstant.values() ) {
            if ( u.getId() == id ) {
                return u;
            }
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    public void setId( short id ) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public static PessoaConstant[] getValoresEdicao() {
        return PessoaConstant.VALORES_EDICAO;
    }

}
