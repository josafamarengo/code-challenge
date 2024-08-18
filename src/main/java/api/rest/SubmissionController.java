package api.rest;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import api.dto.SubmissionRequestDto;
import api.dto.SubmissionResponseDto;
import org.jboss.logging.Logger;
import domain.service.SubmissionService;

@Path("/submissions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubmissionController {

    @Inject
    SubmissionService submissionService;

    private static final Logger LOGGER = Logger.getLogger(SubmissionController.class);

    @POST
    public Response submitCode(SubmissionRequestDto request) {
        submissionService = SubmissionService.createSubmissionService();
        try {
            LOGGER.info("Received request for challengeId: " + request.challengeId());
            SubmissionResponseDto result = submissionService.processSubmission(request);
            LOGGER.info("Submission processed successfully.");
            return Response.ok(result).build();
        } catch (Exception e) {
            LOGGER.error("Error processing submission: " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
