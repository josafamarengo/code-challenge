package common.exception.errors;

import common.exception.ApiErrors;
import lombok.Getter;

@Getter
public class InvalidFieldException extends RuntimeException  {
    private final String code;
    private final String errorMessage;
    private final int statusCode;

    public InvalidFieldException(ApiErrors error) {
        this.code = error.getCode();
        this.errorMessage = error.getMessage();
        this.statusCode = error.getStatusCode();
    }
}
