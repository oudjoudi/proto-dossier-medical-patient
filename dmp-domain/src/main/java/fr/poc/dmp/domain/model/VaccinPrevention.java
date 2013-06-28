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
@Table(name = "VACCIN_PREV")
public class VaccinPrevention implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -6888103192630229706L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String vaccinsPrevus;

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
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * @return the vaccinsPrevus
     */
    public String getVaccinsPrevus()
    {
        return vaccinsPrevus;
    }

    /**
     * @param vaccinsPrevus the vaccinsPrevus to set
     */
    public void setVaccinsPrevus(String vaccinsPrevus)
    {
        this.vaccinsPrevus = vaccinsPrevus;
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
    public void setPatient(Dossier dossier)
    {
        this.dossier = dossier;
    }

}
