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

@Entity
@Table(name = "ACTIVIDAD")
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_actividad")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actividad")
	private Date fechaActividad;

	@Column(name = "precio_x_persona")
	private float precioPorPersona;

	@Column(name = "descripcion")
	private String descripcion;

	// CONSTRUCTORES

	public Actividad() {

	}

	// GETTERS & SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(Date fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public float getPrecioPorPersona() {
		return precioPorPersona;
	}

	public void setPrecioPorPersona(float precioPorPersona) {
		this.precioPorPersona = precioPorPersona;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
