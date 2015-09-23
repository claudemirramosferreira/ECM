package br.interactive.ecm.model.dto.seguranca;

public class UsuarioSimplesDTO {
    
    private Long id;
    
    private String txNome;

    public UsuarioSimplesDTO() {
    }

    public UsuarioSimplesDTO(Long id, String txNome) {
        this.id = id;
        this.txNome = txNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxNome() {
        return txNome;
    }

    public void setTxNome(String txNome) {
        this.txNome = txNome;
    }
    
}
