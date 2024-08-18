package api.dto;

public record SubmissionRequestDto(
        Integer challengeId,
        Integer userId,
        String code
){}
