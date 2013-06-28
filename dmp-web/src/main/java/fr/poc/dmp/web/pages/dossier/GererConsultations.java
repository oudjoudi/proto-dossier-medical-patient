package fr.poc.dmp.web.pages.dossier;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;

public class GererConsultations
{
    @Property
    private String nomMedecin;
    @Property
    private String dateRencontre;
    @Property
    private String typeContact;
    @Property
    private String donneesSignificatives;
    @Property
    private String conclusionSynthese;
    @Property
    private String decision;

    @InjectPage
    private ConsulterDossier consulterDossier;

    @OnEvent(value = "ajouter")
    Object onAjouter()
    {
        // consulterDossier.setSuccessAjoutConsultation(true);
        return consulterDossier;
    }

    @OnEvent(value = "annuler")
    Object onAnnuler()
    {
        return consulterDossier;
    }
}
