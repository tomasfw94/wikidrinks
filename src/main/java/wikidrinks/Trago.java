package wikidrinks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    
    @Column(name="trago_autor")
    private Boolean tragoAutor;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Puntuacion> puntuaciones = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario")
	private Usuario usuario;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingrediente> ingredientes = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Consejo> consejos = new ArrayList<>();

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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(List<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}
	
	public BigDecimal getPuntuacion() {
		if(!puntuaciones.isEmpty()) {
			BigDecimal suma = BigDecimal.ZERO;
			for (Puntuacion p : puntuaciones) {
				suma = suma.add(BigDecimal.valueOf(p.getPuntuacion()));
			}
			return suma.divide(BigDecimal.valueOf(puntuaciones.size()), 2, RoundingMode.HALF_UP);
		}else
			return null;
	}

	public Boolean getTragoAutor() {
		return tragoAutor;
	}

	public void setTragoAutor(Boolean tragoAutor) {
		this.tragoAutor = tragoAutor;
	}
    
	public TragoDTO getDto() {
		TragoDTO t = new TragoDTO();
		
		t.setId(getId());
		t.setNombre(getNombre());
		t.setVaso(getVaso());
		t.setGraduacion(getGraduacion());
		t.setUrlImagen(getImagen());
		t.setDeAutor(getTragoAutor() == null ? false : getTragoAutor());
		t.setActivo(getActivo() == null ? false : getActivo());
		
		List<Ingrediente> ings = getIngredientes();
		int size = ings.size();
		if(0 < size) {
			t.setIng1(ings.get(0).getNombre());
			t.setMedIng1(ings.get(0).getMedida());
		}
		if(1 < size) {
			t.setIng2(ings.get(1).getNombre());
			t.setMedIng2(ings.get(1).getMedida());
		}
		if(2 < size) {
			t.setIng3(ings.get(2).getNombre());
			t.setMedIng3(ings.get(2).getMedida());
		}
		if(3 < size) {
			t.setIng4(ings.get(3).getNombre());
			t.setMedIng4(ings.get(3).getMedida());
		}
		if(4 < size) {
			t.setIng5(ings.get(4).getNombre());
			t.setMedIng5(ings.get(4).getMedida());
		}
		if(5 < size) {
			t.setIng6(ings.get(5).getNombre());
			t.setMedIng6(ings.get(5).getMedida());
		}
		if(6 < size) {
			t.setIng7(ings.get(6).getNombre());
			t.setMedIng7(ings.get(6).getMedida());
		}
		
		
		return t;
	}

	public List<Consejo> getConsejos() {
		return consejos;
	}

	public void setConsejos(List<Consejo> consejos) {
		this.consejos = consejos;
	}
}
