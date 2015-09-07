/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.gerais.service;

import br.interactive.ecm.exception.BusinessException;
import br.interactive.ecm.message.ErrorMessage;
import br.interactive.ecm.model.dao.gerais.UserSessionDAO;
import br.interactive.ecm.model.dao.gerais.UsuarioDAO;
import br.interactive.ecm.model.dto.gerais.LoginDTO;
import br.interactive.ecm.model.entity.Pessoa;
import br.interactive.ecm.model.entity.UserSession;
import br.interactive.ecm.model.entity.Usuario;
import br.interactive.ecm.util.StringUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author robson.ramos
 */
@Stateless
public class UsuarioService {
    
    @Inject
    private UserSessionDAO userSessionDAO;
    
    @Inject
    private UsuarioDAO usuarioDAO;
    
    
    public LoginDTO login(LoginDTO user, HttpServletRequest request) {

        if (!StringUtil.notEmpty(user.getSenha())) {
            throw new BusinessException(new ErrorMessage("seguranca.login.naoencontrado"));
        }

//        Usuario usua = usuarioDAO.getUsuarioByLoginSenha(user.getLogin(), user.getSenha());
        // FIXME Obter usuario
        Usuario usua = new Usuario();
        Pessoa p = new Pessoa();
        p.setTxNome("Nome do Usuario");
        p.setUsuario(usua);
        usua.setPessoa(p);

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
    
}