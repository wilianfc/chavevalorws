package mack.chavevalor.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import mack.chavevalor.ws.auth.*;
import mack.chavevalor.ws.resources.ChaveValorResource;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class ChaveValorApplication extends Application<ChaveValorConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ChaveValorApplication().run(args);
    }

    @Override
    public String getName() {
        return "ChaveValor";
    }

    @Override
    public void initialize(final Bootstrap<ChaveValorConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(final ChaveValorConfiguration configuration,
                    final Environment environment) {
        DateFormat professorDateFormat = new SimpleDateFormat(configuration.getDateFormat());
        environment.getObjectMapper().setDateFormat(professorDateFormat);
        ChaveValorResource professorResource = new ChaveValorResource();
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
