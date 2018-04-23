package mack.lp3.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import mack.lp3.ws.resources.ProfessorResource;

import mack.lp3.ws.auth.*;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class ProfessoresApplication extends Application<ProfessoresConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ProfessoresApplication().run(args);
    }

    @Override
    public String getName() {
        return "Professores";
    }

    @Override
    public void initialize(final Bootstrap<ProfessoresConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(final ProfessoresConfiguration configuration,
                    final Environment environment) {
        DateFormat professorDateFormat = new SimpleDateFormat(configuration.getDateFormat());
        environment.getObjectMapper().setDateFormat(professorDateFormat);
        ProfessorResource professorResource = new ProfessorResource();
        environment.jersey().register(professorResource);

        environment.jersey().setUrlPattern("/api/*");
        
        /*
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                                .setAuthenticator(new AppBasicAuthenticator())
                                .setAuthorizer(new AppAuthorizer())
                                .setRealm("BASIC-AUTH-REALM")
                                .buildAuthFilter()));
        */
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

}
