package fr.poc.dmp.domain.util;

import org.apache.commons.lang.RandomStringUtils;

public final class DossierUtils
{

    // RG_MET_001
    public static String generateIdentifiant()
    {
        return generateCle(RandomStringUtils.random(9, "0123456789"));
    }

    public static String generateCle(String identifiant9)
    {
        String identifiant = identifiant9;

        String keyGenerator = identifiant;

        int count = 0;
        for (int i = 0; i < keyGenerator.length(); i++)
        {
            int current = (i % 2 == 0) ? Integer.parseInt(keyGenerator.charAt(i) + "") * 2 : Integer.parseInt(keyGenerator.charAt(i) + "");

            if (("" + current).length() == 2)
            {
                String curS = ("" + current);
                current = Integer.parseInt(curS.substring(0, 1)) + Integer.parseInt(curS.substring(1, 2));
            }

            count += current;
        }

        String key = "" + (10 - (count % 10));

        return identifiant + key;

    }
}
