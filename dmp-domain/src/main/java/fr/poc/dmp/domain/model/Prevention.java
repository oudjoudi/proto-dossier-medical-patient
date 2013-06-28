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
@Table(name = "PREVENTION")
public class Prevention implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 2499080888370422850L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String prevention;

    @Temporal(TemporalType.DATE)
    private Date datePrevention;

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
     * @return the prevention
     */
    public String getPrevention()
    {
        return prevention;
    }

    /**
     * @param prevention the prevention to set
     */
    public void setPrevention(String prevention)
    {
        this.prevention = prevention;
    }

    /**
     * @return the datePrevention
     */
    public Date getDatePrevention()
    {
        return datePrevention;
    }

    /**
     * @param datePrevention the datePrevention to set
     */
    public void setDatePrevention(Date datePrevention)
    {
        this.datePrevention = datePrevention;
    }

    /**
     * @return the patient
     */
    public Dossier getDossier()
    {
        return dossier;
    }

    /**
     * @param patient the patient to set
     */
    public void setDossier(Dossier dossier)
    {
        this.dossier = dossier;
    }

}
