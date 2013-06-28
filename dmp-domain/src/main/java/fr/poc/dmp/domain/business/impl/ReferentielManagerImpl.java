package fr.poc.dmp.domain.business.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import fr.poc.dmp.domain.business.ReferentielManager;
import fr.poc.dmp.domain.core.business.impl.DefaultManagerImpl;
import fr.poc.dmp.domain.core.dao.CrudDAO;
import fr.poc.dmp.domain.core.dao.QueryParameters;
import fr.poc.dmp.domain.model.referentiel.AbstractReferentielModel;

@Service("referentielManager")
public class ReferentielManagerImpl extends DefaultManagerImpl<Object, Serializable> implements ReferentielManager
{

    @Autowired
    private CrudDAO crudDAO;

    public <T extends AbstractReferentielModel, PK extends Serializable> void delete(Class<T> c, PK id)
    {
        Assert.notNull(id, "L'identifiant de l'objet ne peut etre null");
        crudDAO.delete(c, id);
    }

    @Override
    public void delete(Serializable id)
    {
        throw new UnsupportedOperationException("not supported for referential");
    }

    @Transactional(readOnly = true)
    public <T extends AbstractReferentielModel, PK extends Serializable> T find(Class<T> c, PK id)
    {
        Assert.notNull(id, "L'identifiant de l'objet ne peut etre null");
        return crudDAO.find(c, id);
    }

    @Override
    public Object find(Serializable id)
    {
        throw new UnsupportedOperationException("not supported for referential");
    }

    public long count(Class<? extends AbstractReferentielModel> c)
    {
        return crudDAO.count(c.getName());
    }

    @Override
    public long count()
    {
        throw new UnsupportedOperationException("not supported for referential");
    }

    @Override
    public <T extends AbstractReferentielModel> List<T> list(Class<T> c)
    {
        return crudDAO.executeCache("FROM " + c.getName() + " ORDER BY libelle");
    }

    @Override
    public <T extends AbstractReferentielModel> T findByCode(Class<T> c, String code)
    {
        List<T> list = crudDAO.execute("FROM " + c.getName() + " WHERE code = :c", QueryParameters.with("c", code).parameters());
        if (CollectionUtils.isNotEmpty(list)) { return list.get(0); }
        return null;
    }
}
