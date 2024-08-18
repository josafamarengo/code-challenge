package infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "challenges_data")
public class ChallengeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer challengeId;
    private String inputData;
    private String expectedOutput;
    private Integer order;

    private ChallengeData(){}

    public ChallengeData(Integer id, Integer challengeId, String inputData, String expectedOutput, Integer order) {
        this.id = id;
        this.challengeId = challengeId;
        this.inputData = inputData;
        this.expectedOutput = expectedOutput;
        this.order = order;
    }
}
