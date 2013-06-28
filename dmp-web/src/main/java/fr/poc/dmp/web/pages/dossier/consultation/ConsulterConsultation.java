package fr.poc.dmp.web.pages.dossier.consultation;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import fr.poc.dmp.domain.business.DossierManager;
import fr.poc.dmp.domain.model.Consultation;

public class ConsulterConsultation
{
    @Persist
    @Property
    private Consultation consultation;

    @Inject
    private DossierManager dossierManager;

    void onActivate(Integer id)
    {
        consultation = dossierManager.findConsultationDossierById(id);
    }

    public String getDateToString()
    {
        return FastDateFormat.getInstance("dd/MM/yyyy").format(consultation.getDateConsultation());
    }
}
