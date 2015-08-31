/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.model.dao.gerais;

import br.interactive.ecm.model.dao.AbstractDAO;
import br.interactive.ecm.model.entity.UserSession;
import br.interactive.ecm.model.entity.UserSession_;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserSessionDAO extends AbstractDAO<UserSession> {

    public UserSession getUserSessionByToken(String token) {
        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserSession> query = cb.createQuery(UserSession.class);
        Root<UserSession> root = query.from(UserSession.class);
        query.select(root);
        query.where(cb.equal(cb.lower(root.get(UserSession_.txToken)), token.toLowerCase()));

        return getSingleResultOrNull(super.entityManager.createQuery(query));
    }

    public UserSession getUserSessionLoginBrowserIp(String login, String browser, String ip) {
        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserSession> query = cb.createQuery(UserSession.class);
        Root<UserSession> root = query.from(UserSession.class);
        query.select(root);

        query.where(cb.equal(cb.lower(root.get(UserSession_.txLogin)), login.toLowerCase()),
                cb.equal(cb.lower(root.get(UserSession_.txBrowser)), browser.toLowerCase()),
                cb.equal(cb.lower(root.get(UserSession_.txIpAdress)), ip.toLowerCase())
        );
        TypedQuery<UserSession> q = super.entityManager.createQuery(query);
        q.setMaxResults(1);
        return getSingleResultOrNull(q);
    }
}
