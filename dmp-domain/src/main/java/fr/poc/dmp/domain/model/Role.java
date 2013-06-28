package fr.poc.dmp.domain.model;

import java.io.Serializable;
import java.util.List;

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

/**
 * Rôles attribués aux utilisateurs de l'application.
 */
@NamedQueries(@NamedQuery(name = Role.FIND_BY_ROLE_NAME, query = "FROM Role WHERE name = :name"))
@Entity
public class Role implements Serializable
{
    /**
     * serialUID
     */
    private static final long serialVersionUID = 2186257499592013844L;

    /**
     * Identifiants des requetes NamedQuery
     */
    public static final String FIND_BY_ROLE_NAME = "Role.findByRoleName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    /**
     * Fetch.EAGER because User needs all the authorities
     * all the time
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "J_ROLE_AUTHORITY", joinColumns = @JoinColumn(name = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_authority"))
    private List<Authority> authorities;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public Role()
    {
        super();
    }

    public long getId()
    {
        return id;
    }

    public List<Authority> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities)
    {
        this.authorities = authorities;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
