package fr.poc.dmp.web.pages.dossier.hospitalisation;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import fr.poc.dmp.domain.business.DossierManager;
import fr.poc.dmp.domain.model.Consultation;
import fr.poc.dmp.domain.model.Hospitalisation;

public class ConsulterHospitalisation
{
    @Inject
    private DossierManager dossierManager;

    @Persist
    @Property
    private Hospitalisation hospitalisation;

    @Property
    private Consultation loopConsultation;

    void onActivate(Integer id)
    {
        hospitalisation = dossierManager.findHospitalisationDossierById(id);
    }

    public String getDateToString()
    {
        return FastDateFormat.getInstance("dd/MM/yyyy").format(hospitalisation.getDateEntree());
    }
}
