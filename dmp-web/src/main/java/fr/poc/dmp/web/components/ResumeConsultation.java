package fr.poc.dmp.web.components;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import fr.poc.dmp.domain.model.Consultation;

public class ResumeConsultation
{
    @Parameter(required = true, allowNull = false)
    @Property
    private Consultation consultation;

    public String getDateToString()
    {
        return FastDateFormat.getInstance("dd/MM/yyyy").format(consultation.getDateConsultation());
    }
}
