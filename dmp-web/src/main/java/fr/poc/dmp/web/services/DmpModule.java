package fr.poc.dmp.web.services;

import java.util.Date;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.Translator;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.Coercion;
import org.apache.tapestry5.ioc.services.CoercionTuple;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.RequestExceptionHandler;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.ResponseRenderer;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;

import fr.poc.dmp.domain.business.ReferentielManager;
import fr.poc.dmp.domain.model.referentiel.AbstractReferentielModel;
import fr.poc.dmp.domain.model.referentiel.DecisionConsultation;
import fr.poc.dmp.domain.model.referentiel.MotifSortie;
import fr.poc.dmp.domain.model.referentiel.ServiceConsultation;
import fr.poc.dmp.domain.model.referentiel.Sexe;
import fr.poc.dmp.web.services.exceptionHandler.DmpRequestExceptionHandler;
import fr.poc.dmp.web.services.exceptionHandler.ExceptionHandlerService;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class DmpModule
{
    public static void bind(ServiceBinder binder)
    {
        binder.bind(ApplicationListener.class).eagerLoad();
        binder.bind(ExceptionHandlerService.class);
        binder.bind(ReferentielSelectModel.class);

        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }

    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration, @InjectService("applicationContext")
    ApplicationContext applicationContext)
    {
        // This code is a bridge to Spring profiles
        boolean productionMode = true;

        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();

        if (profiles != null)
        {
            for (String s : profiles)
            {
                if (s.equals("embedded"))
                {
                    productionMode = false;
                    break;
                }
            }
        }

        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).

        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "fr");

        // The factory default is true but during the early stages of an application
        // overriding to false is a good idea. In addition, this is often overridden
        // on the command line as -Dtapestry.production-mode=false
        configuration.add(SymbolConstants.PRODUCTION_MODE, "" + productionMode);

        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions.
        configuration.add(SymbolConstants.APPLICATION_VERSION, "1.0.0-SNAPSHOT");
    }

    @SuppressWarnings("rawtypes")
    public static void contributeTranslatorSource(MappedConfiguration<Class, Translator> configuration)
    {
        configuration.add(Date.class, new DateTranslator());
    }

    public static void contributeTypeCoercer(Configuration<CoercionTuple> configuration, @InjectService("referentielManager")
    ReferentielManager referentielManager)
    {
        addReferentielCoercion(Sexe.class, configuration, referentielManager);
        addReferentielCoercion(ServiceConsultation.class, configuration, referentielManager);
        addReferentielCoercion(MotifSortie.class, configuration, referentielManager);
        addReferentielCoercion(DecisionConsultation.class, configuration, referentielManager);
    }

    private static <R extends AbstractReferentielModel> void addReferentielCoercion(Class<R> r, Configuration<CoercionTuple> configuration,
            @InjectService("referentielManager")
            ReferentielManager referentielManager)
    {
        Coercion<String, R> coercion = new ReferentielCoercion<R>(referentielManager, r);

        configuration.add(new CoercionTuple<String, R>(String.class, r, coercion));
    }

    public static void contributeValidatorMacro(MappedConfiguration<String, String> configuration)
    {
        configuration.add("referenceDossier", "regexp=^[0-9]{9}$");
    }

    /**
     * Tell Tapestry how to handle classpath URLs - we provide a converter to handle JBoss 5.
     * See http://wiki.apache.org/tapestry/HowToRunTapestry5OnJBoss5 .
     */
    @SuppressWarnings("rawtypes")
    public static void contributeServiceOverride(MappedConfiguration<Class, Object> configuration)
    {
        // Si JBoss
        // configuration.add(ClasspathURLConverter.class, new ClasspathURLConverterJBoss5());
    }

    public RequestExceptionHandler decorateRequestExceptionHandler(final Logger logger, final ResponseRenderer renderer, final ComponentSource componentSource,
            @Symbol(SymbolConstants.PRODUCTION_MODE)
            boolean productionMode, Object service, ExceptionHandlerService exceptionService, RequestGlobals request)
    {
        return new DmpRequestExceptionHandler(exceptionService, componentSource, renderer, request, productionMode);
    }
}
