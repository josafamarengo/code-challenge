package infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Getter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private Integer id;
    private String username;
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    private String avatar;
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    private Integer role;
    private String confirmationToken;
    private Integer status;

    protected UserEntity() {}

    public UserEntity(
            Integer id,
            String username,
            String email,
            String passwordHash,
            String avatar,
            Integer role,
            Integer status
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.avatar = avatar;
        this.role = role;
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public void update(String username, String email, String passwordHash, String avatar, Integer status) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.avatar = avatar;
        this.status = status;
    }
}
