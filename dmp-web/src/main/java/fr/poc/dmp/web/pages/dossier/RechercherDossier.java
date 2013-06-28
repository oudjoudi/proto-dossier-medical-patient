package fr.poc.dmp.web.pages.dossier;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import fr.poc.dmp.domain.business.DossierManager;
import fr.poc.dmp.domain.model.Dossier;

public class RechercherDossier
{
    @Property
    @Persist(PersistenceConstants.FLASH)
    private String identifiant;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String nom;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private Date dateNaissance;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private boolean notFound;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private boolean byIdentifiant;

    @Inject
    private DossierManager patientManager;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Component(id = "form")
    private Form form;

    @OnEvent(value = EventConstants.VALIDATE, component = "form")
    void validerRecherche()
    {
        if (StringUtils.isEmpty(identifiant) && (StringUtils.isEmpty(nom) || dateNaissance == null))
        {
            form.recordError("Veuillez renseigner l'identifiant du dossier ou le couple nom et date de naissance");
        }
        if (StringUtils.isNotEmpty(identifiant) && (StringUtils.isNotEmpty(nom) || dateNaissance != null))
        {
            form.recordError("Veuillez renseigner l'identifiant du dossier ou le couple nom et date de naissance");
        }
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "form")
    Object rechercherDossier()
    {
        Dossier dossier = null;
        if (StringUtils.isNotEmpty(identifiant))
        {
            byIdentifiant = true;
            dossier = patientManager.findByIdentifiant(identifiant);
        }
        else
        {
            dossier = patientManager.searchByNomDateNaissance(nom, dateNaissance);
        }

        if (dossier == null)
        {
            notFound = true;
            return this;
        }

        return pageRenderLinkSource.createPageRenderLinkWithContext(ConsulterDossier.class, dossier.getIdentifiant());
    }
}
