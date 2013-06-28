package fr.poc.dmp.web.pages.dossier;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;

import fr.poc.dmp.domain.model.Dossier;

public class ConfirmerCreationDossier
{
    @Persist(PersistenceConstants.FLASH)
    private Dossier dossier;

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
