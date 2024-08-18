package common.exception.model;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    INVALID_USERNAME_FORMAT("Username must be between 5 and 20 characters long and contain only alphanumeric characters"),
    INVALID_EMAIL_FORMAT("Invalid email format"),
    INVALID_PASSWORD_FORMAT("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character"),
    USERNAME_ALREADY_EXISTS("Username already exists"),
    EMAIL_ALREADY_EXISTS("Email already exists"),
    INVALID_CONFIRMATION_TOKEN("Invalid confirmation token");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
