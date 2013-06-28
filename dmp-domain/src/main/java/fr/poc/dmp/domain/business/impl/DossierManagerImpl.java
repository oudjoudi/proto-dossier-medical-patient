/**
 * 
 */
package fr.poc.dmp.domain.business.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.poc.dmp.common.exception.BusinessException;
import fr.poc.dmp.domain.business.DossierManager;
import fr.poc.dmp.domain.core.business.impl.DefaultManagerImpl;
import fr.poc.dmp.domain.core.dao.CrudDAO;
import fr.poc.dmp.domain.core.dao.QueryParameters;
import fr.poc.dmp.domain.model.Consultation;
import fr.poc.dmp.domain.model.Dossier;
import fr.poc.dmp.domain.model.Hospitalisation;
import fr.poc.dmp.domain.model.Patient;
import fr.poc.dmp.domain.util.DossierUtils;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
@Component("dossierManager")
public class DossierManagerImpl extends DefaultManagerImpl<Dossier, Integer> implements DossierManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DossierManagerImpl.class);

    @Autowired
    private CrudDAO crudDAO;

    @Transactional(readOnly = false)
    @Override
    public Dossier createDossier(Patient patient) throws BusinessException
    {
        // RG_MET_009
        if (!validateUnicity(patient)) { throw new BusinessException("Dossier existant pour " + patient.getNom() + " " + patient.getPrenom()); }

        Dossier d = new Dossier();
        // RG_MET_001
        d.setIdentifiant(DossierUtils.generateIdentifiant());
        d.setPatient(patient);
        patient.setDossier(d);

        super.create(d);

        return d;

    }

    @Override
    public Dossier searchByNomDateNaissance(String nom, Date dateNaissance)
    {
        List<Dossier> dossiers = crudDAO.findMaxResultsWithNamedQuery(
                Dossier.QUERY_SEARCH_NAME_DATENAISSANCE,
                QueryParameters.with("nom", nom).and("dateNaissance", dateNaissance).parameters(),
                1);
        if (CollectionUtils.isEmpty(dossiers)) { return null; }

        return dossiers.get(0);
    }

    public Dossier findByIdentifiant(String identifiant)
    {
        List<Dossier> dossiers = crudDAO.findMaxResultsWithNamedQuery(
                Dossier.QUERY_SEARCH_BY_IDENTIFIANT_DOSSIER,
                QueryParameters.with("identifiant", identifiant).parameters(),
                1);

        if (CollectionUtils.isEmpty(dossiers)) { return null; }

        dossiers.get(0).getHospitalisations().size();

        return dossiers.get(0);
    }

    private boolean validateUnicity(Patient patient)
    {
        return crudDAO.count(
                Dossier.QUERY_SEARCH_UNICITY,
                QueryParameters.with("nom", patient.getNom()).and("prenom", patient.getPrenom()).and("dateNaissance", patient.getDateNaissance()).parameters()) == 0;
    }

    @Override
    public Hospitalisation findHospitalisationDossierById(Integer id)
    {
        Hospitalisation h = crudDAO.find(Hospitalisation.class, id);
        h.getDossier();
        h.getDossier().getPatient();
        h.getConsultations().size();

        return h;
    }

    @Override
    public Consultation findConsultationDossierById(Integer id)
    {
        Consultation c = crudDAO.find(Consultation.class, id);
        c.getDossier();
        c.getDossier().getPatient();

        return c;
    }

    @Override
    @Transactional(readOnly = false)
    public void ajouterConsultation(Dossier dossier, Consultation c, Integer hospitalisationId)
    {
        dossier = crudDAO.find(Dossier.class, dossier.getId());
        dossier.getConsultations().add(c);
        c.setDossier(dossier);
        crudDAO.update(dossier);

        if (hospitalisationId != null)
        {
            Hospitalisation h = crudDAO.find(Hospitalisation.class, hospitalisationId);
            c.setHospitalisation(h);
            h.getConsultations().add(c);

        }

    }

    @Override
    @Transactional(readOnly = false)
    public void ajouterHospitalisation(Dossier dossier, Hospitalisation hospitalisation)
    {
        dossier = crudDAO.find(Dossier.class, dossier.getId());
        dossier.getHospitalisations().add(hospitalisation);
        hospitalisation.setDossier(dossier);
        crudDAO.update(dossier);
    }

}
