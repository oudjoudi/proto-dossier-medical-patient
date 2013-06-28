/**
 * 
 */
package fr.poc.dmp.domain.business;

import java.util.Date;

import fr.poc.dmp.common.exception.BusinessException;
import fr.poc.dmp.domain.core.business.DefaultManager;
import fr.poc.dmp.domain.model.Consultation;
import fr.poc.dmp.domain.model.Dossier;
import fr.poc.dmp.domain.model.Hospitalisation;
import fr.poc.dmp.domain.model.Patient;

public interface DossierManager extends DefaultManager<Dossier, Integer>
{
    Dossier createDossier(Patient patient) throws BusinessException;

    Dossier searchByNomDateNaissance(String nom, Date dateNaissance);

    Dossier findByIdentifiant(String identifiant);

    Hospitalisation findHospitalisationDossierById(Integer id);

    Consultation findConsultationDossierById(Integer id);

    void ajouterConsultation(Dossier d, Consultation c, Integer hospitalisationId);

    void ajouterHospitalisation(Dossier dossier, Hospitalisation hospitalisation);

}
