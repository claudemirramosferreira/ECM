package br.interactive.ecm.service.seguranca;

import br.interactive.ecm.exception.BusinessException;
import br.interactive.ecm.internationalization.MessageResolver;
import br.interactive.ecm.message.BusinessMessage;
import br.interactive.ecm.message.EmptyMessage;
import br.interactive.ecm.message.ErrorMessage;
import br.interactive.ecm.message.InfoMessage;
import br.interactive.ecm.model.dao.seguranca.PessoaDAO;
import br.interactive.ecm.model.dao.seguranca.UserSessionDAO;
import br.interactive.ecm.model.dao.seguranca.UsuarioDAO;
import br.interactive.ecm.model.dto.seguranca.LoginDTO;
import br.interactive.ecm.model.dto.seguranca.UsuarioSimplesDTO;
import br.interactive.ecm.model.entity.Pessoa;
import br.interactive.ecm.model.entity.UserSession;
import br.interactive.ecm.model.entity.Usuario;
import br.interactive.ecm.response.Response;
import br.interactive.ecm.util.DateUtil;
import br.interactive.ecm.util.StringUtil;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.time.DateUtils;

/**
 * Service para operações relacionadas a entidade {@link Usuario}.
 */
@Stateless
public class UsuarioService {

    /**
     * Entidade de {@link Usuario}
     */
    private Usuario usuario;

    /**
     * Entidade de {@link Pessoa}
     */
    private Pessoa pessoa;

    /**
     * DAO de {@link Usuario}
     */
    @Inject
    private UsuarioDAO usuarioDAO;

    /**
     * Componente de mensagem.
     */
    @Inject
    private BusinessMessage businessMessage;

    /**
     * DAO de {@link Pessoa}
     */
    @Inject
    private PessoaDAO pessoaDAO;

    @Inject
    private UserSessionDAO userSessionDAO;
     

    /**
     * Faz a busca da pessoa informada pelo CPF.
     *
     * @param cpf
     * @return solicitaCadastro
     */
    public Response<String> getStatusCfp(String cpf) {
        Response<String> response = null;
        Pessoa pes = pessoaDAO.findPessoaByCpf(cpf);

        if (pes != null) {
            Usuario usuario = this.usuarioDAO.getByPrimaryKey(pes.getId());
            if (usuario != null) {
                response = new Response<>(String.valueOf(usuario.getCsStatus()));
            }
        }

        if (response == null) {
            throw new BusinessException(new EmptyMessage("nenhum.registro.encontrado"));
        }

        return response;

    }

    /**
     * Faz a validacao de login ja existente
     *
     * @param cpf
     * @param login
     */
    public void validarLogin(String cpf, String login) {
        this.validarLoginUsuario(cpf, login);
    }

    public void validarLoginUsuario(String cpf, String login) {
        String msg = "";

        Pessoa pes = pessoaDAO.findPessoaByCpf(cpf.replace(".", "").replace("-", ""));

        Usuario user = usuarioDAO.getUsuarioByLogin(login);

        if (user != null) {
            if (pes == null) {
                msg = MessageResolver.interpolate("msg_solicitar_cadastro.msg_erro_login_already_exists");
                throw new BusinessException(new ErrorMessage(msg));
            } else {
                if (!user.getPessoa().getId().equals(pes.getId())) {
                    msg = MessageResolver.interpolate("msg_solicitar_cadastro.msg_erro_login_already_exists");
                    throw new BusinessException(new ErrorMessage(msg));
                }
            }
        }
        throw new BusinessException(new InfoMessage("true"));
    }


    /**
     * Faz a validação do E-mail
     *
     * @param email
     * @return booleano.
     */
    public boolean validaEmail(String email) {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        return m.find();
    }

    
    public String obterHost(HttpServletRequest httpServletRequest) {
        final String url = httpServletRequest.getRequestURL().toString();

        return url.substring(0, url.length() - httpServletRequest.getRequestURI().length()) + "";
    }

    /**
     * Verificar se existe conexao com a internet
     *
     * @param
     * @return
     */
    private void verificarConexaoInternet() {
        try {
            java.net.URL mandarMail = new java.net.URL("https://www.google.com.br/");
            java.net.URLConnection conn = mandarMail.openConnection();

            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) conn;
            httpConn.connect();
            int x = httpConn.getResponseCode();
            if (x != 200) {
                throw new BusinessException(new ErrorMessage("Sem conexao com a internet."));
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Gerar senha.
     *
     * @return
     */
    private String gerarSenha() {
        final int maxPosicao = 256;
        final int minIndex = 48;
        final int maxIndex = 57;
        Random random = new Random();
        String novaSenha = "";
        while (novaSenha.length() != 6) {
            int indice = random.nextInt(maxPosicao);
            if ((indice > minIndex) && (indice <= maxIndex)) {
                novaSenha += (char) indice;
            }
        }

        return novaSenha;
    }


    public LoginDTO login(LoginDTO user, HttpServletRequest request) {

        if (!StringUtil.notEmpty(user.getSenha())) {
            throw new BusinessException(new ErrorMessage("seguranca.login.naoencontrado"));
        }

        Usuario usua = usuarioDAO.getUsuarioByLoginSenha(user.getLogin(), user.getSenha());

        if (usua == null) {
            throw new BusinessException(new ErrorMessage("seguranca.login.naoencontrado"));
        }
//        this.validarUsuarioParaAutenticacao(usua);

        UserSession userSession = userSessionDAO.getUserSessionLoginBrowserIp(usua.getTxLogin(),
                request.getHeader("user-agent"), request.getRemoteAddr());

        if (userSession != null) {
            userSessionDAO.remove(userSession);
        }

        String token = UUID.randomUUID().toString();
        UserSession session = new UserSession();
        session.setTxLogin(usua.getTxLogin());
        session.setTxToken(token);
        session.setDtStartOrRefreshSession(new Date());
        session.setDtExpiredSession(DateUtils.addMinutes(new Date(), 120));
        session.setTxBrowser(request.getHeader("user-agent"));
        session.setTxIpAdress(request.getRemoteAddr());
        userSessionDAO.persist(session);
        user.sethToken(token);

        usua.setNbTentativas(Short.valueOf("0"));
        usua.setDtDataAcesso(Calendar.getInstance());
        usuarioDAO.merge(usua);

        return user;
    }

//    private void validarUsuarioParaAutenticacao(Usuario usuario) {
//
//        if (usuario == null) {
//            throw new BusinessException(new ErrorMessage("login.naoencontrado"));
//        }
//
//        if (usuario.getCsStatus().equals(StatusUsuarioConstant.CADASTRO_SOLICITADO.getId())) {
//            throw new BusinessException(new ErrorMessage("login.aguardaaprovacao"));
//        }
//
//        if (usuario.getCsStatus().equals(StatusUsuarioConstant.BLOQUEADO.getId())) {
//            throw new BusinessException(new ErrorMessage("login.bloqueado"));
//        }
//
//        if (usuario.getCsStatus().equals(StatusUsuarioConstant.CADASTRO_REJEITADO.getId())) {
//            List<String> mensagens = new ArrayList<>();
//            mensagens.add("login.rejeitado");
//            mensagens.add(usuario.getUsuarioStatus().get(usuario.getUsuarioStatus().size() - 1).getTxJustificativa());
//            mensagens.add(usuario.getPessoa().getTxCpf());
//            throw new BusinessException(new ErrorMessage(mensagens));
//        }
//
//        if (usuario.getCsStatus().equals(StatusUsuarioConstant.INATIVO.getId())) {
//            throw new BusinessException(new ErrorMessage("login.inativo"));
//        }
//
//        if (usuario.getCsStatus().equals(StatusUsuarioConstant.SENHA_REGERADA_PELO_ADMNISTRADOR.getId())) {
//            throw new BusinessException(new ErrorMessage("login.senharegerada"));
//        }
//
//        if (usuario.getCsStatus().equals(StatusUsuarioConstant.CADASTRO_INCORRETO.getId())) {
//            List<String> mensagens = new ArrayList<>();
//            mensagens.add("login.incorreto");
//            mensagens.add(usuario.getUsuarioStatus().get(usuario.getUsuarioStatus().size() - 1).getTxJustificativa());
//            mensagens.add(usuario.getPessoa().getTxCpf());
//            throw new BusinessException(new ErrorMessage(mensagens));
//        }
//    }

    public boolean isConnected(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (!StringUtil.notEmpty(token)) {
            return false;
        }

        UserSession userSession = userSessionDAO.getUserSessionByToken(token);

        if (userSession != null) {
            if (DateUtil.isDateGreaterCurrentDate(userSession.getDtExpiredSession())) {
                userSession.setDtStartOrRefreshSession(new Date());
                userSession.setDtExpiredSession(DateUtils.addMinutes(new Date(), 120));
                userSessionDAO.merge(userSession);
                return true;
            } else {
                userSessionDAO.remove(userSession);
            }
        }

        return false;
    }

    public boolean logout(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        UserSession userSession = userSessionDAO.getUserSessionByToken(token);

        if (userSession != null) {
            userSessionDAO.remove(userSession);
            return true;
        }
        return false;
    }

    public LoginDTO isGetUser(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        UserSession userSession = userSessionDAO.getUserSessionByToken(token);

        if (userSession != null) {
            LoginDTO loginDTO = usuarioDAO.getUsuarioByLoginDTO(userSession.getTxLogin());
            loginDTO.setToken(userSession.getTxToken());
            return loginDTO;
        }

        return null;
    }


    public List<UsuarioSimplesDTO> getUsuarioByNome(String txNome) {
        return usuarioDAO.getUsuarioByNome(txNome);
    }
     
}
