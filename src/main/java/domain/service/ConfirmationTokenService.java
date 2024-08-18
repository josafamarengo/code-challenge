package domain.service;

import domain.model.ConfirmationToken;
import domain.model.User;
import infrastructure.persistence.entity.ConfirmationTokenEntity;
import infrastructure.persistence.mapper.ConfirmationTokenMapper;
import infrastructure.persistence.repository.ConfirmationTokenRepository;

public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository tokenRepository) {
        this.confirmationTokenRepository = tokenRepository;
    }

    public void createTokenForUser(User user) {
        ConfirmationToken token = new ConfirmationToken(user.getUsername());
        ConfirmationTokenEntity entity = new ConfirmationTokenEntity(
                token.getToken(),
                token.getExpirationTime(),
                token.getResendCount(),
                token.getLastResendTime(),
                user.getId()
        );
        confirmationTokenRepository.save(entity);
    }

    public ConfirmationTokenEntity findTokenByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void updateToken(ConfirmationToken token) {
        token.updateToken();
        confirmationTokenRepository.updateToken(token);
    }

    public void deleteToken(ConfirmationToken token) {
        confirmationTokenRepository.delete(token);
    }
}
