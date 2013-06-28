/**
 * 
 */
package fr.poc.dmp.web.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.annotations.EagerLoad;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import fr.poc.dmp.common.exception.BusinessException;
import fr.poc.dmp.domain.business.DossierManager;
import fr.poc.dmp.domain.business.ReferentielManager;
import fr.poc.dmp.domain.business.UserManager;
import fr.poc.dmp.domain.model.Dossier;
import fr.poc.dmp.domain.model.Hospitalisation;
import fr.poc.dmp.domain.model.Patient;
import fr.poc.dmp.domain.model.Role;
import fr.poc.dmp.domain.model.User;
import fr.poc.dmp.domain.model.referentiel.DecisionConsultation;
import fr.poc.dmp.domain.model.referentiel.MotifSortie;
import fr.poc.dmp.domain.model.referentiel.ServiceConsultation;
import fr.poc.dmp.domain.model.referentiel.Sexe;
import fr.poc.dmp.domain.model.referentiel.Vaccination;

/**
 * Classe chargée automatiquement au démarrage de l'application.
 * 
 * @author jmaupoux
 */
@EagerLoad
public class ApplicationListener
{
    /**
     * Logger de la classe
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    /**
     * Le formatteur de date / heure
     */
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Constructeur.
     * 
     * @param context Contexte Spring
     * @param productionMode Mode Tapestry (PRODUCTION ou DEVELOPPEMENT)
     * @throws SQLException
     * @throws ParseException
     */
    public ApplicationListener(ApplicationContext context, @Inject
    @Symbol(SymbolConstants.PRODUCTION_MODE)
    boolean productionMode) throws Exception
    {
        if (productionMode)
        {
            LOGGER.info("Demarrage de l'application en mode PRODUCTION.");
            return;
        }

        /**
         * En mode DEVELOPPEMENT, permet d'initialiser un jeu de données par défaut.
         */
        LOGGER.info("Demarrage de l'application en mode DEVELOPPEMENT.");

        // Chargement des roles et users de test
        cleanUpUserAndRole(context);
        loadRole(context);
        try
        {
            loadUser(context);
            loadReferentiel(context);
            loadDossier(context);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(">> Erreur fatale : impossible de charger la liste d'utilisateurs: " + e);
            throw e;
        }
    }

    private void loadReferentiel(ApplicationContext context)
    {
        ReferentielManager referentielManager = (ReferentielManager) context.getBean("referentielManager");

        loadSexes(referentielManager);
        loadVaccinations(referentielManager);
        loadServiceConsultation(referentielManager);
        loadDecisionConsultation(referentielManager);
        loadMotifSortie(referentielManager);
    }

    private void loadMotifSortie(ReferentielManager referentielManager)
    {
        createMotifSortie(referentielManager, "Sortie contre avis médical", "00");
        createMotifSortie(referentielManager, "Guérison", "01");
        createMotifSortie(referentielManager, "Rémission partielle", "02");
        createMotifSortie(referentielManager, "Rémission totale", "03");
        createMotifSortie(referentielManager, "Transféré", "04");
        createMotifSortie(referentielManager, "Evadé", "05");
        createMotifSortie(referentielManager, "Décès", "06");
    }

    private void createMotifSortie(ReferentielManager referentielManager, String string, String code)
    {
        MotifSortie ms = new MotifSortie();
        ms.setLibelle(string);
        ms.setCode(code);
        referentielManager.create(ms);
    }

    private void loadDecisionConsultation(ReferentielManager referentielManager)
    {
        createDecisionConsultation(referentielManager, "Sortie", "00");
        createDecisionConsultation(referentielManager, "Examens complémentaires", "01");
        createDecisionConsultation(referentielManager, "Hospitalisation d'urgence", "02");
        createDecisionConsultation(referentielManager, "Hospitalisation programmée", "03");
        createDecisionConsultation(referentielManager, "Transféré dans un autre établissement", "04");
    }

    private void createDecisionConsultation(ReferentielManager referentielManager, String decision, String code)
    {
        DecisionConsultation dc = new DecisionConsultation();
        dc.setLibelle(decision);
        dc.setCode(code);
        referentielManager.create(dc);
    }

    private void loadServiceConsultation(ReferentielManager referentielManager)
    {
        ServiceConsultation s = new ServiceConsultation();
        s.setLibelle("Urgence");
        s.setCode("00");

        ServiceConsultation s2 = new ServiceConsultation();
        s2.setLibelle("Autre");
        s2.setCode("99");

        referentielManager.create(s);
        referentielManager.create(s2);
    }

    private void loadVaccinations(ReferentielManager referentielManager)
    {
        Vaccination v1 = new Vaccination();
        v1.setLibelle("BCG");
        Vaccination v2 = new Vaccination();
        v2.setLibelle("DTCoqpolio");
        Vaccination v3 = new Vaccination();
        v3.setLibelle("Rougeole");
        Vaccination v4 = new Vaccination();
        v4.setLibelle("ROR RR");
        Vaccination v5 = new Vaccination();
        v5.setLibelle("Méningite");
        Vaccination v6 = new Vaccination();
        v6.setLibelle("Hépatite B");
        Vaccination v7 = new Vaccination();
        v7.setLibelle("Pneumocoque");
        Vaccination v8 = new Vaccination();
        v8.setLibelle("Rétrovirus");

        referentielManager.create(v1);
        referentielManager.create(v2);
        referentielManager.create(v3);
        referentielManager.create(v4);
        referentielManager.create(v5);
        referentielManager.create(v6);
        referentielManager.create(v7);
        referentielManager.create(v8);
    }

    private void loadSexes(ReferentielManager referentielManager)
    {
        Sexe sexe = new Sexe();
        sexe.setCode("M");
        sexe.setLibelle("Masculin");

        Sexe sexe2 = new Sexe();
        sexe2.setCode("F");
        sexe2.setLibelle("Féminin");

        referentielManager.create(sexe);
        referentielManager.create(sexe2);
    }

    private void loadDossier(ApplicationContext context) throws BusinessException, ParseException
    {

        DossierManager dossierManager = (DossierManager) context.getBean("dossierManager");
        ReferentielManager referentielManager = (ReferentielManager) context.getBean(ReferentielManager.class);

        Patient p = new Patient();
        p.setNom("DJABIRALY");
        p.setPrenom("Sarah");

        Calendar c = Calendar.getInstance();
        c.set(2012, Calendar.JANUARY, 1);

        p.setDateNaissance(c.getTime());
        p.setAdresse("27 rue du petit bois 75001 Paris");
        p.setSexe(referentielManager.findByCode(Sexe.class, "F"));

        Dossier d = dossierManager.createDossier(p);

        Hospitalisation h = new Hospitalisation();
        h.setDateEntree(Calendar.getInstance().getTime());
        h.setDiagnosticPrincipal("Grippe H1N1");
        h.setIdentifiantHospitalisation("1234567");
        h.setDossier(d);

        d.getHospitalisations().add(h);

        Hospitalisation h2 = new Hospitalisation();
        h2.setDateEntree(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2013"));
        h2.setDateSortie(new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2011"));
        h2.setDiagnosticPrincipal("Fracture");
        h2.setIdentifiantHospitalisation("1234580");
        h2.setDossier(d);

        d.getHospitalisations().add(h2);

        dossierManager.update(d);

        d = dossierManager.findByIdentifiant(d.getIdentifiant());
    }

    private void cleanUpUserAndRole(ApplicationContext context) throws SQLException
    {
        LOGGER.info(">>> Nettoyage USER / ROLE / AUTHORITY...");

        Connection c = pullConnection(context);
        Statement statement = null;
        try
        {
            statement = c.createStatement();
            statement.addBatch("DELETE FROM J_USER_ROLE WHERE id_user >= 0");
            statement.addBatch("DELETE FROM UTILISATEUR WHERE id >= 0");
            statement.addBatch("DELETE FROM J_ROLE_AUTHORITY WHERE id_role >= 0");
            statement.addBatch("DELETE FROM ROLE WHERE id >= 0");
            statement.addBatch("DELETE FROM AUTHORITY WHERE id >= 0");
            statement.executeBatch();
            // c.commit();
        }
        finally
        {
            statement.close();
            c.close();
        }
        LOGGER.info("<<< Nettoyage : traitement termine");
    }

    private void loadRole(ApplicationContext context) throws SQLException
    {
        LOGGER.info(">>> Roles : Chargement du jeu de donnees par defaut...");

        Connection c = pullConnection(context);
        Statement statement = null;
        try
        {
            statement = c.createStatement();
            statement.addBatch("INSERT INTO ROLE (id, name) VALUES (0, 'ADMIN')");
            statement.addBatch("INSERT INTO ROLE (id, name) VALUES (1, 'USER')");

            statement.addBatch("INSERT INTO AUTHORITY (id, authority) VALUES (0, 'ROLE_ADMIN')");
            statement.addBatch("INSERT INTO J_ROLE_AUTHORITY (id_role, id_authority) VALUES (0, 0)");

            statement.addBatch("INSERT INTO AUTHORITY (id, authority) VALUES (1, 'ROLE_USER')");
            statement.addBatch("INSERT INTO J_ROLE_AUTHORITY (id_role, id_authority) VALUES (1, 1)");
            statement.executeBatch();
            // c.commit();
        }
        finally
        {
            statement.close();
            c.close();
        }

        LOGGER.info("<<< Roles : Chargement termine.");
    }

    private void loadUser(ApplicationContext context) throws SQLException, BusinessException
    {
        LOGGER.info(">>> Users : Chargement du jeu de donnees par defaut...");

        UserManager userManager = (UserManager) context.getBean("userManager");
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        Role role = new Role();
        role.setName("ADMIN");
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        userManager.createUser(user, roles);

        LOGGER.info("<<< Users : Chargement termine.");

        // TODO delete, cache test purpose
        for (int i = 0; i < 10; i++)
        {
            userManager.findByUsername("admin");
        }

    }

    private Connection pullConnection(ApplicationContext context) throws SQLException
    {
        return pullDataSource(context).getConnection();
    }

    private DataSource pullDataSource(ApplicationContext context)
    {
        return (DataSource) context.getBean("dataSource");
    }
}
