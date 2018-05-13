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
@Table(name = "HOTEL")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_hotel", nullable = false)
	private long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[a-z \\s A-Z 0-9 ñ Ñ]{1,50}", message = "El nombre no puede contener caracteres no válidos")
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@NotNull
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[a-z \\s A-Z 0-9 ñ Ñ]{1,50}", message = "La dirección no puede contener caracteres no válidos")
	@Column(name = "direccion", nullable = false)
	private String direccion;

	@NotNull
	@Column(name = "precio_x_persona", nullable = false)
	private float precioPorPersona;

	// CONSTRUCTORES

	public Hotel() {

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getPrecioPorPersona() {
		return precioPorPersona;
	}

	public void setPrecioPorPersona(float precioPorPersona) {
		this.precioPorPersona = precioPorPersona;
	}

}
