package fr.poc.dmp.domain.business;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.poc.dmp.domain.core.business.DefaultManager;
import fr.poc.dmp.domain.model.referentiel.AbstractReferentielModel;

public interface ReferentielManager extends DefaultManager<Object, Serializable>
{
    public <T extends AbstractReferentielModel, PK extends Serializable> void delete(Class<T> c, PK id);

    @Transactional(readOnly = true)
    public <T extends AbstractReferentielModel, PK extends Serializable> T find(Class<T> c, PK id);

    public long count(Class<? extends AbstractReferentielModel> c);

    public <T extends AbstractReferentielModel> List<T> list(Class<T> c);

    public <T extends AbstractReferentielModel> T findByCode(Class<T> c, String code);
}
