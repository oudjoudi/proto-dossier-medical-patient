/**
 * 
 */
package fr.poc.dmp.domain.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

import fr.poc.dmp.domain.base.DmpDomainBaseTest;
import fr.poc.dmp.domain.model.Dossier;

/**
 * @author jmaupoux
 */
@DataSet("/dataset/DossierManagerTest.xml")
public class DossierManagerTest extends DmpDomainBaseTest
{
    @SpringBean("dossierManager")
    private DossierManager dossierManager;

    @Test
    public void findByIdentifiant()
    {
        Dossier expected = dossierManager.findByIdentifiant("2");
        Assert.assertNull(expected);

        expected = dossierManager.findByIdentifiant("3");
        Assert.assertNotNull(expected);
    }

    @Test
    public void search() throws ParseException
    {
        Dossier expected = dossierManager.searchByNomDateNaissance("totoo", new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01"));
        Assert.assertNotNull(expected);
    }
}
