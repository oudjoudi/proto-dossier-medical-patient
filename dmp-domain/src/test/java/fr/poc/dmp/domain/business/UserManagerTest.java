/**
 * 
 */
package fr.poc.dmp.domain.business;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringBean;

import fr.poc.dmp.common.exception.BusinessException;
import fr.poc.dmp.domain.base.DmpDomainBaseTest;
import fr.poc.dmp.domain.model.Role;
import fr.poc.dmp.domain.model.User;

/**
 * @author jmaupoux
 */
@DataSet("/dataset/DmpBusinessTest.xml")
public class UserManagerTest extends DmpDomainBaseTest
{
    @SpringBean("userManager")
    private UserManager userManager;

    private static final String USERNAME_PASSWORD_ADMIN = "admin";
    private static final String ROLE_ADMIN = "ADMIN";
    
    @Test
    public void findByUsername()
    {
        User expected = userManager.findByUsername(USERNAME_PASSWORD_ADMIN);
        ReflectionAssert.assertPropertyReflectionEquals("username", USERNAME_PASSWORD_ADMIN, expected);
        ReflectionAssert.assertPropertyReflectionEquals("password", USERNAME_PASSWORD_ADMIN, expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByUsernameWithNull()
    {
        userManager.findByUsername(null);
    }
    
    @Test
    public void testIsAvailableUsername()
    {
        // L'utilisateur existe d�j�
        boolean res = userManager.isAvailableUsername(USERNAME_PASSWORD_ADMIN);
        Assert.assertFalse(res);
        // L'utilisateur n'existe pas encore
        res = userManager.isAvailableUsername("zzzzz");
        Assert.assertTrue(res);
    }

    @Test
    public void createUserNominal() throws BusinessException
    {
        User actual = new User();
        actual.setUsername("user");
        actual.setPassword("password");
        Role role = new Role();
        role.setName(ROLE_ADMIN);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        userManager.createUser(actual, roles);
        User expected = userManager.findByUsername(actual.getUsername());
        Assert.assertEquals(expected.getUsername(), actual.getUsername());
    }    

    @Test
    public void cleanRoles() throws BusinessException
    {
        User actual = new User();
        actual.setUsername("user");
        actual.setPassword("password");
        Role role = new Role();
        role.setName(ROLE_ADMIN);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        userManager.createUser(actual, roles);

        User expected = userManager.findByUsername(actual.getUsername());

        Assert.assertFalse(expected.getRoles().isEmpty());

        userManager.cleanRoles(expected);

        expected = userManager.findByUsername(actual.getUsername());

        Assert.assertTrue(expected.getRoles().isEmpty());
    }
}
