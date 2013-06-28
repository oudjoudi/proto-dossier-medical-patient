package fr.poc.dmp.web.pages.dossier.hospitalisation;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import fr.exanpe.t5.lib.annotation.ContextPageReset;
import fr.poc.dmp.domain.business.DossierManager;
import fr.poc.dmp.domain.model.Dossier;
import fr.poc.dmp.domain.model.Hospitalisation;
import fr.poc.dmp.web.pages.dossier.ConsulterDossier;

public class AjouterHospitalisation
{
    @Persist
    private Dossier dossier;

    @Persist
    @Property
    private Hospitalisation hospitalisation;

    @Inject
    private DossierManager dossierManager;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Component(id = "form")
    private Form form;

    @ContextPageReset
    public void reset()
    {
        hospitalisation = null;
    }

    void onActivate()
    {
        if (hospitalisation == null)
        {
            hospitalisation = new Hospitalisation();
        }
        if (hospitalisation.getDateEntree() == null)
        {
            hospitalisation.setDateEntree(new Date());
        }

    }

    @OnEvent(value = EventConstants.VALIDATE, component = "form")
    void validerAjout()
    {
        boolean valid = hospitalisation.getDateEntree() != null && StringUtils.isNotEmpty(hospitalisation.getIdentifiantHospitalisation());

        if (!valid)
        {
            form.recordError("Veuillez saisir l'ensemble des données obligatoires");
        }

        boolean sortieValid = hospitalisation.getDateSortie() == null || (hospitalisation.getDateSortie() != null && hospitalisation.getMotifSortie() != null);

        if (!sortieValid)
        {
            form.recordError("Veuillez renseigner le motif de sortie");
        }

        if (hospitalisation.getDateEntree() != null && hospitalisation.getDateSortie() != null)
        {
            if (hospitalisation.getDateEntree().after(hospitalisation.getDateSortie()))
            {
                form.recordError("La date de sortie ne peut être antérieure à la date d'entrée");
            }
        }
    }

    @OnEvent(value = EventConstants.ACTION, component = "annuler")
    Object annuler()
    {
        return goToDMP();
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "form")
    Object ajouter()
    {
        dossierManager.ajouterHospitalisation(dossier, hospitalisation);
        return goToDMP();
    }

    private Link goToDMP()
    {
        return pageRenderLinkSource.createPageRenderLinkWithContext(ConsulterDossier.class, dossier.getIdentifiant());
    }

    /**
     * @return the dossier
     */
    public Dossier getDossier()
    {
        return dossier;
    }

    /**
     * @param dossier the dossier to set
     */
    public void setDossier(Dossier dossier)
    {
        this.dossier = dossier;
    }

}
