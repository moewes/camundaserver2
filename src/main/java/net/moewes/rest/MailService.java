package net.moewes.rest;

import com.sun.mail.smtp.SMTPTransport;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Startup
@Singleton
public class MailService {

  @PostConstruct
  public void sendMail() {

    Properties prop = System.getProperties();
    prop.put("mail.smtp.host", "localhost"); //optional, defined in SMTPTransport
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.port", "25"); // default port 25

    Session session = Session.getInstance(prop, null);
    Message msg = new MimeMessage(session);

    try {

      // from
      msg.setFrom(new InternetAddress("maik@local.com"));

      // to
      msg.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse("admin@local.com", false));

      // cc
      msg.setRecipients(Message.RecipientType.CC,
          InternetAddress.parse("dozent@local.com", false));

      // subject
      msg.setSubject("Test");

      // content
      // msg.setText("Der Adler ist gelandet");
      msg.setDataHandler(new DataHandler(
          new HTMLDataSource("<H1>Wichtig!!!</h1><p>Der <i>Adler</i> ist gelandet</p>")));

      msg.setSentDate(new Date());

      // Get SMTPTransport
      SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

      // connect
      t.connect("localhost", "", "");

      // send
      t.sendMessage(msg, msg.getAllRecipients());

      System.out.println("Response: " + t.getLastServerResponse());

      t.close();

    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  static class HTMLDataSource implements DataSource {

    private String html;

    public HTMLDataSource(String htmlString) {
      html = htmlString;
    }

    @Override
    public InputStream getInputStream() throws IOException {
      if (html == null) {
        throw new IOException("html message is null!");
      }
      return new ByteArrayInputStream(html.getBytes());
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
      throw new IOException("This DataHandler cannot write HTML");
    }

    @Override
    public String getContentType() {
      return "text/html";
    }

    @Override
    public String getName() {
      return "HTMLDataSource";
    }
  }
}
