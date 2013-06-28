package fr.poc.dmp.domain.util;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;

public class DossierUtilsTest extends UnitilsTestNG
{
    @Test
    public void testSendEmail()
    {
        String identifiant = "245897654";
        Assert.assertEquals(DossierUtils.generateCle(identifiant).substring(9), "1");
    }
}
