package grupo3.fantur.jsf;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDrivenContext;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import grupo3.fantur.mail.Mail;

@MessageDriven(mappedName = "java:/jms/queue/FanturQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/FanturQueue") })
public class JMSProvider implements MessageListener {

	private static final Logger logger = Logger.getLogger(JMSProvider.class.getSimpleName());

	@Resource
	private MessageDrivenContext mdc;

	@Override
	public void onMessage(Message message) {

		try {
			if (message instanceof TextMessage) {
				String direcciones = message.getStringProperty("direcciones");
				String asunto = message.getStringProperty("asunto");
				String mensaje = message.getBody(String.class);
				Mail mail = new Mail();
				mail.sendMsg(direcciones, asunto, mensaje);
			} else {
				logger.log(Level.WARNING, "Message of wrong type: {0}", message.getClass().getName());
			}
		} catch (JMSException e) {
			mdc.setRollbackOnly();
		}
	}
}