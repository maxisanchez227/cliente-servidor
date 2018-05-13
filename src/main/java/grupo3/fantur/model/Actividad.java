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

@Entity
@Table(name = "ACTIVIDAD")
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_actividad")
	private long id;

	@NotNull
	@Column(name = "nombre_actividad", nullable = false)
	private String nombre;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actividad", nullable = false)
	private Date fechaActividad;

	@NotNull
	@Column(name = "precio_x_persona", nullable = false)
	private float precioPorPersona;

	@NotNull
	@Column(name = "descripcion", nullable = false)
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
