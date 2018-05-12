package grupo3.fantur.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RESERVA")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reserva", nullable = false)
	private long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_reserva", nullable = false)
	private Date fechaReserva;

	@NotNull
	@Column(name = "estado", nullable = false)
	private String estado;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_paquete", nullable = false)
	private Paquete paquete;

	// CONSTRUCTORES

	public Reserva() {

	}

	// GETTERS & SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

}
