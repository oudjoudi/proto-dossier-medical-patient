package fr.poc.dmp.web.components;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import fr.poc.dmp.domain.business.ReferentielManager;
import fr.poc.dmp.domain.model.Consultation;
import fr.poc.dmp.domain.model.referentiel.DecisionConsultation;
import fr.poc.dmp.domain.model.referentiel.ServiceConsultation;
import fr.poc.dmp.web.services.ReferentielSelectModel;

public class FormConsultation
{
    @Parameter(autoconnect = true, allowNull = false)
    @Property
    private Consultation consultation;

    @Inject
    private ReferentielManager referentielManager;
    @Inject
    private ReferentielSelectModel referentielSelectModel;

    @Property
    @Persist
    private SelectModel servicesSelect;

    @Property
    @Persist
    private SelectModel decisionsSelect;

    void setupRender()
    {
        servicesSelect = referentielSelectModel.create(referentielManager.list(ServiceConsultation.class));
        decisionsSelect = referentielSelectModel.create(referentielManager.list(DecisionConsultation.class));
    }

    public String getDateToString()
    {
        return FastDateFormat.getInstance("dd/MM/yyyy").format(consultation.getDateConsultation());
    }

    public String getDisplayCommentaires()
    {
        if (consultation.getDecisions() == null) { return "none"; }
        return "";
    }
}
