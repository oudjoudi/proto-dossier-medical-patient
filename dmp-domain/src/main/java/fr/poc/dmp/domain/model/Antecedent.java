package fr.poc.dmp.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ANTECEDENT")
public class Antecedent implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 2943819264509860853L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String type;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date dateSaisie;

    @ManyToOne(optional = false)
    private Dossier dossier;

    private Integer techindex;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the dateSaisie
     */
    public Date getDateSaisie()
    {
        return dateSaisie;
    }

    /**
     * @param dateSaisie the dateSaisie to set
     */
    public void setDateSaisie(Date dateSaisie)
    {
        this.dateSaisie = dateSaisie;
    }

    /**
     * @return the dossier
     */
    public Dossier getDossier()
    {
        return dossier;
    }

    /**
     * @param patient the dossier to set
     */
    public void setDossier(Dossier dossier)
    {
        this.dossier = dossier;
    }

}
