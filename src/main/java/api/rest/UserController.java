package api.rest;

import api.dto.CreateUserRequestDto;
import api.dto.UserResponseDto;
import api.mapper.UserMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import domain.service.UserService;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService service;

    @Inject
    UserMapper mapper;

    @POST
    public Response createUser(CreateUserRequestDto request) {
        service.createUser(request);
        ResponseBuilder builder = Response.status(Response.Status.CREATED);
        builder.header("Location", "/users/" + request.username());
        return builder.build();
    }

    @GET
    @Path("/{username}")
    public Response getUser(@PathParam("username") String username) {
        UserResponseDto response = mapper.mapToResponse(service.findUserByUsername(username));
        return Response.status(Response.Status.OK).entity(response).build();
    }
}
