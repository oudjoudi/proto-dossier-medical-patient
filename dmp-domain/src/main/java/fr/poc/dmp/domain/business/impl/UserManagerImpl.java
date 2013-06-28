/**
 * 
 */
package fr.poc.dmp.domain.business.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import fr.poc.dmp.domain.model.User;

import fr.poc.dmp.common.exception.BusinessException;
import fr.poc.dmp.domain.business.impl.UserManagerImpl;
import fr.poc.dmp.domain.core.dao.QueryParameters;
import fr.poc.dmp.domain.model.Role;
import fr.poc.dmp.domain.model.User;

import fr.poc.dmp.domain.business.UserManager;
import fr.poc.dmp.domain.model.User;
import fr.poc.dmp.domain.security.DmpSecurityContext;
import fr.poc.dmp.domain.core.dao.CrudDAO;
import fr.poc.dmp.domain.core.dao.QueryParameters;

/**
 * @author jmaupoux
 */
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
@Component("userManager")
public class UserManagerImpl implements UserManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerImpl.class);
    
    @Autowired
    private CrudDAO crudDAO;

    @Autowired
    private DmpSecurityContext securityContext;

    @Autowired
    private SaltSource saltSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * (non-Javadoc)
     * @see fr.poc.dmp.domain.business.UserManager#findByUsername(java.lang.String)
     */
    @Override
    public User findByUsername(String username)
    {
        Assert.hasText(username, String.format("Le parametre username ne peut pas etre null ou vide"));
        User result;
        try
        {

            result = crudDAO.findUniqueWithNamedQuery(User.FIND_BY_USERNAME, QueryParameters.with("username", username).parameters());
        }
        catch (NoResultException e)
        {
            LOGGER.info("L'utilisateur identifi� par: " + username + " n'existe pas.");
            result = null;
        }
        catch (EmptyResultDataAccessException e)
        {
            LOGGER.info("User identified by username: " + username + " n'existe pas.");
            result = null;
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = BusinessException.class)
    public void createUser(User user, List<Role> roles) throws BusinessException
    {
        if (!isAvailableUsername(user.getUsername())) { throw new BusinessException("L'utilisateur: " + user.getUsername() + " existe d�j�."); }

        String pass = user.getPassword();
        user.setPassword(this.passwordEncoder.encodePassword(pass, this.saltSource.getSalt(user)));
        for (Role role : roles)
        {
            Role realRole = crudDAO.findUniqueWithNamedQuery(Role.FIND_BY_ROLE_NAME, QueryParameters.with("name", role.getName()).parameters());
            if (realRole == null) { throw new BusinessException("Le role: " + role.getName() + " n'existe pas !"); }
            user.addRole(realRole);
        }
        crudDAO.create(user);
    }

    /*
     * (non-Javadoc)
     * @see fr.poc.dmp.domain.business.UserManager#isAvailableName(java.lang.String)
     */
    public boolean isAvailableUsername(String username)
    {
        return findByUsername(username) == null;
    }    
    
    @Override
    public void cleanRoles(User user)
    {
        user.getRoles().clear();
        crudDAO.update(user);
    }
    
    public DmpSecurityContext getSecurityContext()
    {
        return securityContext;
    }

    public void setSecurityContext(DmpSecurityContext securityContext)
    {
        this.securityContext = securityContext;
    }

}
