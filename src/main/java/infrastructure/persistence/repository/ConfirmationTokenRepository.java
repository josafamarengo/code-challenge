package infrastructure.persistence.repository;

import domain.model.ConfirmationToken;
import infrastructure.persistence.entity.ConfirmationTokenEntity;

public interface ConfirmationTokenRepository {
    public void save(ConfirmationTokenEntity token);
    public ConfirmationTokenEntity findByToken(String token);
    public ConfirmationTokenEntity updateToken(ConfirmationToken token);
    public void delete(ConfirmationToken token);
}
