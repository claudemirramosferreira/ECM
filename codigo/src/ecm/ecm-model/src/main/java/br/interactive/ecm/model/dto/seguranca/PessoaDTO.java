package br.interactive.ecm.model.dto.seguranca;

import java.util.Calendar;

public class PessoaDTO {

    private Long id;

    private String csDeficiencia;
    private Integer csPapel;
    private Integer csPessoa;
    private String csSexo;
    private Calendar dtDataNascimento;
    private Long idSetor;
    private Integer nbEstadoEmissor;
    private String txCelular;
    private String txCpf;
    private String txEmail;
    private String txFoneComercial;
    private String txFoneResidencial;
    private String txNome;
    private String txRegistroProfissional;
    private String txRg;
    private Long unidadeOrganizacional;
    private Long usuario;

    public PessoaDTO() {
    }

    public PessoaDTO(Long id, String txNome) {
        this.id = id;
        this.txNome = txNome;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the csDeficiencia
     */
    public String getCsDeficiencia() {
        return csDeficiencia;
    }

    /**
     * @param csDeficiencia the csDeficiencia to set
     */
    public void setCsDeficiencia(String csDeficiencia) {
        this.csDeficiencia = csDeficiencia;
    }

    /**
     * @return the csPapel
     */
    public Integer getCsPapel() {
        return csPapel;
    }

    /**
     * @param csPapel the csPapel to set
     */
    public void setCsPapel(Integer csPapel) {
        this.csPapel = csPapel;
    }

    /**
     * @return the csPessoa
     */
    public Integer getCsPessoa() {
        return csPessoa;
    }

    /**
     * @param csPessoa the csPessoa to set
     */
    public void setCsPessoa(Integer csPessoa) {
        this.csPessoa = csPessoa;
    }

    /**
     * @return the csSexo
     */
    public String getCsSexo() {
        return csSexo;
    }

    /**
     * @param csSexo the csSexo to set
     */
    public void setCsSexo(String csSexo) {
        this.csSexo = csSexo;
    }

    /**
     * @return the dtDataNascimento
     */
    public Calendar getDtDataNascimento() {
        return dtDataNascimento;
    }

    /**
     * @param dtDataNascimento the dtDataNascimento to set
     */
    public void setDtDataNascimento(Calendar dtDataNascimento) {
        this.dtDataNascimento = dtDataNascimento;
    }

    /**
     * @return the idSetor
     */
    public Long getIdSetor() {
        return idSetor;
    }

    /**
     * @param idSetor the idSetor to set
     */
    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
    }

    /**
     * @return the nbEstadoEmissor
     */
    public Integer getNbEstadoEmissor() {
        return nbEstadoEmissor;
    }

    /**
     * @param nbEstadoEmissor the nbEstadoEmissor to set
     */
    public void setNbEstadoEmissor(Integer nbEstadoEmissor) {
        this.nbEstadoEmissor = nbEstadoEmissor;
    }

    /**
     * @return the txCelular
     */
    public String getTxCelular() {
        return txCelular;
    }

    /**
     * @param txCelular the txCelular to set
     */
    public void setTxCelular(String txCelular) {
        this.txCelular = txCelular;
    }

    /**
     * @return the txCpf
     */
    public String getTxCpf() {
        return txCpf;
    }

    /**
     * @param txCpf the txCpf to set
     */
    public void setTxCpf(String txCpf) {
        this.txCpf = txCpf;
    }

    /**
     * @return the txEmail
     */
    public String getTxEmail() {
        return txEmail;
    }

    /**
     * @param txEmail the txEmail to set
     */
    public void setTxEmail(String txEmail) {
        this.txEmail = txEmail;
    }

    /**
     * @return the txFoneComercial
     */
    public String getTxFoneComercial() {
        return txFoneComercial;
    }

    /**
     * @param txFoneComercial the txFoneComercial to set
     */
    public void setTxFoneComercial(String txFoneComercial) {
        this.txFoneComercial = txFoneComercial;
    }

    /**
     * @return the txFoneResidencial
     */
    public String getTxFoneResidencial() {
        return txFoneResidencial;
    }

    /**
     * @param txFoneResidencial the txFoneResidencial to set
     */
    public void setTxFoneResidencial(String txFoneResidencial) {
        this.txFoneResidencial = txFoneResidencial;
    }

    /**
     * @return the txNome
     */
    public String getTxNome() {
        return txNome;
    }

    /**
     * @param txNome the txNome to set
     */
    public void setTxNome(String txNome) {
        this.txNome = txNome;
    }

    /**
     * @return the txRegistroProfissional
     */
    public String getTxRegistroProfissional() {
        return txRegistroProfissional;
    }

    /**
     * @param txRegistroProfissional the txRegistroProfissional to set
     */
    public void setTxRegistroProfissional(String txRegistroProfissional) {
        this.txRegistroProfissional = txRegistroProfissional;
    }

    /**
     * @return the txRg
     */
    public String getTxRg() {
        return txRg;
    }

    /**
     * @param txRg the txRg to set
     */
    public void setTxRg(String txRg) {
        this.txRg = txRg;
    }

    /**
     * @return the unidadeOrganizacional
     */
    public Long getUnidadeOrganizacional() {
        return unidadeOrganizacional;
    }

    /**
     * @param unidadeOrganizacional the unidadeOrganizacional to set
     */
    public void setUnidadeOrganizacional(Long unidadeOrganizacional) {
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    /**
     * @return the usuario
     */
    public Long getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

}
