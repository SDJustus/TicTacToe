package de.tud.mbo.resources;

import com.codahale.metrics.annotation.Timed;
import de.tud.mbo.core.TicTacToeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/restart")
@Produces(MediaType.APPLICATION_JSON)
public class TicTacToeRestartIntentResource {

    public TicTacToeRestartIntentResource() {
    }

    @GET
    @Timed
    public void sayHello() {
        TicTacToeView.getGameController().resetGame();
    }
}
