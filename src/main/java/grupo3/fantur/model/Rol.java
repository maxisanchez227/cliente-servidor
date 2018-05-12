package grupo3.fantur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ROL")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[a-z \\s A-Z ñ Ñ]{1,50}", message = "El nombre no puede contener caracteres no válidos")
	@Column(name = "nombre_rol", nullable = false)
	private String nombre;

	// CONSTRUCTORES

	public Rol() {

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

}
