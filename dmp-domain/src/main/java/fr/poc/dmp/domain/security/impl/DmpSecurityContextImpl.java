/**
 * 
 */
package fr.poc.dmp.domain.security.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import fr.poc.dmp.domain.model.User;
import fr.poc.dmp.domain.security.DmpSecurityContext;

/**
 * Implementation du SecurityContext propre à dmp
 * 
 * @author jmaupoux
 */
@Component("dmpSecurityContext")
public class DmpSecurityContextImpl implements DmpSecurityContext
{
    /*
     * (non-Javadoc)
     * @see
     * fr.poc.dmp.domain.security.DmpSecurityContext#login(fr.poc.dmp.domain.model
     * .User)
     */
    @Override
    public void login(User user)
    {
        Assert.notNull(user, "user");
        UsernamePasswordAuthenticationToken logged = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(logged);
    }

    /*
     * (non-Javadoc)
     * @see fr.poc.dmp.domain.security.DmpSecurityContext#isLoggedIn()
     */
    @Override
    public boolean isLoggedIn()
    {
        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null)
        {
            if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName())) { return false; }
            return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see fr.poc.dmp.domain.security.DmpSecurityContext#getUser()
     */
    @Override
    public User getUser()
    {
        User user = null;
        if (isLoggedIn())
        {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User)
            {
                user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            }
        }
        return user;
    }

    /*
     * (non-Javadoc)
     * @see fr.poc.dmp.domain.security.DmpSecurityContext#logout()
     */
    @Override
    public void logout()
    {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
    }

}
