package grupo3.fantur.jsf;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import grupo3.fantur.model.Rol;
import grupo3.fantur.model.Usuario;

@ManagedBean
@SessionScoped
public class SesionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * esAdmin // esCliente
	 * 
	 */
	public static boolean esAdmin(Usuario u) {
		boolean admin = false;
		List<Rol> roles = u.getRoles();
		for (Rol r : roles) {
			if (r.getNombre().equals("Administrador")) {
				admin = true;
			}
		}
		return admin;
	}

	public static boolean esCliente(Usuario u) {
		boolean cliente = false;
		List<Rol> roles = u.getRoles();
		for (Rol r : roles) {
			if (r.getNombre().equals("Cliente")) {
				cliente = true;
			}
		}
		return cliente;
	}

	/*
	 * Verifica que el usuario este logueado
	 * 
	 */
	public void verificarSesionAdmin() {
		try {
			Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			/*
			 * Si nadie inicio sesion el usuario sera nulo
			 * 
			 */
			if ((u == null) || (!esAdmin(u))) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../accesoDenegado.xhtml");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verificarSesionCliente() {
		try {
			Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			if ((u == null) || (!esCliente(u))) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("accesoDenegado.xhtml");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Cerrar sesion
	 * 
	 */
	public void cerrarSesionAdmin() {
		// Destruye los valores del facescontext de la variable session
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cerrarSesionCliente() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
