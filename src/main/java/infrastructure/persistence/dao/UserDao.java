package infrastructure.persistence.dao;

import infrastructure.persistence.entity.UserEntity;
import infrastructure.persistence.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.NoSuchElementException;

@ApplicationScoped
public class UserDao implements UserRepository {

    @Inject
    EntityManager em;

    @Transactional
    @Override
    public void save(UserEntity user) {
        em.persist(user);
        em.flush();
    }

    @Override
    @Transactional
    public void update(UserEntity user) {
        em.merge(user);
        em.flush();
    }

    @Override
    public UserEntity findByUsername(String username) {
        TypedQuery<UserEntity> query = em.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class);

        query.setParameter("username", username);

        try {
            return query.getResultList().getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public UserEntity findById(int userId) {
        TypedQuery<UserEntity> query = em.createQuery("SELECT u FROM UserEntity u WHERE u.id = :id", UserEntity.class);

        query.setParameter("id", userId);

        try {
            return query.getSingleResult();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public UserEntity findByEmail(String email) {
        TypedQuery<UserEntity> query = em.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class);

        query.setParameter("email", email);

        try {
            return query.getResultList().getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Integer findLastId() {
        TypedQuery<Integer> query = em.createQuery("SELECT MAX(u.id) FROM UserEntity u", Integer.class);
        return null != query.getSingleResult() ? query.getSingleResult() : 0;
    }

    @Override
    public UserEntity findByConfirmationToken(String confirmationToken) {
        TypedQuery<UserEntity> query = em.createQuery("SELECT u FROM UserEntity u WHERE u.confirmationToken = :confirmationToken", UserEntity.class);

        query.setParameter("confirmationToken", confirmationToken);

        try {
            return query.getResultList().getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
