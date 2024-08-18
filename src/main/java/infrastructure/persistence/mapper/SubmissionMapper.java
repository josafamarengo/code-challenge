package infrastructure.persistence.mapper;

import domain.model.Submission;
import infrastructure.persistence.entity.SubmissionEntity;

import static common.Parser.fromListToString;

public class SubmissionMapper {
    public SubmissionEntity toEntity(Submission submission) {
        return new SubmissionEntity(
                submission.getChallengeId(),
                submission.getUserId(),
                submission.getCode(),
                fromListToString(submission.getOutput()),
                submission.getCpuUsage(),
                submission.getMemUsage(),
                submission.getStatus(),
                submission.getSentAt(),
                submission.getExecutionTime(),
                submission.getErrorMessage(),
                submission.getAttempts()
        );
    }
}
