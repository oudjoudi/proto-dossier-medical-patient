package fr.poc.dmp.web.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import fr.poc.dmp.domain.model.Dossier;

public class ConsultationLayout
{
    @Property
    @Parameter(allowNull = false, required = true)
    private Dossier dossier;
}
