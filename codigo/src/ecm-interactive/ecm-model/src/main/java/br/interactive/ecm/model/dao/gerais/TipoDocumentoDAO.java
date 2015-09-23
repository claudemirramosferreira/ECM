package br.interactive.ecm.model.dao.gerais;

import br.interactive.ecm.model.dao.AbstractDAO;
import br.interactive.ecm.response.GridResponse;
import br.interactive.ecm.model.dto.gerais.TipoDocumentoDTO;
import br.interactive.ecm.model.entity.TipoDocumento;
import br.interactive.ecm.model.entity.TipoDocumento_;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class TipoDocumentoDAO extends AbstractDAO<TipoDocumento> {

    public GridResponse<TipoDocumentoDTO> buscarPorDescricaoOuSituacao(String descricao, String situacao, int[] range) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TipoDocumentoDTO> queryDTO = cb.createQuery(TipoDocumentoDTO.class);

        Root<TipoDocumento> root = queryDTO.from(TipoDocumento.class);

        queryDTO.select(cb.construct(TipoDocumentoDTO.class, select(root, 
                TipoDocumento_.id, TipoDocumento_.txTipoDocumento, TipoDocumento_.csAtivo)));

        final List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(cb.lower(root.get(TipoDocumento_.txTipoDocumento)), "%" + descricao.toLowerCase() + "%"));

        if (  situacao != null ) {
            switch (situacao) {
                case "T":
                    predicates.add(cb.isTrue(root.get(TipoDocumento_.csAtivo)));
                    break;
                case "F":
                    predicates.add(cb.isFalse(root.get(TipoDocumento_.csAtivo)));
                    break;
            }
        }

        queryDTO.where(super.addPredicate(predicates));

        queryDTO.orderBy(cb.asc(root.get(TipoDocumento_.txTipoDocumento)));

        Query q = entityManager.createQuery(queryDTO);

        int count = q.getResultList().size();
        super.setRange(q, range);

        return new GridResponse<>(q.getResultList(), count);
    }

    /**
     * Gets the TipoDocumento by descricao.
     *
     * @param descricao the descricao.
     * @return the TipoDocumento by descricao.
     */
    public TipoDocumentoDTO getTipoDocumentoByDescricao(String descricao) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TipoDocumentoDTO> queryDTO = cb.createQuery(TipoDocumentoDTO.class);

        Root<TipoDocumento> root = queryDTO.from(TipoDocumento.class);

        queryDTO.select(cb.construct(TipoDocumentoDTO.class, select(root, 
                TipoDocumento_.id, TipoDocumento_.txTipoDocumento, TipoDocumento_.csAtivo)))
                .where(cb.equal(cb.lower(root.get(TipoDocumento_.txTipoDocumento)), descricao.toLowerCase()));

        return super.getSingleResultOrNull(entityManager.createQuery(queryDTO));
    }

    /**
     * Gets the TipoDocumento by id.
     *
     * @param idTipoDocumento the id TipoDocumento
     * @return the TipoDocumento by id
     */
    public TipoDocumentoDTO getTipoDocumentoById(Long idTipoDocumento) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TipoDocumentoDTO> queryDTO = cb.createQuery(TipoDocumentoDTO.class);

        Root<TipoDocumento> root = queryDTO.from(TipoDocumento.class);

        queryDTO.select(cb.construct(TipoDocumentoDTO.class, select(root, 
                TipoDocumento_.id, TipoDocumento_.txTipoDocumento, TipoDocumento_.csAtivo)))
                .where(cb.equal(root.get(TipoDocumento_.id), idTipoDocumento));

        return entityManager.createQuery(queryDTO).getSingleResult();
    }

    public List<TipoDocumentoDTO> buscarTodos() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TipoDocumentoDTO> queryDTO = cb.createQuery(TipoDocumentoDTO.class);

        Root<TipoDocumento> root = queryDTO.from(TipoDocumento.class);

        queryDTO.select(cb.construct(TipoDocumentoDTO.class, select(root, 
                TipoDocumento_.id, TipoDocumento_.txTipoDocumento, TipoDocumento_.csAtivo)))
                .where(cb.isTrue(root.get(TipoDocumento_.csAtivo)));

        queryDTO.orderBy(cb.asc(root.get(TipoDocumento_.txTipoDocumento)));

        return entityManager.createQuery(queryDTO).getResultList();
    }
}
