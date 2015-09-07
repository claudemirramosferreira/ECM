package br.interactive.ecm.model.dao.gerais;

import br.interactive.ecm.model.dao.AbstractDAO;
import br.interactive.ecm.model.dto.gerais.TipoDocumentoDTO;
import br.interactive.ecm.model.entity.TipoDocumento;
import br.interactive.ecm.model.entity.TipoDocumento_;
import br.interactive.ecm.response.GridResponse;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * DAO para a entidade SysVersao.
 */
public class TipoDocumentoDAO extends AbstractDAO<TipoDocumento> {

    /**
     * Retorna todas os tipos de documentos.
     *
     * @return TipoDocumentoDTO
     */
    public GridResponse<TipoDocumentoDTO> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TipoDocumentoDTO> queryDTO = cb.createQuery(TipoDocumentoDTO.class);

        Root<TipoDocumento> root = queryDTO.from(TipoDocumento.class);

        queryDTO.select(cb.construct(TipoDocumentoDTO.class, 
                select(root, TipoDocumento_.idTipoDocumento, 
                             TipoDocumento_.txTipoDocumento, 
                             TipoDocumento_.csAtivo)))
                .orderBy(cb.asc(root.get(TipoDocumento_.txTipoDocumento)));

        Query query = entityManager.createQuery(queryDTO);

        int[] range = {1, 5};   // FIXME : exemplo de paginação
        super.setRange(query, range);
        int count = query.getResultList().size();
        return new GridResponse<>(query.getResultList(), count);
    }
    
}
