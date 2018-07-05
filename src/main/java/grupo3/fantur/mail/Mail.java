package grupo3.fantur.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//@Stateless
public class Mail {

	// @Resource(mappedName = "java:jboss/mail/gmail")
	// private Session session;
	//
	// public void sendMsg(String addresses, String topic, String textMessage) {
	//
	// try {
	// Message message = new MimeMessage(session);
	// message.setRecipients(Message.RecipientType.TO,
	// InternetAddress.parse(addresses));
	// message.setSubject(topic);
	// message.setText(textMessage);
	//
	// Transport.send(message);
	//
	// } catch (MessagingException e) {
	// Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail",
	// e);
	// }
	// }

	@SuppressWarnings("restriction")
	public void sendMsg(String direcciones, String asunto, String mensaje) {

		try {
			String host = "smtp.gmail.com";
			String user = "fanturgrupo3@gmail.com"; // Enter Your Gmail Username
			String pass = "dacsgrupo3"; // Enter Your Gmail Password
			String to = direcciones;
			String from = "fanturgrupo3@gmail.com";
			String subject = asunto;
			String messageText = mensaje;
			boolean sessionDebug = false;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			// InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("messages sent successfully");

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
