package fr.poc.dmp.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.poc.dmp.domain.model.referentiel.MotifSortie;

@Entity
@Table(name = "HOSPITALISATION")
public class Hospitalisation implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 662258972128246503L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date dateEntree;

    @Temporal(TemporalType.DATE)
    private Date dateSortie;

    @ManyToOne(optional = true)
    private MotifSortie motifSortie;

    private String diagnosticPrincipal;

    private String diagnosticAssocie;

    private String identifiantHospitalisation;

    // private Integer techindex;

    @ManyToOne(optional = false)
    private Dossier dossier;

    @OneToMany(mappedBy = "hospitalisation", orphanRemoval = false, cascade = CascadeType.ALL)
    private List<Consultation> consultations;

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
     * @return the dateEntree
     */
    public Date getDateEntree()
    {
        return dateEntree;
    }

    /**
     * @param dateEntree the dateEntree to set
     */
    public void setDateEntree(Date dateEntree)
    {
        this.dateEntree = dateEntree;
    }

    /**
     * @return the dateSortie
     */
    public Date getDateSortie()
    {
        return dateSortie;
    }

    /**
     * @param dateSortie the dateSortie to set
     */
    public void setDateSortie(Date dateSortie)
    {
        this.dateSortie = dateSortie;
    }

    /**
     * @return the motifSortie
     */
    public MotifSortie getMotifSortie()
    {
        return motifSortie;
    }

    /**
     * @param motifSortie the motifSortie to set
     */
    public void setMotifSortie(MotifSortie motifSortie)
    {
        this.motifSortie = motifSortie;
    }

    /**
     * @return the diagnosticPrincipal
     */
    public String getDiagnosticPrincipal()
    {
        return diagnosticPrincipal;
    }

    /**
     * @param diagnosticPrincipal the diagnosticPrincipal to set
     */
    public void setDiagnosticPrincipal(String diagnosticPrincipal)
    {
        this.diagnosticPrincipal = diagnosticPrincipal;
    }

    /**
     * @return the diagnosticAssocie
     */
    public String getDiagnosticAssocie()
    {
        return diagnosticAssocie;
    }

    /**
     * @param diagnosticAssocie the diagnosticAssocie to set
     */
    public void setDiagnosticAssocie(String diagnosticAssocie)
    {
        this.diagnosticAssocie = diagnosticAssocie;
    }

    /**
     * @return the identifiantHospitalisation
     */
    public String getIdentifiantHospitalisation()
    {
        return identifiantHospitalisation;
    }

    /**
     * @param identifiantHospitalisation the identifiantHospitalisation to set
     */
    public void setIdentifiantHospitalisation(String identifiantHospitalisation)
    {
        this.identifiantHospitalisation = identifiantHospitalisation;
    }

    /**
     * @return the patient
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

}
