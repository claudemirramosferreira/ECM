/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.model.dao;

import br.interactive.ecm.response.GridResponse;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;

/**
 * @author Osenias
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> persistentClass;

    public AbstractDAO() {
        initAnnotedClass();
    }

    private void initAnnotedClass() {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
            persistentClass = (Class<T>) paramType.getActualTypeArguments()[0];
        } else if (getClass().getSuperclass().getGenericSuperclass() instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();
            persistentClass = (Class<T>) paramType.getActualTypeArguments()[0];
        }
    }

    public void persist(T entity) {
        this.entityManager.persist(entity);
    }

    public void merge(T entity) {
        this.entityManager.merge(entity);
    }

    public void remove(T entity) {
        this.entityManager.remove(entity);
    }

    public void remove(Long id) {
        this.entityManager.remove(this.entityManager.find(persistentClass, id));
    }

    public T getByPrimaryKey(Long id) {
        return (T) this.entityManager.find(persistentClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = (CriteriaQuery<T>) this.entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(persistentClass));
        Query q = this.entityManager.createQuery(cq);
        return q.getResultList();
    }

    public GridResponse<T> findAll(int[] range) {
        CriteriaQuery<T> cq = (CriteriaQuery<T>) this.entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(persistentClass));
        Query q = this.entityManager.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return new GridResponse<T>(q.getResultList(), count());
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(persistentClass);
        cq.select(entityManager.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = entityManager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public GridResponse<?> findAll(int[] range, Class DTO, SingularAttribute<?, ?>... selectedColumns) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery queryDTO = cb.createQuery(DTO);
        Root<T> queryEntity = queryDTO.from(persistentClass);
        queryDTO.select(cb.construct(DTO, select(queryEntity, selectedColumns)));
        Query q = this.entityManager.createQuery(queryDTO);
        int count = q.getResultList().size();
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return new GridResponse<T>(q.getResultList(), count);
    }

    protected Selection<?>[] select(Root<T> queryEntity, SingularAttribute<?, ?>... selectedColumns) {
        int i = 0;
        Selection<?> selection[] = new Selection<?>[selectedColumns.length];
        for (SingularAttribute c : selectedColumns) {
            Expression<?> e = queryEntity.get(c);
            selection[i] = e;
            i++;
        }
        return selection;
    }

    protected void setRange(Query q, int range[]) {
        q.setMaxResults(range[1]);
        q.setFirstResult((range[0] - 1) * range[1]);
    }

    protected <E> E getSingleResultOrNull(Query q) {
        try {
            return (E) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void flush() {
        this.entityManager.flush();
    }

    protected Predicate[] addPredicate(List<Predicate> predicates) {
        return predicates.toArray(new Predicate[]{});
    }
    
    
    protected boolean notEmpty(String str) {
        return null != str && str.length() > 0;
    }
    
    protected boolean notEmpty(Object obj) {
        return null != obj;
    }
    
}
