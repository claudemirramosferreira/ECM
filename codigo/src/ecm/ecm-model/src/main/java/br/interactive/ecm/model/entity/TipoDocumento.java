package br.interactive.ecm.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "TIPO_DOCUMENTO")
@SequenceGenerator(name = "tipoDocumentoSeq", sequenceName = "TIPO_DOCUMENTO_ID_SEQ", allocationSize = 1)
public class TipoDocumento  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Getter @Setter
    @Id
    @Column(name = "ID_TIPO_DOCUMENTO", unique = true, nullable = false)
    @GeneratedValue(generator = "tipoDocumentoSeq", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Getter @Setter
    @Column(name = "TX_TIPO_DOCUMENTO", nullable = false, length = 50)
    @NotNull
    @Length(max = 50)
    private String txTipoDocumento;
    
    @Getter @Setter
    @Column(name = "CS_ATIVO", nullable = false)
    @NotNull
    @Type(type = "true_false")
    private Boolean csAtivo;
    
    public TipoDocumento(){ }

    public TipoDocumento(Long id, String txTipoDocumento, Boolean csAtivo) {
        this.id = id;
        this.txTipoDocumento = txTipoDocumento;
        this.csAtivo = csAtivo;
    }

    
}
