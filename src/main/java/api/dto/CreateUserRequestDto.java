package api.dto;

public record CreateUserRequestDto (
        String username,
        String email,
        String password,
        String avatar
){}
