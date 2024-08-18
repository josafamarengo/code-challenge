package infrastructure.persistence.mapper;

import domain.model.User;
import infrastructure.persistence.entity.ConfirmationTokenEntity;
import infrastructure.persistence.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {
    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getAvatar(),
                user.getRole(),
                user.getStatus()
        );
    }

    public User toModel(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getAvatar(),
                entity.getStatus()
        );
    }
}
