package de.tud.mbo;

import de.tud.mbo.core.GameController;
import de.tud.mbo.core.TicTacToeView;
import de.tud.mbo.health.TemplateHealthCheck;
import de.tud.mbo.resources.TicTacToeGameIntentResource;
import de.tud.mbo.resources.TicTacToeRestartIntentResource;
import de.tud.mbo.resources.TicTacToeStopIntentResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.swing.*;
import java.awt.*;

public class TicTacToeApplication extends Application<TicTacToeConfiguration> {
    private static JPanel restartPanel = new JPanel();


    public static void main(final String[] args) throws Exception {
        new TicTacToeApplication().run(args);
        JFrame window = new JFrame("Tic-Tac-Toe for MBO");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToeView(), BorderLayout.CENTER);
        //window.getContentPane().add(restartPanel, BorderLayout.EAST);
        window.setBounds(300,200,600,500);
        window.setVisible(true);

    }

    @Override
    public String getName() {
        return "TicTacToeView";
    }

    @Override
    public void initialize(final Bootstrap<TicTacToeConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final TicTacToeConfiguration configuration,
                    final Environment environment) {
        final TicTacToeGameIntentResource gameResource = new TicTacToeGameIntentResource();
        final TicTacToeStopIntentResource stopResource = new TicTacToeStopIntentResource();
        final TicTacToeRestartIntentResource restartResource = new TicTacToeRestartIntentResource();

        // final TemplateHealthCheck healthCheck = new TemplateHealthCheck();
        //environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(gameResource);
        environment.jersey().register(stopResource);
        environment.jersey().register(restartResource);


    }

}
