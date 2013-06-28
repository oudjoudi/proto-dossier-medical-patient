package fr.poc.dmp.web.pages.dossier.consultation;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import fr.exanpe.t5.lib.annotation.ContextPageReset;
import fr.poc.dmp.domain.business.DossierManager;
import fr.poc.dmp.domain.model.Consultation;
import fr.poc.dmp.domain.model.Dossier;
import fr.poc.dmp.domain.model.Hospitalisation;
import fr.poc.dmp.web.pages.dossier.ConsulterDossier;

// TODO rattachement
public class AjouterConsultation
{
    @Persist
    private Dossier dossier;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private Consultation consultation;

    @Inject
    private DossierManager dossierManager;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Component(id = "form")
    private Form form;

    @Property
    private Hospitalisation loopHospitalisation;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private boolean rattach;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private Integer rattachId;

    void onActivate()
    {
        if (consultation == null)
        {
            consultation = new Consultation();
        }
        if (consultation.getDateConsultation() == null)
        {
            consultation.setDateConsultation(new Date());
        }

    }

    @ContextPageReset
    public void reset()
    {
        consultation = null;
        rattach = false;
        rattachId = null;
    }

    @OnEvent(value = EventConstants.VALIDATE, component = "form")
    void validerAjout()
    {
        boolean valid = consultation.getDateConsultation() != null && StringUtils.isNotEmpty(consultation.getIdentifiantMedecin())
                && StringUtils.isNotEmpty(consultation.getNomMedecin()) && StringUtils.isNotEmpty(consultation.getPrenomMedecin())
                && consultation.getServiceConsultation() != null;

        if (!valid)
        {
            form.recordError("Veuillez saisir l'ensemble des données obligatoires");
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
        dossierManager.ajouterConsultation(dossier, consultation, rattachId);
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

    public String getRattachDisplay()
    {
        return rattach ? "" : "none";
    }
}
