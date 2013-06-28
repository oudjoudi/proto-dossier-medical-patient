package fr.poc.dmp.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "DOSSIER")
@NamedQueries(
{
        @NamedQuery(name = Dossier.QUERY_SEARCH_NAME_DATENAISSANCE, query = "FROM Dossier WHERE patient.nom=:nom AND patient.dateNaissance=:dateNaissance"),
        @NamedQuery(name = Dossier.QUERY_SEARCH_UNICITY, query = "SELECT count(*) FROM Dossier WHERE patient.nom=:nom AND patient.prenom=:prenom AND patient.dateNaissance=:dateNaissance"),
        @NamedQuery(name = Dossier.QUERY_SEARCH_BY_IDENTIFIANT_DOSSIER, query = "FROM Dossier d "
                + "LEFT JOIN FETCH d.antecedents LEFT JOIN FETCH d.consultations LEFT JOIN FETCH d.preventions LEFT JOIN FETCH d.risques LEFT JOIN FETCH d.vaccinsPreventions WHERE d.identifiant = :identifiant "),
        @NamedQuery(name = Dossier.QUERY_SEARCH_BY_REFERENCE_PHYSIQUE, query = "FROM Dossier WHERE referenceDossierPhysique = :reference ") })
public class Dossier implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -6872062666036148725L;

    public static final String QUERY_SEARCH_NAME_DATENAISSANCE = "searchNameDatenaissance";

    public static final String QUERY_SEARCH_UNICITY = "searchUnicity";

    public static final String QUERY_SEARCH_BY_IDENTIFIANT_DOSSIER = "searchByIdentifiantDossier";

    public static final String QUERY_SEARCH_BY_REFERENCE_PHYSIQUE = "searchByReferencePhysique";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String identifiant;

    @Column(nullable = true)
    private String referenceDossierPhysique;

    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;

    @IndexColumn(name = "techindex")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Antecedent> antecedents = new ArrayList<Antecedent>();

    // @IndexColumn(name = "techindex")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @OrderBy("dateEntree DESC")
    private List<Hospitalisation> hospitalisations = new ArrayList<Hospitalisation>();

    // @IndexColumn(name = "techindex")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @OrderBy("dateConsultation DESC")
    private List<Consultation> consultations = new ArrayList<Consultation>();

    @IndexColumn(name = "techindex")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Prevention> preventions = new ArrayList<Prevention>();

    @IndexColumn(name = "techindex")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Risque> risques = new ArrayList<Risque>();

    @IndexColumn(name = "techindex")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<VaccinPrevention> vaccinsPreventions = new ArrayList<VaccinPrevention>();

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
     * @return the patient
     */
    public Patient getPatient()
    {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    /**
     * @return the antecedants
     */
    public List<Antecedent> getAntecedents()
    {
        return antecedents;
    }

    /**
     * @param antecedants the antecedants to set
     */
    public void setAntecedents(List<Antecedent> antecedants)
    {
        this.antecedents = antecedants;
    }

    /**
     * @return the hospitalisations
     */
    public List<Hospitalisation> getHospitalisations()
    {
        return hospitalisations;
    }

    /**
     * @param hospitalisations the hospitalisations to set
     */
    public void setHospitalisations(List<Hospitalisation> hospitalisations)
    {
        this.hospitalisations = hospitalisations;
    }

    /**
     * @return the consultations
     */
    public List<Consultation> getConsultations()
    {
        return consultations;
    }

    /**
     * @param consultations the consultations to set
     */
    public void setConsultations(List<Consultation> consultations)
    {
        this.consultations = consultations;
    }

    /**
     * @return the preventions
     */
    public List<Prevention> getPreventions()
    {
        return preventions;
    }

    /**
     * @param preventions the preventions to set
     */
    public void setPreventions(List<Prevention> preventions)
    {
        this.preventions = preventions;
    }

    /**
     * @return the risques
     */
    public List<Risque> getRisques()
    {
        return risques;
    }

    /**
     * @param risques the risques to set
     */
    public void setRisques(List<Risque> risques)
    {
        this.risques = risques;
    }

    /**
     * @return the vaccinsPreventions
     */
    public List<VaccinPrevention> getVaccinsPreventions()
    {
        return vaccinsPreventions;
    }

    /**
     * @param vaccinsPreventions the vaccinsPreventions to set
     */
    public void setVaccinsPreventions(List<VaccinPrevention> vaccinsPreventions)
    {
        this.vaccinsPreventions = vaccinsPreventions;
    }

}
