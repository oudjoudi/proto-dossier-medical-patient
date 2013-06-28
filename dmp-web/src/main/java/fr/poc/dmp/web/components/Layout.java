package fr.poc.dmp.web.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;

import fr.poc.dmp.domain.security.DmpSecurityContext;

/**
 * Layout component for pages of application dmp-web.
 */
@Import(stylesheet =
{ "context:layout/datepicker/css/datepicker.css", "context:layout/css/dmp.css", "${exanpe.asset-base}/css/exanpe-t5-lib-core.css",
        "${exanpe.asset-base}/css/exanpe-t5-lib-skin.css", "context:layout/css/dmp-manual.css" }, library =
{ "context:layout/js/dmp-lib.js" })
public class Layout
{
    /** The page title, for the <title> element and the <h1>element. */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Inject
    private DmpSecurityContext securityContext;

    @Inject
    private RequestGlobals requestGlobals;

    public boolean isAuthenticated()
    {
        return securityContext.isLoggedIn();
    }

    public String getUsername()
    {
        return securityContext.getUser().getUsername();
    }

    public String getContextRoot()
    {
        return requestGlobals.getHTTPServletRequest().getContextPath();
    }

}
