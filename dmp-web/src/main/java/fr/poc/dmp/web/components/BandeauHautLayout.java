package fr.poc.dmp.web.components;

import org.apache.tapestry5.annotations.Parameter;

public class BandeauHautLayout
{
    @Parameter
    private boolean consult;

    @Parameter
    private boolean creation;

    public String getConsultClass()
    {
        if (consult) { return "active"; }
        return "";
    }

    public String getCreationClass()
    {
        if (creation) { return "active"; }
        return "";
    }
}
