package fr.poc.dmp.domain.model.referentiel;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "R_MOTIF_SORTIE")
@Cacheable(value = true)
@Cache(region = "dmpCacheRef", usage = CacheConcurrencyStrategy.READ_ONLY)
public class MotifSortie extends AbstractReferentielModel
{

    /**
     * 
     */
    private static final long serialVersionUID = 4781988395184474206L;

}
