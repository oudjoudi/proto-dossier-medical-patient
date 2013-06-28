package fr.poc.dmp.web.components;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class FormatDate
{
    @Parameter(allowNull = true)
    @Property
    private Date date;

    void beginRender(MarkupWriter writer)
    {
        if (date != null)
        {
            writer.write(FastDateFormat.getInstance("dd/MM/yyyy").format(date));
        }
    }
}
