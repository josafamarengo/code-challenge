package infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Getter
@Entity
@Table(name = "submissions")
public class SubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer challengeId;
    private Integer userId;
    private String code;
    private String output;
    private Double cpuUsage;
    private Double memUsage;
    private Integer status;
    private Instant sentAt;
    private Double executionTime;
    private String errorMessage;
    private Integer attempts;

    private SubmissionEntity() {}

    public SubmissionEntity(
            Integer challengeId,
            Integer userId,
            String code,
            String output,
            Double cpuUsage,
            Double memUsage,
            Integer status,
            Instant sentAt,
            Double executionTime,
            String errorMessage,
            Integer attempts) {
        this.challengeId = challengeId;
        this.userId = userId;
        this.code = code;
        this.output = output;
        this.cpuUsage = cpuUsage;
        this.memUsage = memUsage;
        this.status = status;
        this.sentAt = sentAt;
        this.executionTime = executionTime;
        this.errorMessage = errorMessage;
        this.attempts = attempts;
    }
}
