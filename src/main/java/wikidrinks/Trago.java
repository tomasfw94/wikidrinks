package wikidrinks;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tragos")
public class Trago {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_trago", nullable = false, updatable = false)
    private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
    @Column(name="vaso")
	private String vaso;
    
    @Column(name="graduacion")
    private float graduacion;
    
    @Column(name="imagen")
    private String imagen;
    
    @Column(name="activo")
    private Boolean activo;
    
    @Column(name="puntuacion")
    private float puntuacion;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVaso() {
		return vaso;
	}

	public void setVaso(String vaso) {
		this.vaso = vaso;
	}

	public float getGraduacion() {
		return graduacion;
	}

	public void setGraduacion(float graduacion) {
		this.graduacion = graduacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
    
}
