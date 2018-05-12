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
@Table(name = "PAGO")
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pago", nullable = false)
	private long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_pago", nullable = false)
	private Date fechaPago;

	@NotNull
	@Column(name = "importe", nullable = false)
	private double importe;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_reserva", nullable = false)
	private Reserva reserva;

	@NotNull
	@OneToOne
	@JoinColumn(name = "medio_de_pago", nullable = false)
	private MedioDePago medioDePago;

	// CONSTRUCTORES

	public Pago() {

	}

	// GETTERS & SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public MedioDePago getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}

}
