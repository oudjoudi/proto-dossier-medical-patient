package fr.poc.dmp.web.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import fr.poc.dmp.domain.model.Dossier;
import fr.poc.dmp.web.pages.dossier.consultation.AjouterConsultation;
import fr.poc.dmp.web.pages.dossier.hospitalisation.AjouterHospitalisation;

public class SousMenuDossier
{
    @Parameter(required = true)
    @Property
    private Dossier dossier;

    @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "false")
    private boolean consulterdossier;

    @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "false")
    private boolean ajouterconsultation;

    @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "false")
    private boolean ajouterhospitalisation;

    @InjectPage
    private AjouterConsultation ajouterConsultation;

    @InjectPage
    private AjouterHospitalisation ajouterHospitalisation;

    @OnEvent(value = EventConstants.ACTION, component = "addConsultation")
    public Object addConsultation()
    {
        ajouterConsultation.setDossier(dossier);
        ajouterConsultation.reset();
        return ajouterConsultation;
    }

    @OnEvent(value = EventConstants.ACTION, component = "addHospitalisation")
    public Object addHospitalisation()
    {
        ajouterHospitalisation.setDossier(dossier);
        ajouterHospitalisation.reset();
        return ajouterHospitalisation;
    }

    public String getClassConsulter()
    {
        return consulterdossier == false ? "" : "active";
    }

    public String getClassAjouterConsultation()
    {
        return ajouterconsultation == false ? "" : "active";
    }

    public String getClassAjouterHospitalisation()
    {
        return ajouterhospitalisation == false ? "" : "active";
    }

}
