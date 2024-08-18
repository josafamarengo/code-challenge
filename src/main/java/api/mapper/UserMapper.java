package api.mapper;

import api.dto.UserResponseDto;
import domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {
    public UserResponseDto mapToResponse(User user) {
        return new UserResponseDto(
                user.getUsername(),
                user.getEmail(),
                user.getAvatar()
        );
    }
}
