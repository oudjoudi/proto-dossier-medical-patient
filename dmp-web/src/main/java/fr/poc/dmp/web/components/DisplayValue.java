package fr.poc.dmp.web.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class DisplayValue
{
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    @Property
    private String label;

    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    @Property
    private boolean last;
}
