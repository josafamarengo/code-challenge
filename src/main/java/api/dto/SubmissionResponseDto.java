package api.dto;

public record SubmissionResponseDto(
        boolean isCorrect,
        String output
){}
