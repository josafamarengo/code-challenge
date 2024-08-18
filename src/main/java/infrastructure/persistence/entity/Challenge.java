package infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "challenges")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Integer difficulty;
    private String examples;
    private Integer tagId;

    private Challenge() {}

    public Challenge(Integer id, String title, String description, Integer difficulty, String examples) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.examples = examples;
    }
}
