package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMail {
   public static void sendMail(String recipient) throws MessagingException {
       Properties properties = new Properties();
       System.out.println("1");
       properties.setProperty("mail.transport.protocol", "smtp");
       properties.setProperty("mail.host", "poczta.interia.pl");
       properties.put("mail.smtp.auth", "true");
       properties.put("mail.smtp.starttls.enable", "true");
       properties.put("mail.smtp.starttls", "true");
       properties.put("mail.smtp.host", "poczta.interia.pl");
       properties.put("mail.smtp.port", "587");
       properties.put("mail.smtp.ssl.trust", "*");



       String myAccountEmail = "javabryk@interia.pl";
       String password = "Ad123456";

       Session session = Session.getInstance(properties,
               new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(myAccountEmail, password);
                   }
               });

       Message message = prepareMessage(session, myAccountEmail, recipient);
        System.out.println("3");
       Transport.send(message);

       System.out.println("wiadomosc wyslana");
   }
   private static Message prepareMessage(Session session, String myAccountEmail, String recepient)  {
       Message message = new MimeMessage(session);
       try {
           message.setFrom(new InternetAddress(myAccountEmail));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
           message.setSubject(" Temat");
           message.setText("Wiadomosc");
           System.out.println("2");
           return message;
       } catch (MessagingException e) {
           throw new RuntimeException(e);
       }

   }
}
