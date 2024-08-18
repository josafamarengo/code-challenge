package infrastructure.persistence.repository;

import infrastructure.persistence.entity.UserEntity;

public interface UserRepository {
    public void save(UserEntity user);
    public void update(UserEntity user);
    public UserEntity findByUsername(String username);
    public UserEntity findById(int userId);
    public UserEntity findByEmail(String email);
    public Integer findLastId();
    public UserEntity findByConfirmationToken(String confirmationToken);
}
