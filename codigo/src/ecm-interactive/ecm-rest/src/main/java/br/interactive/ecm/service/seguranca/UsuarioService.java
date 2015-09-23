package br.interactive.ecm.service.seguranca;

import br.com.interactive.alfresco.servico.impl.AlfrescoServicoImpl;
import br.com.interactive.alfresco.model.Usuario;

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
import br.interactive.ecm.model.dto.seguranca.UsuarioDTO;
import br.interactive.ecm.model.dto.seguranca.UsuarioSimplesDTO;
import br.interactive.ecm.model.entity.Pessoa;
import br.interactive.ecm.model.entity.UserSession;
//import br.interactive.ecm.model.entity.Usuario;
import br.interactive.ecm.response.Response;
import br.interactive.ecm.util.DateUtil;
import br.interactive.ecm.util.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.DocumentException;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Service para operações relacionadas a entidade {@link Usuario}.
 */
@Stateless
public class UsuarioService {

    @Inject
    private AlfrescoServicoImpl alfrescoServico;
    
//    private Usuario usuario;
//    private Pessoa pessoa;
//    @Inject
//    private UsuarioDAO usuarioDAO;
//    @Inject
//    private PessoaDAO pessoaDAO;
//    @Inject
//    private UserSessionDAO userSessionDAO;

    @Inject
    private BusinessMessage businessMessage;
     

    private UsuarioDTO convertToUsuarioDTO(Usuario next) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.convertToUsuarioDTO(next);
        return usuarioDTO;
    }
    
    public List<UsuarioDTO> convertToUsuarioDTO(List<Usuario> lista) {
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        UsuarioDTO usuarioDTO;
        for (Usuario usu : lista) {
            usuarioDTO = convertToUsuarioDTO(usu);
            usuariosDTO.add(usuarioDTO);
        }
        return usuariosDTO;
    }


    public LoginDTO login(LoginDTO user, HttpServletRequest request) {
        
        try {
            
            Usuario usuario = alfrescoServico.autenticarUsuario("admin", "admin");
            UsuarioDTO usuarioDTO = convertToUsuarioDTO(usuario);
            
            
        } catch (HttpClientErrorException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

//        if (!StringUtil.notEmpty(user.getSenha())) {
//            throw new BusinessException(new ErrorMessage("seguranca.login.naoencontrado"));
//        }
//
//        Usuario usua = usuarioDAO.getUsuarioByLoginSenha(user.getLogin(), user.getSenha());
//
//        if (usua == null) {
//            throw new BusinessException(new ErrorMessage("seguranca.login.naoencontrado"));
//        }
////        this.validarUsuarioParaAutenticacao(usua);
//
//        UserSession userSession = userSessionDAO.getUserSessionLoginBrowserIp(usua.getTxLogin(),
//                request.getHeader("user-agent"), request.getRemoteAddr());
//
//        if (userSession != null) {
//            userSessionDAO.remove(userSession);
//        }
//
//        String token = UUID.randomUUID().toString();
//        UserSession session = new UserSession();
//        session.setTxLogin(usua.getTxLogin());
//        session.setTxToken(token);
//        session.setDtStartOrRefreshSession(new Date());
//        session.setDtExpiredSession(DateUtils.addMinutes(new Date(), 120));
//        session.setTxBrowser(request.getHeader("user-agent"));
//        session.setTxIpAdress(request.getRemoteAddr());
//        userSessionDAO.persist(session);
//        user.sethToken(token);
//
//        usua.setNbTentativas(Short.valueOf("0"));
//        usua.setDtDataAcesso(Calendar.getInstance());
//        usuarioDAO.merge(usua);
//
//        return user;


    public boolean logout(HttpServletRequest request) {
        
        // FIXME alterar

        String token = request.getHeader("Authorization");
        UserSession userSession = userSessionDAO.getUserSessionByToken(token);

        if (userSession != null) {
            userSessionDAO.remove(userSession);
            return true;
        }
        return false;
    }

     
}
