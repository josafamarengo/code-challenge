package domain.model;

import common.Const;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class Submission {
    private final Integer challengeId;
    private final Integer userId;
    private final String code;
    private final List<String> output;
    @Setter
    private Double cpuUsage;
    @Setter
    private Double memUsage;
    private Integer status;
    private final Instant sentAt = Instant.now();
    @Setter
    private Double executionTime;
    private String errorMessage;
    private Integer attempts;

    public void incrementAttempts() {
        attempts++;
    }

    public void setCorrectOutput(List<String> expectedOutput) {
        if (output.equals(expectedOutput)) {
            status = Const.SUBMISSION_CORRECT;
        } else {
            status = Const.SUBMISSION_ERRONEOUS;
        }
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        status = Const.SUBMISSION_ERRONEOUS;
    }

    public boolean isCorrect() {
        return status == Const.SUBMISSION_CORRECT;
    }
}
