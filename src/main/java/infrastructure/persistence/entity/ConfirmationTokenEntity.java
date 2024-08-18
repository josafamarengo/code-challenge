package infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Getter
@Entity
@Table(name = "confirm_tokens")
public class ConfirmationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private Instant expirationTime;
    private int resendCount;
    private Instant lastResendTime;
    private int userId;

    public ConfirmationTokenEntity() {}

    public ConfirmationTokenEntity(String token, Instant expirationTime, int resendCount, Instant lastResendTime, Integer userId) {
        this.token = token;
        this.expirationTime = expirationTime;
        this.resendCount = resendCount;
        this.lastResendTime = lastResendTime;
        this.userId = userId;
    }
}
