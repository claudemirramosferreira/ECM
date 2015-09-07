package br.interactive.ecm.model.dao.gerais;

import br.interactive.ecm.model.dao.AbstractDAO;
import br.interactive.ecm.model.dto.gerais.TipoDocumentoDTO;
import br.interactive.ecm.model.entity.TipoDocumento;
import br.interactive.ecm.model.entity.TipoDocumento_;
import br.interactive.ecm.response.GridResponse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
    
    /**
     * Retorna todas os tipos de documentos.
     *
     * @return TipoDocumentoDTO
     */
    public GridResponse<TipoDocumentoDTO> getByDescricao(String descricao, String situacao, int[] range) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TipoDocumentoDTO> queryDTO = cb.createQuery(TipoDocumentoDTO.class);

        Root<TipoDocumento> root = queryDTO.from(TipoDocumento.class);

        queryDTO.select(cb.construct(TipoDocumentoDTO.class, 
                select(root, TipoDocumento_.idTipoDocumento, 
                             TipoDocumento_.txTipoDocumento, 
                             TipoDocumento_.csAtivo)));
        
        
        final List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(cb.lower(root.get(TipoDocumento_.txTipoDocumento)), "%" + descricao.toLowerCase() + "%"));
        
        switch (situacao) {
            case "T":
                predicates.add(cb.isTrue(root.get(TipoDocumento_.csAtivo)));
                break;
            case "F":
                predicates.add(cb.isFalse(root.get(TipoDocumento_.csAtivo)));
                break;
        }
        
        queryDTO.where(super.addPredicate(predicates));

        queryDTO.orderBy(cb.asc(root.get(TipoDocumento_.txTipoDocumento)));

        Query query = entityManager.createQuery(queryDTO);

        int count = query.getResultList().size();
        
        super.setRange(query, range);
        return new GridResponse<>(query.getResultList(), count);
    }
    
}
