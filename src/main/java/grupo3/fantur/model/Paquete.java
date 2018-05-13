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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PAQUETE")
public class Paquete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paquete", nullable = false)
	private long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[a-z \\s A-Z 0-9 ñ Ñ]{1,50}", message = "El nombre no puede contener caracteres no válidos")
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@NotNull
	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@NotNull
	@Column(name = "cant_personas", nullable = false)
	private int cantidadPersonas;

	@NotNull
	@Column(name = "precio", nullable = false)
	private float precio;

	@NotNull
	@Column(name = "cant_disponible", nullable = false)
	private int cantidadDisponible;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_pasaje", nullable = false)
	private Pasaje pasaje;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_hotel", nullable = false)
	private Hotel hotel;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_actividad", nullable = false)
	private Actividad actividad;

	// CONSTRUCTORES

	public Paquete() {

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Pasaje getPasaje() {
		return pasaje;
	}

	public void setPasaje(Pasaje pasaje) {
		this.pasaje = pasaje;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

}
