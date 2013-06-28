package fr.poc.dmp.domain.model.referentiel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractReferentielModel
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String code;

    private String libelle;

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
     * @return the code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * @return the libelle
     */
    public String getLibelle()
    {
        return libelle;
    }

    /**
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle)
    {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof AbstractReferentielModel)) { return false; }

        return id.equals(((AbstractReferentielModel) obj).id);
    }
}
