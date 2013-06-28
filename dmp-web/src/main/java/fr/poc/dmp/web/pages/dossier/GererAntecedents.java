package fr.poc.dmp.web.pages.dossier;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;

public class GererAntecedents
{
    @Property
    private String personnels;
    @Property
    private String familiaux;
    @Property
    private String allergies;
    @Property
    private String facteurRisque;
    @Property
    private String vaccinations;
    @Property
    private String prevention;
    @Property
    private String evenements;

    @InjectPage
    private ConsulterDossier consulterDossier;

    @OnEvent(value = "modifier")
    Object onModifier()
    {
        // consulterDossier.setSuccessModifAntecedents(true);
        return consulterDossier;
    }

    @OnEvent(value = "annuler")
    Object onAnnuler()
    {
        return consulterDossier;
    }
}
