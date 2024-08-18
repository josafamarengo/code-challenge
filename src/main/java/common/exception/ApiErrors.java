package common.exception;

import common.exception.model.ErrorMessage;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum ApiErrors {
    USERNAME_FORMAT_ERROR("001", ErrorMessage.INVALID_USERNAME_FORMAT.getMessage(), 422),
    EMAIL_FORMAT_ERROR("002", ErrorMessage.INVALID_EMAIL_FORMAT.getMessage(), 422),
    PASSWORD_FORMAT_ERROR("003", ErrorMessage.INVALID_PASSWORD_FORMAT.getMessage(), 422),
    USERNAME_ALREADY_EXISTS("004", ErrorMessage.USERNAME_ALREADY_EXISTS.getMessage(), 409),
    EMAIL_ALREADY_EXISTS("005", ErrorMessage.EMAIL_ALREADY_EXISTS.getMessage(), 409),
    INVALID_CONFIRMATION_TOKEN("006", ErrorMessage.INVALID_CONFIRMATION_TOKEN.getMessage(), 401),
    INTERNAL_SERVER_ERROR("999", "Internal Server Error", 500);

    private final String code;
    private final String message;
    private final int statusCode;

    public static ApiErrors of(String code) {
        return Arrays.stream(ApiErrors.values())
                .filter(apiError -> apiError.getCode().equals(code))
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);

    }

    ApiErrors(String code, String message, int statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public Map<String, String> toMap() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("code", String.valueOf(this.code));
        errorResponse.put("error", this.message);
        return errorResponse;
    }
}
