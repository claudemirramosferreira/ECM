package br.interactive.ecm.service.gerais;

import br.interactive.ecm.exception.BusinessException;
import br.interactive.ecm.internationalization.MessageResolver;
import br.interactive.ecm.message.EmptyMessage;
import br.interactive.ecm.message.ErrorMessage;
import br.interactive.ecm.message.InfoMessage;
import br.interactive.ecm.message.Message;
import br.interactive.ecm.model.dao.gerais.TipoDocumentoDAO;
import br.interactive.ecm.model.dto.gerais.TipoDocumentoDTO;
import br.interactive.ecm.model.entity.TipoDocumento;
import br.interactive.ecm.response.GridResponse;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TipoDocumentoService {

    @Inject
    private TipoDocumentoDAO dao;

    public GridResponse<TipoDocumentoDTO> buscarPorDescricaoOuSituacao(String descricao, String situacao, int[] range) {
        GridResponse<TipoDocumentoDTO> grid = dao.buscarPorDescricaoOuSituacao(descricao, situacao, range);
        if (grid.getTotalProperty() == 0) {
            throw new BusinessException(new EmptyMessage("nenhum.registro"));
        }
        return grid;
    }

    public String inserir(List<TipoDocumento> TipoDocumentos) {
        try {
            for (TipoDocumento i : TipoDocumentos) {
                dao.persist(i);
            }
            return "msg.salvo";
        } catch (RuntimeException e) {
            throw new BusinessException(new ErrorMessage("erro.ao.salvar.registro"));
        }
    }

    /**
     * Remover TipoDocumento.
     *
     * @param id the id TipoDocumento.
     * @return the long.
     */
    public Message remover(Long id) {
        dao.remove(id);
        return new InfoMessage("msg.excluido");
    }

    /**
     * Ativa inativa TipoDocumento.
     *
     * @param id the id TipoDocumento.
     * @return the long.
     */
    public Long ativarOuInativar(Long id) {
        TipoDocumento TipoDocumento = dao.getByPrimaryKey(id);
        if (TipoDocumento.getCsAtivo()) {
            TipoDocumento.setCsAtivo(false);
        } else {
            TipoDocumento.setCsAtivo(true);
        }

        return id;
    }

    public String buscarPorDescricao(String descricao) {

        if (dao.getTipoDocumentoByDescricao(descricao) != null) {
            throw new BusinessException(new ErrorMessage(MessageResolver.interpolate("msgConstraints.AK_TX_TIPO_DOCUMENTO")));
        }
        return "";
    }

    /**
     * Gets the TipoDocumento by id.
     *
     * @param id the id TipoDocumento
     * @return the TipoDocumento by id
     */
    public TipoDocumentoDTO buscarPorId(Long id) {
        return dao.getTipoDocumentoById(id);
    }

    /**
     * Update TipoDocumento.
     *
     * @param TipoDocumento
     * @return the string
     */
    public String alterar(TipoDocumento TipoDocumento) {
        try {
            dao.merge(TipoDocumento);
            return "msg.salvo";
        } catch (RuntimeException e) {
            throw new BusinessException(new ErrorMessage("erro.ao.salvar.registro"));
        }
    }

    public List<TipoDocumentoDTO> buscarTodos() {
        return dao.buscarTodos();
    }

}
