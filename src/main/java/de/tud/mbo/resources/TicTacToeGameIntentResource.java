package de.tud.mbo.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class TicTacToeGameIntentResource {

    public TicTacToeGameIntentResource() {
    }

    @GET
    @Timed
    public void sayHello(@QueryParam("value") Optional<String> value) {
        final String urlValue = value.get();

    }
}
