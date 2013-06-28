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

import fr.poc.dmp.domain.model.referentiel.DecisionConsultation;
import fr.poc.dmp.domain.model.referentiel.ServiceConsultation;

@Entity
@Table(name = "CONSULTATION")
public class Consultation implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -2261077934570420896L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String identifiantMedecin;

    @Temporal(TemporalType.DATE)
    private Date dateConsultation;

    private String nomMedecin;

    private String prenomMedecin;

    @ManyToOne(optional = false)
    private ServiceConsultation serviceConsultation;

    private String donneesSignificatives;

    private String conclusions;

    @ManyToOne(optional = true)
    private DecisionConsultation decisions;

    private String commentaires;

    @ManyToOne(optional = false)
    private Dossier dossier;

    @ManyToOne
    private Hospitalisation hospitalisation;

    // private Integer techindex;

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
     * @return the identifiantMedecin
     */
    public String getIdentifiantMedecin()
    {
        return identifiantMedecin;
    }

    /**
     * @param identifiantMedecin the identifiantMedecin to set
     */
    public void setIdentifiantMedecin(String identifiantMedecin)
    {
        this.identifiantMedecin = identifiantMedecin;
    }

    /**
     * @return the dateConsultation
     */
    public Date getDateConsultation()
    {
        return dateConsultation;
    }

    /**
     * @param dateConsultation the dateConsultation to set
     */
    public void setDateConsultation(Date dateConsultation)
    {
        this.dateConsultation = dateConsultation;
    }

    /**
     * @return the nomMedecin
     */
    public String getNomMedecin()
    {
        return nomMedecin;
    }

    /**
     * @param nomMedecin the nomMedecin to set
     */
    public void setNomMedecin(String nomMedecin)
    {
        this.nomMedecin = nomMedecin;
    }

    /**
     * @return the prenomMedecin
     */
    public String getPrenomMedecin()
    {
        return prenomMedecin;
    }

    /**
     * @param prenomMedecin the prenomMedecin to set
     */
    public void setPrenomMedecin(String prenomMedecin)
    {
        this.prenomMedecin = prenomMedecin;
    }

    /**
     * @return the serviceConsultation
     */
    public ServiceConsultation getServiceConsultation()
    {
        return serviceConsultation;
    }

    /**
     * @param serviceConsultation the serviceConsultation to set
     */
    public void setServiceConsultation(ServiceConsultation serviceConsultation)
    {
        this.serviceConsultation = serviceConsultation;
    }

    /**
     * @return the donneesSignificatives
     */
    public String getDonneesSignificatives()
    {
        return donneesSignificatives;
    }

    /**
     * @param donneesSignificatives the donneesSignificatives to set
     */
    public void setDonneesSignificatives(String donneesSignificatives)
    {
        this.donneesSignificatives = donneesSignificatives;
    }

    /**
     * @return the conclusions
     */
    public String getConclusions()
    {
        return conclusions;
    }

    /**
     * @param conclusions the conclusions to set
     */
    public void setConclusions(String conclusions)
    {
        this.conclusions = conclusions;
    }

    /**
     * @return the decisions
     */
    public DecisionConsultation getDecisions()
    {
        return decisions;
    }

    /**
     * @param decisions the decisions to set
     */
    public void setDecisions(DecisionConsultation decisions)
    {
        this.decisions = decisions;
    }

    /**
     * @return the commentaires
     */
    public String getCommentaires()
    {
        return commentaires;
    }

    /**
     * @param commentaires the commentaires to set
     */
    public void setCommentaires(String commentaires)
    {
        this.commentaires = commentaires;
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
    public void setDossier(Dossier patient)
    {
        this.dossier = patient;
    }

    /**
     * @return the hospitalisation
     */
    public Hospitalisation getHospitalisation()
    {
        return hospitalisation;
    }

    /**
     * @param hospitalisation the hospitalisation to set
     */
    public void setHospitalisation(Hospitalisation hospitalisation)
    {
        this.hospitalisation = hospitalisation;
    }

}
