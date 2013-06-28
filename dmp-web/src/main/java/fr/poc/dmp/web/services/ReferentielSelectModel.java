package fr.poc.dmp.web.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;

import fr.poc.dmp.domain.model.referentiel.AbstractReferentielModel;

public class ReferentielSelectModel
{

    public SelectModel create(List<? extends AbstractReferentielModel> list)
    {
        List<OptionModel> olist = new ArrayList<OptionModel>(list.size());

        for (AbstractReferentielModel ref : list)
        {
            olist.add(new OptionModelImpl(ref.getLibelle(), ref.getCode()));
        }

        return new SelectModelImpl(null, olist);
    }
}
