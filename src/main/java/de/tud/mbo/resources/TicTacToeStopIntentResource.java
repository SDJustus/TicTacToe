package de.tud.mbo.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/stop")
@Produces(MediaType.APPLICATION_JSON)

public class TicTacToeStopIntentResource {

    public TicTacToeStopIntentResource() {
    }

    @GET
    @Timed
    public void sayHello() {
        // call the game stop method

    }
}
