package grupo3.fantur.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import grupo3.fantur.dao.UsuarioDao;
import grupo3.fantur.mail.Mail;
import grupo3.fantur.model.Usuario;

@ManagedBean
@ViewScoped
public class MensajeriaBean {

	private Mail mail;
	private List<String> mails;
	private String direcciones;
	private String asunto;
	private String mensaje;
	private boolean todos;
	private static List<Usuario> usuarios;

	@Inject
	UsuarioDao usuarioDao;

	@PostConstruct
	public void init() {
		mail = new Mail();
		mails = new ArrayList<String>();
		todos = false;
		usuarios = usuarioDao.findAll();
	}
	
	/*
	 * Enviar mails
	 * 
	 */
	public void enviarMsj() {
		if (todos) {
			mails = todosEmails();
		}
		direcciones = "";
		for (String d : mails) {
			direcciones += d + ",";
		}
		System.out.println(direcciones);
		System.out.println(asunto);
		System.out.println(mensaje);
		mail.sendMsg(direcciones, asunto, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensajes enviados", "Mensajes enviados con éxito "));
	}

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
