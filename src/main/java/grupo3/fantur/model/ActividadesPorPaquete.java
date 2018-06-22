package grupo3.fantur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ACTIVIDADES_POR_PAQUETE")
public class ActividadesPorPaquete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pqt_act", nullable = false)
	private long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_paquete")
	private Paquete paquete;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_actividad")
	private Actividad actividad;

	// CONSTRUCTORES

	public ActividadesPorPaquete() {

	}

	// GETTERS & SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

}
