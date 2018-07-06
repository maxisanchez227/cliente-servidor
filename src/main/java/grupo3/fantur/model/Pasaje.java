package grupo3.fantur.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PASAJE")
public class Pasaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pasaje", nullable = false)
	private long id;

	@NotNull
	@Column(name = "origen", nullable = false)
	private String origen;

	@NotNull
	@Column(name = "destino", nullable = false)
	private String destino;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_partida", nullable = false)
	private Date fechaPartida;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_regreso", nullable = false)
	private Date fechaRegreso;

	@NotNull
	@Column(name = "precio_pasaje", nullable = false)
	private float precioPasaje;

	@NotNull
	@Column(name = "medio_transporte", nullable = false)
	private String medioTransporte;

	// CONSTRUCTORES

	public Pasaje() {

	}

	// GETTERS & SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(Date fechaPartida) {
		this.fechaPartida = fechaPartida;
	}

	public Date getFechaRegreso() {
		return fechaRegreso;
	}

	public void setFechaRegreso(Date fechaRegreso) {
		this.fechaRegreso = fechaRegreso;
	}

	public float getPrecioPasaje() {
		return precioPasaje;
	}

	public void setPrecioPasaje(float precioPasaje) {
		this.precioPasaje = precioPasaje;
	}

	public String getMedioTransporte() {
		return medioTransporte;
	}

	public void setMedioTransporte(String medioTransporte) {
		this.medioTransporte = medioTransporte;
	}

}
