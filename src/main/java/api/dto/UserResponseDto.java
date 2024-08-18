package api.dto;

public record UserResponseDto (
    String username,
    String email,
    String avatar
) {}
