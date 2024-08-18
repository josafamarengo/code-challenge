package common;

import java.time.Duration;

public class Const {

  private Const(){}
  // Dificulty levels for challenges
  public static final int DIFFICULTY_EASY = 0;
  public static final int DIFFICULTY_MEDIUM = 1;
  public static final int DIFFICULTY_HARD = 2;

  // Roles for users
  public static final int USER_ROLE = 0;
  public static final int MANAGER_ROLE = 1;
  public static final int ADMIN_ROLE = 2;

  // Status for submissions
  public static final int SUBMISSION_PENDING = 0;
  public static final int SUBMISSION_EXECUTED = 1;
  public static final int SUBMISSION_CORRECT = 2;
  public static final int SUBMISSION_ERRONEOUS = 3;

  // Status for users
  public static final int USER_PENDING = 0;
  public static final int USER_ACTIVE = 1;
  public static final int USER_INACTIVE = 2;

  // Confirmation token
  public static final int TOKEN_EXPIRATION_MINUTES = 30;
  public static final int MAX_RESENDS = 3;
  public static final Duration RESEND_PERIOD = Duration.ofHours(24);

  // Email Subjects
  public static final String CONFIRMATION_SUBJECT = "";
  public static final String RESET_PASSWORD_SUBJECT = "";
}
