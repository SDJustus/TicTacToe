package de.tud.mbo;

import de.tud.mbo.core.GameController;
import de.tud.mbo.core.TicTacToeView;
import de.tud.mbo.health.TemplateHealthCheck;
import de.tud.mbo.resources.TicTacToeResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.swing.*;
import java.awt.*;

public class TicTacToeApplication extends Application<TicTacToeConfiguration> {
    private static JPanel restartPanel = new JPanel();
    private static GameController gameController;

    public static void main(final String[] args) throws Exception {
        new TicTacToeApplication().run(args);

        JFrame window = new JFrame("Tic-Tac-Toe for MBO");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToeView(), BorderLayout.CENTER);
        window.getContentPane().add(restartPanel, BorderLayout.EAST);
        window.setBounds(300,200,600,500);
        window.setVisible(true);

        gameController = gameController.getInstance();
        //gameController.initialiseView();
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
