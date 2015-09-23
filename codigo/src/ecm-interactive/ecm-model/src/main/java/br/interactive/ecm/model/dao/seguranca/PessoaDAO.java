package br.interactive.ecm.model.dao.seguranca;

import br.interactive.ecm.model.dao.AbstractDAO;
import br.interactive.ecm.model.dto.seguranca.PessoaDTO;
import br.interactive.ecm.model.entity.Pessoa;
import br.interactive.ecm.model.entity.Pessoa_;
import static br.interactive.ecm.model.entity.Pessoa_.txCpf;
import br.interactive.ecm.response.GridResponse;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PessoaDAO extends AbstractDAO<Pessoa> {

    public GridResponse<Pessoa> getAll(int range[]) {
        return super.findAll(range);
    }

    /**
     * Consulta de pessoa por cpf.
     *
     * @param cpf
     * @return Pessoa
     */
    public Pessoa findPessoaByCpf(String cpf) {
        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> query = cb.createQuery(Pessoa.class);
        Root<Pessoa> root = query.from(Pessoa.class);
        query.select(root);
        query.where(cb.equal(cb.lower(root.get(txCpf)), cpf));
        return getSingleResultOrNull(super.entityManager.createQuery(query));
    }

    public PessoaDTO findPessoaById(Long id) {
        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();
        CriteriaQuery<PessoaDTO> query = cb.createQuery(PessoaDTO.class);
        Root<Pessoa> root = query.from(Pessoa.class);
        query.select(cb.construct(PessoaDTO.class, root.get(Pessoa_.id), root.get(Pessoa_.txNome)));
        query.where(cb.equal(root.get(Pessoa_.id), id));
        return getSingleResultOrNull(super.entityManager.createQuery(query));
    }
    
    public List<PessoaDTO> listarPessoas() {
        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();
        CriteriaQuery<PessoaDTO> query = cb.createQuery(PessoaDTO.class);
        Root<Pessoa> root = query.from(Pessoa.class);
        
        query.select(cb.construct(PessoaDTO.class, root.get(Pessoa_.id), root.get(Pessoa_.txNome)));
        query.orderBy(cb.asc(root.get(Pessoa_.txNome)));
        
        TypedQuery<PessoaDTO> q = super.entityManager.createQuery(query);
        return q.getResultList();
    }
}
