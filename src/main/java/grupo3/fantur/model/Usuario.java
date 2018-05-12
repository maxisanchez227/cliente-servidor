package grupo3.fantur.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[a-z \\s A-Z ñ Ñ]{1,50}", message = "El nombre no puede contener caracteres no válidos")
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[a-z \\s A-Z ñ Ñ]{1,50}", message = "El nombre no puede contener caracteres no válidos")
	@Column(name = "apellido", nullable = false)
	private String apellido;

	@NotNull
	@Min(1)
	@Max(99999999L)
	@Column(name = "dni", nullable = false)
	private long dni;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Past(message = "La fecha de nacimeinto debe ser menor a la fecha actual")
	@Column(name = "fecha_nac", nullable = false)
	private Date fechaNac;

	@NotNull
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[a-z \\s A-Z 0-9 ñ Ñ]{1,100}", message = "La dirección no puede contener caracteres no válidos")
	@Column(name = "direccion", nullable = false)
	private String direccion;

	@NotNull
	@Column(name = "email", nullable = false)
	private String email;

	@NotNull
	@Column(name = "username", nullable = false)
	private String username;

	@NotNull
	@Column(name = "password", nullable = false)
	private String password;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false)
	private Rol rol;

	// CONSTRUCTORES

	public Usuario() {

	}

	// GETTERS & SETTERS

	// --------------- datos personales ---------------

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// ----------------- datos cuenta -----------------

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
