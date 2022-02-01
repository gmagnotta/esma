package org.acme;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.acme.model.TransparencyDataReport;
import org.acme.processor.ESMAService;

@Path("/esma")
public class ESMAResource {

    @Inject
    ESMAService esmaProcessor;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransparencyDataReport> getById(@PathParam("id") String id) {
        return esmaProcessor.getById(id);
    }

    @GET
    @Path("/random/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransparencyDataReport> getRandom(@PathParam("amount") int size) {
        return esmaProcessor.getSome(size);
    }

    @GET
    @Path("/search/{id}")

    @Produces(MediaType.APPLICATION_JSON)
    public List<TransparencyDataReport> searchId(@PathParam("id") String id, @DefaultValue("100") @QueryParam("size") int size) {
        return esmaProcessor.searchId(id, size);
    }
}