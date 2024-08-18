package domain.service;

import api.dto.CreateUserRequestDto;
import common.Const;
import common.auth.Crypto;
import common.exception.ApiErrors;
import common.exception.errors.InvalidFieldException;
import domain.model.ConfirmationToken;
import domain.model.User;
import infrastructure.persistence.entity.UserEntity;
import infrastructure.persistence.repository.UserRepository;
import infrastructure.persistence.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import domain.validation.Validation;

import java.util.Locale;

@ApplicationScoped
public class UserService {

    @Inject
    Validation<CreateUserRequestDto> validation;

    @Inject
    UserRepository repository;

    @Inject
    UserMapper mapper;

    public void createUser(CreateUserRequestDto request) {
        validation.validate(request);

        String username = request.username().toLowerCase(Locale.ROOT);
        String email = request.email().toLowerCase(Locale.ROOT);
        String passwordHash = Crypto.hash(request.password());
        String avatar = request.avatar();

        if (repository.findByUsername(username) != null) {
            throw new InvalidFieldException(ApiErrors.USERNAME_ALREADY_EXISTS);
        }

        if (repository.findByEmail(email)!= null) {
            throw new InvalidFieldException(ApiErrors.EMAIL_ALREADY_EXISTS);
        }

        if (avatar == null || avatar.isEmpty()) {
            avatar = "https://robohash.org/" + username + "?set=set3&bgset=bg2";
        }

        ConfirmationToken token = new ConfirmationToken(username);

        Integer id = repository.findLastId() + 1;
        User user = new User(id, username, email, passwordHash, avatar);
        repository.save(mapper.toEntity(user));
    }

    public User findUserByUsername(String username) {
        return mapper.toModel(repository.findByUsername(username));
    }

    public void confirmUserRegistration(String token) {
        UserEntity entity = repository.findByConfirmationToken(token);
        if ( null == entity|| entity.getStatus() != Const.USER_PENDING) {
            throw new InvalidFieldException(ApiErrors.INVALID_CONFIRMATION_TOKEN);
        }

        User user = mapper.toModel(entity);
        user.updateStatus(Const.USER_ACTIVE);

        repository.update(mapper.toEntity(user));
    }
}
