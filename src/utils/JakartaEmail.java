package utils;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class JakartaEmail {
        static String to,from;
    public JakartaEmail(String to, String from) {
        this.to = to;
        this.from = from;
    }
    public void sendMail(){
            final String username = "a37281e1ab3da4";      final String password = "683b3688e13c69";
      String host = "sandbox.smtp.mailtrap.io";
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");
      //create the Session object
      Session session = Session.getInstance(props,
        new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
    }
        });
      try {
    //create a MimeMessage object
    Message message = new MimeMessage(session);
    //set From email field
    message.setFrom(new InternetAddress(from));
    //set To email field
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
    //set email subject field
    message.setSubject("Here comes my SMTP email from Java!");
    //set the content of the email message
    message.setText("Dear User Welome to our Application");
    //send the email message
    Transport.send(message);
    System.out.println("Email Message Sent Successfully");
      } catch (MessagingException e) {
        throw new RuntimeException(e);
      }
  }
    }

  