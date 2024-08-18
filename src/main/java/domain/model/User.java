package domain.model;

import lombok.Getter;

@Getter
public class User {
    private final Integer id;
    private String username;
    private String email;
    private String passwordHash;
    private String avatar;
    private Integer role;
    private Integer status;

    public User(Integer id, String username, String email, String passwordHash, String avatar) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.avatar = avatar;
        this.role = 0;
        this.status = 0;
    }

    public User(Integer id, String username, String email, String passwordHash, String avatar, Integer status) {
        this(id, username, email, passwordHash, avatar);
        this.status = status;
    }

    public void updateUsername(String newUsername) {
        this.username = newUsername;
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }

    public void updatePasswordHash(String newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }

    public void updateAvatar(String newAvatar) {
        this.avatar = newAvatar;
    }

    public void updateStatus(Integer newStatus) {this.status = newStatus;  }
}
