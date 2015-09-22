package br.interactive.ecm.model.dao.seguranca;

import br.interactive.ecm.model.dao.AbstractDAO;
import br.interactive.ecm.model.dto.seguranca.LoginDTO;
import br.interactive.ecm.model.dto.seguranca.UsuarioSimplesDTO;
import br.interactive.ecm.model.entity.Pessoa_;
import br.interactive.ecm.model.entity.Usuario;
import static br.interactive.ecm.model.entity.Usuario_.*;
//import br.interactive.ecm.model.entity.Usuario_;
import br.interactive.ecm.response.GridResponse;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Class for UsuarioDAO
 *
 * @author anderson.monteiro
 */
public class UsuarioDAO extends AbstractDAO<Usuario> {

    /**
     * Get all usuarios
     *
     * @param range
     * @return
     */
    public GridResponse<Usuario> getAll(int[] range) {
        return super.findAll(range);
    }

    /**
     * Retorna um usuario por login e senha
     *
     * @param login, senha
     * @param senha
     * @return Usuario
     */
    public Usuario getUsuarioByLoginSenha(String login, String senha) {
        if (login == null || senha == null) {
            return null;
        }

        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);
        query.select(root);
        query.where(cb.and(
                cb.equal(cb.lower(root.get(txLogin)), login.toLowerCase()),
                cb.equal(cb.lower(root.get(txSenha)), senha.toLowerCase())
        )
        );

        Query q = entityManager.createQuery(query);

        return super.getSingleResultOrNull(q);
    }

    /**
     * Retorna uma lista paginada de usuarios por login
     *
     * @param login
     * @param range
     * @return PaginatedQuery<>
     */
    public GridResponse<Usuario> getUsuariosByLogin(String login, int[] range) {
        if (login == null) {
            return null;
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);

        Root<Usuario> root = query.from(Usuario.class);

        query.select(query.from(Usuario.class))
                .where(cb.equal(cb.lower(root.get(txLogin)), login.toLowerCase()));

        Query q = entityManager.createQuery(query);

        int count = q.getResultList().size();
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);

        return new GridResponse<>(q.getResultList(), count);
    }

    /**
     * Gets the usuario by login.
     *
     * @param login the login
     * @return the usuario by login
     */
    public Usuario getUsuarioByLogin(String login) {
        if (login == null) {
            return null;
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);

        query.select(root)
                .where(cb.equal(cb.lower(root.get(txLogin)), login.toLowerCase()));

        Query q = entityManager.createQuery(query);

        return super.getSingleResultOrNull(q);
    }

    public LoginDTO getUsuarioByLoginDTO(String login) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoginDTO> queryDTO = cb.createQuery(LoginDTO.class);
        Root<Usuario> root = queryDTO.from(Usuario.class);

        queryDTO.select(cb.construct(LoginDTO.class, root.get(id), root.get(txLogin),
                root.get(txSenha), root.get(pessoa).get(Pessoa_.txNome)))
                .where(cb.equal(cb.lower(root.get(txLogin)), login.toLowerCase()));

        return super.getSingleResultOrNull(entityManager.createQuery(queryDTO));
    }

    /**
     * Obter perfil acesso
     *
     * @param idUsuario
     * @return
     */
    public Usuario obterPerfisAcesso(Long idUsuario) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);

        Root<Usuario> root = query.from(Usuario.class);

        query.select(query.from(Usuario.class))
                .where(cb.equal(root.get(id), idUsuario));

        Query q = entityManager.createQuery(query);

        return super.getSingleResultOrNull(q);
    }

    
    public List<UsuarioSimplesDTO> getUsuarioByNome(String nome) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsuarioSimplesDTO> queryDTO = cb.createQuery(UsuarioSimplesDTO.class);

        Root<Usuario> root = queryDTO.from(Usuario.class);

        queryDTO.select(cb.construct(UsuarioSimplesDTO.class, 
                                root.get(id),
                                root.get(pessoa).get(Pessoa_.txNome)))
                .where(cb.and(
                                cb.like(cb.lower(root.get(pessoa).get(Pessoa_.txNome)), "%" + nome.toLowerCase() + "%")
                        )
                )
                .orderBy(cb.asc(root.get(pessoa).get(Pessoa_.txNome)));

        return entityManager.createQuery(queryDTO).getResultList();
    }
    
    
}
