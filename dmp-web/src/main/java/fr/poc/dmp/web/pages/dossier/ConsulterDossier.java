package fr.poc.dmp.web.pages.dossier;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import fr.poc.dmp.domain.business.DossierManager;
import fr.poc.dmp.domain.model.Consultation;
import fr.poc.dmp.domain.model.Dossier;
import fr.poc.dmp.domain.model.Hospitalisation;

public class ConsulterDossier
{
    @Persist
    @Property
    private Dossier dossier;

    @Inject
    private DossierManager dossierManager;

    @Property
    private Hospitalisation loopHospitalisation;

    @Property
    private Consultation loopConsultation;

    void onActivate(String identifiant)
    {
        dossier = dossierManager.findByIdentifiant(identifiant);
    }

    public Hospitalisation getHospitalisationEnCours()
    {
        if (CollectionUtils.isNotEmpty(dossier.getHospitalisations()) && dossier.getHospitalisations().get(0).getDateSortie() == null) { return dossier
                .getHospitalisations().get(0); }
        return null;
    }

}
