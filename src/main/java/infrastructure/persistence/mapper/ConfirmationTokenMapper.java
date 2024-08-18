package infrastructure.persistence.mapper;

import domain.model.ConfirmationToken;
import infrastructure.persistence.entity.ConfirmationTokenEntity;

public class ConfirmationTokenMapper {
    public ConfirmationToken toModel(ConfirmationTokenEntity entity) {
        return new ConfirmationToken(
                entity.getToken()
        );
    }
}
