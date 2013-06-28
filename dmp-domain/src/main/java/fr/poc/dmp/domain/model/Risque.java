package fr.poc.dmp.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RISQUE")
public class Risque implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -2995555188629746589L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String facteurRisque;

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
     * @return the facteurRisque
     */
    public String getFacteurRisque()
    {
        return facteurRisque;
    }

    /**
     * @param facteurRisque the facteurRisque to set
     */
    public void setFacteurRisque(String facteurRisque)
    {
        this.facteurRisque = facteurRisque;
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
