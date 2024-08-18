package infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tagName;

    private Tag() {}

    public Tag(Long id, String tag) {
        this.id = id;
        this.tagName = tag;
    }
}
