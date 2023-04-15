package ru.hhschool.segment.resource;

import ru.hhschool.segment.service.LayerService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/layers")
public class LayerResource {
    private final LayerService layerService;

    @Inject
    public LayerResource(LayerService layerService) {
        this.layerService = layerService;
    }

    @GET
    @Path(value = "/changes/{layerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLayerChanges(@PathParam(value = "layerId") Long layerId) {
        return Response
                .ok(layerService.getLayerChanges(layerId))
                .build();
    }
}
