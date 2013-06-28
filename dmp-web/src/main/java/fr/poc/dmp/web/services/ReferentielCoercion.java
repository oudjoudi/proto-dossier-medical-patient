package fr.poc.dmp.web.services;

import org.apache.tapestry5.ioc.services.Coercion;

import fr.poc.dmp.domain.business.ReferentielManager;
import fr.poc.dmp.domain.model.referentiel.AbstractReferentielModel;

public class ReferentielCoercion<T extends AbstractReferentielModel> implements Coercion<String, T>
{

    private ReferentielManager referentielManager;

    private Class<T> t;

    public ReferentielCoercion(ReferentielManager referentielManager, Class<T> t)
    {
        this.t = t;
        this.referentielManager = referentielManager;
    }

    @Override
    public T coerce(String input)
    {
        return referentielManager.findByCode(t, input);
    }
}
