package fr.poc.dmp.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.poc.dmp.domain.model.referentiel.Sexe;

@Entity
@Table(name = "PATIENT")
public class Patient implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1180851137809606476L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = true)
    private String identifiant;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = true)
    private String nomUsage;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = true)
    private Boolean homonyme = Boolean.FALSE;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Column(nullable = false)
    private String adresse;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Sexe sexe;

    @Column(nullable = true)
    private String profession;

    @Column(nullable = true)
    private String numeroSS;

    @Column(nullable = true)
    private Boolean indicateurALD = Boolean.FALSE;

    @Column(nullable = true)
    private String nomTuteur;

    @Column(nullable = true)
    private String nomMutuelle;

    @Column(nullable = true)
    private String numeroMutuelle;

    @Column(nullable = true)
    private String referenceDossierPhysique;

    @Column(nullable = true)
    private String telephoneFixe;

    @Column(nullable = true)
    private String telephoneMobile;

    @OneToOne(optional = true)
    private Dossier dossier;

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
     * @return the identifiant
     */
    public String getIdentifiant()
    {
        return identifiant;
    }

    /**
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(String identifiant)
    {
        this.identifiant = identifiant;
    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    /**
     * @return the nomUsage
     */
    public String getNomUsage()
    {
        return nomUsage;
    }

    /**
     * @param nomUsage the nomUsage to set
     */
    public void setNomUsage(String nomUsage)
    {
        this.nomUsage = nomUsage;
    }

    /**
     * @return the prenom
     */
    public String getPrenom()
    {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    /**
     * @return the homonyme
     */
    public boolean isHomonyme()
    {
        return homonyme;
    }

    /**
     * @param homonyme the homonyme to set
     */
    public void setHomonyme(boolean homonyme)
    {
        this.homonyme = homonyme;
    }

    /**
     * @return the dateNaissance
     */
    public Date getDateNaissance()
    {
        return dateNaissance;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date dateNaissance)
    {
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return the adresse
     */
    public String getAdresse()
    {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    /**
     * @return the sexe
     */
    public Sexe getSexe()
    {
        return sexe;
    }

    /**
     * @param sexe the sexe to set
     */
    public void setSexe(Sexe sexe)
    {
        this.sexe = sexe;
    }

    /**
     * @return the profession
     */
    public String getProfession()
    {
        return profession;
    }

    /**
     * @param profession the profession to set
     */
    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    /**
     * @return the numeroSS
     */
    public String getNumeroSS()
    {
        return numeroSS;
    }

    /**
     * @param numeroSS the numeroSS to set
     */
    public void setNumeroSS(String numeroSS)
    {
        this.numeroSS = numeroSS;
    }

    /**
     * @return the indicateurALD
     */
    public boolean isIndicateurALD()
    {
        return indicateurALD;
    }

    /**
     * @param indicateurALD the indicateurALD to set
     */
    public void setIndicateurALD(boolean indicateurALD)
    {
        this.indicateurALD = indicateurALD;
    }

    /**
     * @return the nomTuteur
     */
    public String getNomTuteur()
    {
        return nomTuteur;
    }

    /**
     * @param nomTuteur the nomTuteur to set
     */
    public void setNomTuteur(String nomTuteur)
    {
        this.nomTuteur = nomTuteur;
    }

    /**
     * @return the nomMutuelle
     */
    public String getNomMutuelle()
    {
        return nomMutuelle;
    }

    /**
     * @param nomMutuelle the nomMutuelle to set
     */
    public void setNomMutuelle(String nomMutuelle)
    {
        this.nomMutuelle = nomMutuelle;
    }

    /**
     * @return the numeroMutuelle
     */
    public String getNumeroMutuelle()
    {
        return numeroMutuelle;
    }

    /**
     * @param numeroMutuelle the numeroMutuelle to set
     */
    public void setNumeroMutuelle(String numeroMutuelle)
    {
        this.numeroMutuelle = numeroMutuelle;
    }

    /**
     * @return the referenceDossierPhysique
     */
    public String getReferenceDossierPhysique()
    {
        return referenceDossierPhysique;
    }

    /**
     * @param referenceDossierPhysique the referenceDossierPhysique to set
     */
    public void setReferenceDossierPhysique(String referenceDossierPhysique)
    {
        this.referenceDossierPhysique = referenceDossierPhysique;
    }

    /**
     * @return the telephoneFixe
     */
    public String getTelephoneFixe()
    {
        return telephoneFixe;
    }

    /**
     * @param telephoneFixe the telephoneFixe to set
     */
    public void setTelephoneFixe(String telephoneFixe)
    {
        this.telephoneFixe = telephoneFixe;
    }

    /**
     * @return the telephonePortable
     */
    public String getTelephoneMobile()
    {
        return telephoneMobile;
    }

    /**
     * @param telephonePortable the telephonePortable to set
     */
    public void setTelephoneMobile(String telephonePortable)
    {
        this.telephoneMobile = telephonePortable;
    }

    /**
     * @return the dossier
     */
    public Dossier getDossier()
    {
        return dossier;
    }

    /**
     * @param dossier the dossier to set
     */
    public void setDossier(Dossier dossier)
    {
        this.dossier = dossier;
    }

}
