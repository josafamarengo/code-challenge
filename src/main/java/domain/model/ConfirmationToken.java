package domain.model;

import common.Const;
import common.auth.Crypto;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ConfirmationToken {
    private String token;
    private Instant expirationTime;
    private int resendCount;
    private Instant lastResendTime;

    public ConfirmationToken(String username) {
        this.generateToken(username);
        this.resendCount = 0;
        this.lastResendTime = Instant.MIN;
    }

    private void generateToken(String username) {
        this.token = Crypto.hash(username + System.currentTimeMillis());
        this.expirationTime = Instant.now().plusSeconds(60 * Const.TOKEN_EXPIRATION_MINUTES);
    }

    public void updateToken() {
        generateToken(this.getToken());
        this.incrementResendCount();
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expirationTime);
    }

    public boolean isResendable() {
        return resendCount < Const.MAX_RESENDS && Instant.now().isAfter(lastResendTime.plusSeconds(Const.RESEND_PERIOD.toSeconds()));
    }

    public void incrementResendCount() {
        resendCount++;
        lastResendTime = Instant.now();
    }

    public void resetResendCount() {
        resendCount = 0;
        lastResendTime = Instant.MIN;
    }

    public String toString() {
        return token;
    }
}
