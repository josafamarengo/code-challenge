package common.exception.exception_mapper;

import common.exception.ApiErrors;
import common.exception.errors.InvalidFieldException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<InvalidFieldException> {
    @Override
    public Response toResponse(InvalidFieldException exception) {
        ApiErrors error = toApiError(exception);
        return Response.status(error.getStatusCode())
               .entity(error.toMap())
               .build();
    }

    private ApiErrors toApiError(InvalidFieldException exception) {
        return ApiErrors.of(exception.getCode());
    }
}