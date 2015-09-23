package br.interactive.ecm.model.dao.gerais;

import br.interactive.ecm.model.dao.AbstractDAO;
import br.interactive.ecm.model.dto.gerais.LoginDTO;
import br.interactive.ecm.model.entity.Pessoa_;
import static br.interactive.ecm.model.entity.UserSession_.txLogin;
import br.interactive.ecm.model.entity.Usuario;
import br.interactive.ecm.model.entity.Usuario_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsuarioDAO extends AbstractDAO<Usuario> {

    public LoginDTO getUsuarioByLoginDTO(String login) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoginDTO> queryDTO = cb.createQuery(LoginDTO.class);
        Root<Usuario> root = queryDTO.from(Usuario.class);

        queryDTO.select(
                cb.construct(
                        LoginDTO.class, root.get(Usuario_.id), root.get(Usuario_.txLogin),
                        root.get(Usuario_.txSenha), root.get(Usuario_.pessoa).get(Pessoa_.txNome)
//                        ,root.get(Usuario_.pessoa).get(Pessoa_.csPapel)
                )
        )
//                .where(cb.equal(cb.lower(root.get(txLogin)), login.toLowerCase()))        // FIXME continuar
                ;

        return super.getSingleResultOrNull(entityManager.createQuery(queryDTO));
    }
    
//    /**
//     * Get all usuarios
//     *
//     * @param range
//     * @return
//     */
//    public GridResponse<Usuario> getAll(int[] range) {
//        return super.findAll(range);
//    }
//
//    /**
//     * Retorna um usuario por login e senha
//     *
//     * @param login, senha
//     * @param senha
//     * @return Usuario
//     */
//    public Usuario getUsuarioByLoginSenha(String login, String senha) {
//        if (login == null || senha == null) {
//            return null;
//        }
//
//        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();
//        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
//        Root<Usuario> root = query.from(Usuario.class);
//        query.select(root);
//        query.where(cb.and(
//                cb.equal(cb.lower(root.get(txLogin)), login.toLowerCase()),
//                cb.equal(cb.lower(root.get(txSenha)), senha.toLowerCase())
//        )
//        );
//
//        Query q = entityManager.createQuery(query);
//
//        return super.getSingleResultOrNull(q);
//    }
//
//    /**
//     * Retorna uma lista paginada de usuarios por login
//     *
//     * @param login
//     * @param range
//     * @return PaginatedQuery<>
//     */
//    public GridResponse<Usuario> getUsuariosByLogin(String login, int[] range) {
//        if (login == null) {
//            return null;
//        }
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
//
//        Root<Usuario> root = query.from(Usuario.class);
//
//        query.select(query.from(Usuario.class))
//                .where(cb.equal(cb.lower(root.get(txLogin)), login.toLowerCase()));
//
//        Query q = entityManager.createQuery(query);
//
//        int count = q.getResultList().size();
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//
//        return new GridResponse<>(q.getResultList(), count);
//    }
//
//    /**
//     * Gets the usuario by login.
//     *
//     * @param login the login
//     * @return the usuario by login
//     */
//    public Usuario getUsuarioByLogin(String login) {
//        if (login == null) {
//            return null;
//        }
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
//        Root<Usuario> root = query.from(Usuario.class);
//
//        query.select(root)
//                .where(cb.equal(cb.lower(root.get(txLogin)), login.toLowerCase()));
//
//        Query q = entityManager.createQuery(query);
//
//        return super.getSingleResultOrNull(q);
//    }

//
//    /**
//     * Obter perfil acesso
//     *
//     * @param idUsuario
//     * @return
//     */
//    public Usuario obterPerfisAcesso(Long idUsuario) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
//
//        Root<Usuario> root = query.from(Usuario.class);
//
//        query.select(query.from(Usuario.class))
//                .where(cb.equal(root.get(id), idUsuario));
//
//        Query q = entityManager.createQuery(query);
//
//        return super.getSingleResultOrNull(q);
//    }
//
//    /**
//     * Consulta os usuarios ativos pela descricao para o componente
//     * autocomplete.
//     *
//     * @param descricao
//     * @return List<AutocompleteModel>
//     */
//    public List<AutocompleteDTO> getUsuarioAssistSocial(String descricao) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<AutocompleteDTO> queryDTO = cb.createQuery(AutocompleteDTO.class);
//
//        Root<Usuario> root = queryDTO.from(Usuario.class);
//
//        queryDTO.select(cb.construct(AutocompleteDTO.class, root.get(id),
//                root.get(pessoa).get(Pessoa_.txNome)))
//                .where(cb.and(
//                                cb.like(cb.lower(root.get(pessoa).get(Pessoa_.txNome)), "%" + descricao.toLowerCase() + "%"),
//                                cb.equal(root.get(pessoa).get(Pessoa_.csPapel), PapelPessoaConstant.ASSISTENTE_SOCIAL.getId())
//                        ))
//                .orderBy(cb.asc(root.get(pessoa).get(Pessoa_.txNome)));
//
//        return entityManager.createQuery(queryDTO).getResultList();
//    }
//
//    /**
//     * Obtem a lista de todos os usuÃ¡rio
//     *
//     * @return List<Usuario>
//     */
//    public List<UserForKeycloakDTO> getAllUser() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<UserForKeycloakDTO> queryDTO = cb.createQuery(UserForKeycloakDTO.class);
//
//        Root<Usuario> root = queryDTO.from(Usuario.class);
//
//        queryDTO.select(cb.construct(UserForKeycloakDTO.class, select(root, txLogin, txSenha, csStatus)))
//                .orderBy(cb.asc(root.get(txLogin)));
//
//        return entityManager.createQuery(queryDTO).getResultList();
//    }
//
//    /**
//     * Get aprovar usuario by identifier.
//     *
//     * @param idUsuario
//     * @return
//     */
//    public AprovarUsuarioDTO getAprovarUsuarioById(Long idUsuario) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<AprovarUsuarioDTO> queryDTO = cb.createQuery(AprovarUsuarioDTO.class);
//
//        Root<Usuario> root = queryDTO.from(Usuario.class);
//
//        queryDTO.select(cb.construct(AprovarUsuarioDTO.class, root.get(id),
//                root.get(pessoa).get(Pessoa_.txNome),
//                root.get(txLogin),
//                root.get(pessoa).get(Pessoa_.txEmail),
//                root.get(pessoa).get(Pessoa_.csPapel),
//                root.get(txMensagem),
//                root.get(txSenha),
//                root.get(csStatus)))
//                .where(cb.equal(root.get(id), idUsuario));
//
//        Query q = entityManager.createQuery(queryDTO);
//
//        return super.getSingleResultOrNull(q);
//    }
//
//    /**
//     * Obter usuarios para manutenção.
//     *
//     * @param busca
//     * @param papel
//     * @param situacao
//     * @param range
//     * @return
//     */
//    public GridResponse<AprovarUsuarioDTO> obterUsuariosParaManutencao(String busca, Integer papel, Integer situacao, int[] range) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<AprovarUsuarioDTO> query = cb.createQuery(AprovarUsuarioDTO.class);
//        Root<Usuario> root = query.from(Usuario.class);
//
//        query.select(cb.construct(AprovarUsuarioDTO.class, root.get(id),
//                root.get(pessoa).get(Pessoa_.txNome),
//                root.get(txLogin),
//                root.get(pessoa).get(Pessoa_.csPapel),
//                root.get(pessoa).get(Pessoa_.txRegistroProfissional),
//                root.get(txMensagem),
//                root.get(csStatus)));
//
//        List<Predicate> list = new ArrayList<>();
//
//        if (busca != null && !busca.isEmpty()) {
//            busca = busca.toLowerCase();
//            list.add(cb.or(
//                    cb.like(cb.lower(root.get(txLogin)), "%" + busca + "%"),
//                    cb.like(cb.lower(root.get(pessoa).get(Pessoa_.txRegistroProfissional)), "%" + busca + "%"),
//                    cb.like(cb.lower(root.get(pessoa).get(Pessoa_.txNome)), "%" + busca + "%")));
//        }
//
//        if (papel != null) {
//            list.add(cb.or(cb.equal(root.get(pessoa).get(Pessoa_.csPapel), papel)));
//        }
//
//        if (situacao != null) {
//            list.add(cb.or(cb.equal(root.get(csStatus), situacao)));
//        } else {
//            // Filtro para as situações: 1 = Ativo, 2 = Inativo, 3 = Bloqueado, 4 = Senha regerada
//            list.add(cb.or(
//                    cb.equal(root.get(csStatus), 1),
//                    cb.equal(root.get(csStatus), 2),
//                    cb.equal(root.get(csStatus), 3),
//                    cb.equal(root.get(csStatus), 4)));
//        }
//
//        query.where(super.addPredicate(list));
//        query.orderBy(cb.asc(root.get(pessoa).get(Pessoa_.txNome)));
//
//        Query q = this.entityManager.createQuery(query);
//        int count = q.getResultList().size();
//        super.setRange(q, range);
//        return new GridResponse<>(q.getResultList(), count);
//
//    }
//
//    public UsuarioManutencaoDTO obterUsuarioManutencao(Long idUsuario) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<UsuarioManutencaoDTO> query = cb.createQuery(UsuarioManutencaoDTO.class);
//        Root<Usuario> root = query.from(Usuario.class);
//
//        query.select(cb.construct(UsuarioManutencaoDTO.class, root.get(id),
//                root.get(csStatus),
//                root.get(txLogin),
//                root.get(txSenha),
//                root.get(txMensagem),
//                root.get(dtDataAcesso),
//                root.get(txLocale),
//                root.get(pessoa).get(Pessoa_.id),
//                root.get(pessoa).get(Pessoa_.csPapel),
//                root.get(pessoa).get(Pessoa_.nbEstadoEmissor),
//                root.get(pessoa).get(Pessoa_.txEmail),
//                root.get(pessoa).get(Pessoa_.txNome),
//                root.get(pessoa).get(Pessoa_.txRegistroProfissional)));
//
//        query.where(cb.equal(root.get(id), idUsuario));
//
//        return super.getSingleResultOrNull(entityManager.createQuery(query));
//
//    }
//
//    public List<AutocompleteDTO> buscarUsuarios() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<AutocompleteDTO> queryDTO = cb.createQuery(AutocompleteDTO.class);
//
//        Root<Usuario> root = queryDTO.from(Usuario.class);
//
//        queryDTO.select(cb.construct(AutocompleteDTO.class, root.get(id),
//                root.get(pessoa).get(Pessoa_.txNome)))
//                .orderBy(cb.asc(root.get(pessoa).get(Pessoa_.txNome)));
//
//        return entityManager.createQuery(queryDTO).getResultList();
//    }
}
