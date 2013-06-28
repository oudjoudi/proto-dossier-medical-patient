package fr.poc.dmp.web.pages.dossier;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import fr.poc.dmp.common.exception.BusinessException;
import fr.poc.dmp.domain.business.DossierManager;
import fr.poc.dmp.domain.business.ReferentielManager;
import fr.poc.dmp.domain.model.Dossier;
import fr.poc.dmp.domain.model.Patient;
import fr.poc.dmp.domain.model.referentiel.Sexe;

public class CreerDossier
{

    @Property
    @Persist
    private Patient patient;

    @Component(id = "form")
    private Form form;

    @Inject
    private DossierManager patientManager;

    @Inject
    private ReferentielManager referentielManager;

    @InjectPage
    private ConfirmerCreationDossier confirmerCreationDossier;

    @Property
    private List<Sexe> sexes;

    @Property
    private Sexe loopSexe;

    void onActivate()
    {
        if (patient == null || patient.getId() != null)
        {
            patient = new Patient();
        }

        sexes = referentielManager.list(Sexe.class);
    }

    @OnEvent(value = EventConstants.VALIDATE, component = "form")
    void validerRecherche() throws ValidationException
    {
        // RG_MET_005
        if (StringUtils.isEmpty(patient.getNom()) || StringUtils.isEmpty(patient.getPrenom()) || patient.getSexe() == null
                || patient.getDateNaissance() == null || StringUtils.isEmpty(patient.getAdresse()))
        {
            form.recordError("Veuillez saisir l'ensemble des données obligatoires");
        }

        // RG_MET_008
        if (StringUtils.isNotEmpty(patient.getNomMutuelle()) && StringUtils.isEmpty(patient.getNumeroMutuelle()))
        {
            form.recordError("Le numéro de mutelle est obligatoire si le nom de la mutuelle est renseigné");
        }
    }

    Object onSuccessFromForm()
    {
        Dossier d = null;

        try
        {
            d = patientManager.createDossier(patient);
        }
        catch (BusinessException b)
        {
            form.recordError(b.getMessage());
            return this;
        }

        confirmerCreationDossier.setDossier(d);

        return confirmerCreationDossier;
    }
}
