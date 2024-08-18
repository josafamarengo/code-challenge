package domain.service;

import io.quarkus.logging.Log;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

import common.Const;

public class EmailService {

  private static final String SMTP_HOST = System.getenv("EMAIL_SMTP_HOST");
  private static final int SMTP_PORT = Integer.parseInt(System.getenv("EMAIL_SMTP_PORT"));
  private static final String USERNAME = System.getenv("EMAIL_USERNAME");
  private static final String PASSWORD = System.getenv("EMAIL_PASSWORD");

  public static Session newSessionMail() {
    Properties props = new Properties();
    props.put("mail.smtp.host", SMTP_HOST);
    props.put("mail.smtp.port", SMTP_PORT);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.port", SMTP_PORT);

    return Session.getInstance(props, new jakarta.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(USERNAME, PASSWORD);
      }
    });
  }

  public static void sendEmail(String to, String subject, String body) {
    Session session = newSessionMail();

    try {
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(USERNAME));
      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      msg.setSubject(subject);
      msg.setText(body);

      Transport.send(msg);
      Log.infof("Email enviado para %s com assunto '%s'", to, subject);
    } catch (MessagingException e) {
      Log.error("Erro ao enviar email: " + e.getMessage(), e);
    }
  }

  public void sendConfirmationEmail(String email, String token) {
    String subject = Const.CONFIRMATION_SUBJECT;
    String confirmationUrl = "http://localhost:8080/confirm?token=" + token;
  }

  public void resendConfirmationEmail() {
  }

  public void sendResetPasswordEmail() {
  }
}
