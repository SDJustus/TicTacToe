package de.tud.mbo.resources;

import com.codahale.metrics.annotation.Timed;
import de.tud.mbo.core.TicTacToeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/game/slot")
@Produces(MediaType.APPLICATION_JSON)
public class TicTacToeGameIntentResource {

    public TicTacToeGameIntentResource() {
    }

    @GET
    @Timed
    public void sayHello(@QueryParam("value") Optional<String> value) {
        final String urlValue = value.get();
        System.out.println(urlValue);
        String rowSelector = urlValue.substring(0,1);
        int colSelector = Integer.parseInt(urlValue.substring(1,2));
        TicTacToeView.getGameController().makeMove(rowSelector, colSelector);



    }
}
