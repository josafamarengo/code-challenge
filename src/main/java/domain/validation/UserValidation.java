package domain.validation;

import api.dto.CreateUserRequestDto;
import common.exception.ApiErrors;
import common.exception.errors.InvalidFieldException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Locale;

@ApplicationScoped
public class UserValidation implements Validation<CreateUserRequestDto> {

    @Override
    public void validate(CreateUserRequestDto request) {
        validateUsername(request.username().toLowerCase(Locale.ROOT));
        validateEmail(request.email().toLowerCase(Locale.ROOT));
        validatePassword(request.password());
    }

    private void validateUsername(String username) {
        if (username.length() < 5
                || username.length() > 20
                || !username.matches("[a-zA-Z0-9]+")) {
            throw new InvalidFieldException(ApiErrors.USERNAME_FORMAT_ERROR);
        }
    }

    private void validateEmail(String email) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new InvalidFieldException(ApiErrors.EMAIL_FORMAT_ERROR);
        }
    }

    private void validatePassword(String password) {
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&/])[A-Za-z\\d@$!%*?&/]{8,}$")) {
            throw new InvalidFieldException(ApiErrors.PASSWORD_FORMAT_ERROR);
        }
    }
}
