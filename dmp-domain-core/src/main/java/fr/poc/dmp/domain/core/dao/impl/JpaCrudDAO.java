/**
 * 
 */
package fr.poc.dmp.domain.core.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.poc.dmp.domain.core.dao.CrudDAO;

/**
 * @author jmaupoux
 * @param <T>, type entity
 * @param <PK>, primarykey, the primary key
 */
@Repository("crudDAO")
public class JpaCrudDAO implements CrudDAO
{

    @PersistenceContext
    protected EntityManager entityManager;

    public <T> T create(T t)
    {
        this.entityManager.persist(t);
        return t;
    }

    public <T> T update(T type)
    {
        entityManager.merge(type);
        return type;
    }

    public <T, PK extends Serializable> T find(Class<T> type, PK id)
    {
        return (T) entityManager.find(type, id);
    }

    public <T, PK extends Serializable> void delete(Class<T> type, PK id)
    {
        T ref = (T) entityManager.find(type, id);
        entityManager.remove(ref);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findWithNamedQuery(String queryName)
    {
        return entityManager.createNamedQuery(queryName).getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findWithNamedQuery(String queryName, Map<String, Object> params)
    {
        Set<Entry<String, Object>> rawParameters = params.entrySet();
        Query query = entityManager.createNamedQuery(queryName);

        for (Entry<String, Object> entry : rawParameters)
        {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> T findMaxResultsWithNamedQuery(String queryName, int range)
    {
        Query query = entityManager.createNamedQuery(queryName);
        query.setMaxResults(range);
        return (T) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> T findUniqueWithNamedQuery(String queryName)
    {
        return (T) entityManager.createNamedQuery(queryName).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public <T> T findUniqueWithNamedQuery(String queryName, Map<String, Object> params)
    {
        Set<Entry<String, Object>> rawParameters = params.entrySet();
        Query query = entityManager.createNamedQuery(queryName);

        for (Entry<String, Object> entry : rawParameters)
        {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return (T) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findMaxResultsWithNamedQuery(String queryName, Map<String, Object> params, int range)
    {
        Query query = entityManager.createNamedQuery(queryName);
        Set<Entry<String, Object>> rawParameters = params.entrySet();

        for (Entry<String, Object> entry : rawParameters)
        {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        query.setMaxResults(range);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findRangeResultsWithNamedQuery(String queryName, int startIndex, int endIndex, Map<String, Object> params)
    {
        Query query = entityManager.createNamedQuery(queryName);
        if (params != null)
        {
            Set<Entry<String, Object>> rawParameters = params.entrySet();

            for (Entry<String, Object> entry : rawParameters)
            {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        query.setFirstResult(startIndex);
        query.setMaxResults(1 + endIndex - startIndex);
        return query.getResultList();
    }

    @Override
    public <T> long count(T type)
    {
        Query query = this.entityManager.createQuery("SELECT count(*) FROM " + type);
        Long count = (Long) query.getSingleResult();
        return count;
    }

    @Override
    public <T> long count(String queryName, Map<String, Object> params)
    {
        return (Long) this.findUniqueWithNamedQuery(queryName, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> execute(String query)
    {
        Query q = this.entityManager.createQuery(query);
        return q.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> executeCache(String query)
    {
        Query q = this.entityManager.createQuery(query);
        q.setHint("org.hibernate.cacheable", true);
        return q.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> execute(String query, Map<String, Object> params)
    {
        Query q = this.entityManager.createQuery(query);

        if (params != null)
        {
            Set<Entry<String, Object>> rawParameters = params.entrySet();

            for (Entry<String, Object> entry : rawParameters)
            {
                q.setParameter(entry.getKey(), entry.getValue());
            }
        }

        return q.getResultList();
    }
}
