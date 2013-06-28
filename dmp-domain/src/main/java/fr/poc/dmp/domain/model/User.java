/**
 * 
 */
package fr.poc.dmp.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * Objet représentant l'utilisateur connecté.
 * Implémente l'interface {@link UserDetails} de Spring Security.
 * 
 * @author jmaupoux
 */
@Table(name = "utilisateur")
@Entity
@NamedQueries(
{ @NamedQuery(name = User.FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username") })
public class User implements UserDetails
{
    /**
     * serialUID
     */
    private static final long serialVersionUID = -6157226898787740763L;

    /**
     * Identifiants des requetes NamedQuery
     */
    public static final String FIND_BY_USERNAME = "User.findByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * For SUBSELECT explanation,
     * 
     * @see http://opensource.atlassian.com/projects/hibernate/browse/EJB-346
     * @see http://opensource.atlassian.com/projects/hibernate/browse/HHH-1718
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @Column(nullable = false)
    // TODO delete Cache ! just for tests purpose
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "J_USER_ROLE", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles;

    /**
     * Ajoute un role a un utilisateur.
     * 
     * @param role le role a ajouter
     */
    public void addRole(Role role)
    {
        Assert.notNull(role, "role");
        if (roles == null)
        {
            roles = new ArrayList<Role>();
        }
        if (!roles.contains(role))
        {
            roles.add(role);
        }
    }

    /**
     * Ajoute une liste de roles a un utilisateur.
     * 
     * @param roles
     */
    public void setRoles(List<Role> roles)
    {
        Assert.notNull(roles);
        this.roles = roles;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    /**
     * Permet à Spring Security d'accéder aux autorisations de
     * l'utilisateur courant. {@link UserDetails}
     */
    public Collection<GrantedAuthority> getAuthorities()
    {
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

        for (Role r : roles)
        {
            for (Authority authority : r.getAuthorities())
            {
                GrantedAuthority ga = authority;
                auths.add(ga);
            }
        }
        return auths;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return the username
     */
    @Override
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

}
