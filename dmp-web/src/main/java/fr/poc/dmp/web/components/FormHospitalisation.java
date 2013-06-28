package fr.poc.dmp.web.components;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import fr.poc.dmp.domain.business.ReferentielManager;
import fr.poc.dmp.domain.model.Hospitalisation;
import fr.poc.dmp.domain.model.referentiel.MotifSortie;
import fr.poc.dmp.web.services.ReferentielSelectModel;

public class FormHospitalisation
{
    @Parameter(autoconnect = true, allowNull = false)
    @Property
    private Hospitalisation hospitalisation;

    @Inject
    private ReferentielManager referentielManager;
    @Inject
    private ReferentielSelectModel referentielSelectModel;

    @Property
    @Persist
    private SelectModel motifsSelect;

    void setupRender()
    {
        motifsSelect = referentielSelectModel.create(referentielManager.list(MotifSortie.class));
    }

    public String getDateToString()
    {
        return FastDateFormat.getInstance("dd/MM/yyyy").format(hospitalisation.getDateEntree());
    }

    public String getDateDuJourString()
    {
        return FastDateFormat.getInstance("dd/MM/yyyy").format(new Date());
    }
}
