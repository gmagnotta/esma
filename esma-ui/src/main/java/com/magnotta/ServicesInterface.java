package com.magnotta;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.magnotta.model.TransparencyDataReport;

@Path("/esma")
public interface ServicesInterface {
    
    @GET
    @Path("/search/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransparencyDataReport> searchId(@PathParam("id") String id, @DefaultValue("100") @QueryParam("size") int size);
    
}
