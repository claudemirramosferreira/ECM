package br.interactive.ecm.model.filtro;

import br.interactive.ecm.util.StringUtil;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 */
@Stateless
public class SessaoService {

//    @Inject
//    private UserSessionDAO sessionDAO;

    public boolean isConnected(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (!StringUtil.notEmpty(token)) {
            return false;
        }

//        UserSession userSession = sessionDAO.getUserSessionByToken(token);

//        if (userSession != null) {
//            if (DateUtil.isDateGreaterCurrentDate(userSession.getDtExpiredSession())) {
//                userSession.setDtStartOrRefreshSession(new Date());
//                userSession.setDtExpiredSession(DateUtils.addMinutes(new Date(), 120));
//                sessionDAO.merge(userSession);
//                return true;
//            } else {
//                sessionDAO.remove(userSession);
//            }
//        }

        return false;
    }
}
