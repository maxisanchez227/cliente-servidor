package grupo3.fantur.jsf;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import grupo3.fantur.dao.UsuarioDao;
import grupo3.fantur.model.Usuario;

@ManagedBean
@ViewScoped
public class MensajeriaBean {

	private List<String> mails;
	private String direcciones;
	private String asunto;
	private String mensaje;
	private boolean todos;
	private static List<Usuario> usuarios;

	private static Logger logger = Logger.getLogger(MensajeriaBean.class.getSimpleName());

	@Inject
	UsuarioDao usuarioDao;

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:/jms/queue/FanturQueue")
	private Queue queue;

	@PostConstruct
	public void init() {
		mails = new ArrayList<String>();
		todos = false;
		usuarios = usuarioDao.findAll();
	}

	/*
	 * Enviar a la cola
	 * 
	 */
	public void send() {

		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;

		try {
			// 1 - Create the Connection
			connection = connectionFactory.createConnection();

			// 2 - Create the Session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// 3 - Create the producer
			producer = session.createProducer(queue);

			// 4 - Create the message
			TextMessage textMessage = session.createTextMessage();

			// 5 - Set the payload
			textMessage.setText(mensaje);
			if (todos) {
				mails = todosEmails();
			}
			direcciones = "";
			for (String d : mails) {
				direcciones += d + ",";
			}
			textMessage.setStringProperty("direcciones", direcciones);
			textMessage.setStringProperty("asunto", asunto);

			// 6 - Send the message
			producer.send(textMessage);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensajes enviados", "Mensajes enviados con éxito "));

		} catch (JMSException e) {

			logger.log(Level.SEVERE, e.getMessage());

		} finally {
			// 7 - Clean up
			if (producer != null) {
				try {
					producer.close();
				} catch (JMSException e) {
				}
			}
			if (session != null) {
				try {
					session.close();
				} catch (JMSException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
				}
			}
		}
	}

	/*
	 * Devuelve los mails de todos los usuarios
	 * 
	 */
	public static List<String> todosEmails() {
		List<String> mc = new ArrayList<String>();
		for (Usuario u : usuarios) {
			mc.add(u.getEmail());
		}
		return mc;
	}

	// GETTERS & SETTERS

	public String getDirecciones() {
		return direcciones;
	}

	public List<String> getMails() {
		return mails;
	}

	public void setMails(List<String> mails) {
		this.mails = mails;
	}

	public void setDirecciones(String direcciones) {
		this.direcciones = direcciones;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean getTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

}
