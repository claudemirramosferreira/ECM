package br.interactive.ecm.model.dao.gerais;

import br.interactive.ecm.model.dto.gerais.SysVersaoDTO;
import br.interactive.ecm.model.entity.SysVersao;
import br.interactive.ecm.model.entity.SysVersao_;
import br.interactive.ecm.model.dao.AbstractDAO;
import br.interactive.ecm.response.GridResponse;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * DAO para a entidade SysVersao.
 */
public class SysVersaoDAO extends AbstractDAO<SysVersao> {

    /**
     * Retorna a ultima versao do sistema.
     *
     * @return SysVersaoDTO
     */
    public SysVersaoDTO getCurrentVersion() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SysVersaoDTO> queryDTO = cb.createQuery(SysVersaoDTO.class);

        Root<SysVersao> root = queryDTO.from(SysVersao.class);

        queryDTO.select(cb.construct(SysVersaoDTO.class, select(root, SysVersao_.txVersao)))
                .orderBy(cb.desc(root.get( SysVersao_.idSysVersao)));

        Query q = entityManager.createQuery(queryDTO);
        q.setMaxResults(1);

        return (SysVersaoDTO) q.getSingleResult();
    }

    /**
     * Retorna todas as versoes do sistema.
     *
     * @return SysVersaoDTO
     */
    public GridResponse<SysVersaoDTO> getAllVersion() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SysVersaoDTO> queryDTO = cb.createQuery(SysVersaoDTO.class);

        Root<SysVersao> root = queryDTO.from(SysVersao.class);

        queryDTO.select(cb.construct(SysVersaoDTO.class, select(root, SysVersao_.txVersao)))
                .orderBy(cb.asc(root.get(SysVersao_.txVersao)));

        Query query = entityManager.createQuery(queryDTO);

        int[] range = {1, 5};   // FIXME : exemplo de paginação
        super.setRange(query, range);
        int count = query.getResultList().size();
        return new GridResponse<>(query.getResultList(), count);
    }
    
}
