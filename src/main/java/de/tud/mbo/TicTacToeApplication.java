package de.tud.mbo;

import de.tud.mbo.health.TemplateHealthCheck;
import de.tud.mbo.resources.TicTacToeResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TicTacToeApplication extends Application<TicTacToeConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TicTacToeApplication().run(args);
    }

    @Override
    public String getName() {
        return "TicTacToe";
    }

    @Override
    public void initialize(final Bootstrap<TicTacToeConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final TicTacToeConfiguration configuration,
                    final Environment environment) {
        final TicTacToeResource resource = new TicTacToeResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
