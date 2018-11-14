package wikidrinks;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class TragoDTO {
	
	private Integer id;
	private Integer idUsuario;
	private String nombre;
	private Boolean deAutor;
	private Boolean activo;
	private String vaso;
    private float graduacion;
    private String urlImagen;
    private String ing1;
    private String ing2;
    private String ing3;
    private String ing4;
    private String ing5;
    private String ing6;
    private String ing7;
    private float medIng1;
    private float medIng2;
    private float medIng3;
    private float medIng4;
    private float medIng5;
    private float medIng6;
    private float medIng7;
    
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
	public String getUrlImagen() {
		return urlImagen;
	}
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	public String getIng1() {
		return ing1;
	}
	public void setIng1(String ing1) {
		this.ing1 = ing1;
	}
	public String getIng2() {
		return ing2;
	}
	public void setIng2(String ing2) {
		this.ing2 = ing2;
	}
	public String getIng3() {
		return ing3;
	}
	public void setIng3(String ing3) {
		this.ing3 = ing3;
	}
	public String getIng4() {
		return ing4;
	}
	public void setIng4(String ing4) {
		this.ing4 = ing4;
	}
	public String getIng5() {
		return ing5;
	}
	public void setIng5(String ing5) {
		this.ing5 = ing5;
	}
	public String getIng6() {
		return ing6;
	}
	public void setIng6(String ing6) {
		this.ing6 = ing6;
	}
	public String getIng7() {
		return ing7;
	}
	public void setIng7(String ing7) {
		this.ing7 = ing7;
	}
	public float getMedIng1() {
		return medIng1;
	}
	public void setMedIng1(float medIng1) {
		this.medIng1 = medIng1;
	}
	public float getMedIng2() {
		return medIng2;
	}
	public void setMedIng2(float medIng2) {
		this.medIng2 = medIng2;
	}
	public float getMedIng3() {
		return medIng3;
	}
	public void setMedIng3(float medIng3) {
		this.medIng3 = medIng3;
	}
	public float getMedIng4() {
		return medIng4;
	}
	public void setMedIng4(float medIng4) {
		this.medIng4 = medIng4;
	}
	public float getMedIng5() {
		return medIng5;
	}
	public void setMedIng5(float medIng5) {
		this.medIng5 = medIng5;
	}
	public float getMedIng6() {
		return medIng6;
	}
	public void setMedIng6(float medIng6) {
		this.medIng6 = medIng6;
	}
	public float getMedIng7() {
		return medIng7;
	}
	public void setMedIng7(float medIng7) {
		this.medIng7 = medIng7;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Boolean getDeAutor() {
		return deAutor;
	}
	public void setDeAutor(Boolean deAutor) {
		this.deAutor = deAutor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public Trago getTrago() {
		
		Trago trago = new Trago();
		trago.setId(getId());
		trago.setNombre(getNombre());
		trago.setVaso(getVaso());
		trago.setGraduacion(getGraduacion());
		trago.setImagen(getUrlImagen());
		trago.setActivo(true);
		trago.setTragoAutor(getDeAutor());
		
		List<Ingrediente> ingredientes = new ArrayList<>();
		
		if(!StringUtils.isBlank(getIng1())) {
			Ingrediente ing1 = new Ingrediente();
			ing1.setNombre(getIng1());
			ing1.setMedida(getMedIng1());
			ingredientes.add(ing1);
		}
		
		if(!StringUtils.isBlank(getIng2())) {
			Ingrediente ing2 = new Ingrediente();
			ing2.setNombre(getIng2());
			ing2.setMedida(getMedIng2());
			ingredientes.add(ing2);
		}
		
		if(!StringUtils.isBlank(getIng3())) {
			Ingrediente ing3 = new Ingrediente();
			ing3.setNombre(getIng3());
			ing3.setMedida(getMedIng3());
			ingredientes.add(ing3);
		}
		
		if(!StringUtils.isBlank(getIng4())) {
			Ingrediente ing4 = new Ingrediente();
			ing4.setNombre(getIng4());
			ing4.setMedida(getMedIng4());
			ingredientes.add(ing4);
		}
		
		if(!StringUtils.isBlank(getIng5())) {
			Ingrediente ing5 = new Ingrediente();
			ing5.setNombre(getIng5());
			ing5.setMedida(getMedIng5());
			ingredientes.add(ing5);
		}
		
		if(!StringUtils.isBlank(getIng6())) {
			Ingrediente ing6 = new Ingrediente();
			ing6.setNombre(getIng6());
			ing6.setMedida(getMedIng6());
			ingredientes.add(ing6);
		}
		
		if(!StringUtils.isBlank(getIng7())) {
			Ingrediente ing7 = new Ingrediente();
			ing7.setNombre(getIng7());
			ing7.setMedida(getMedIng7());
			ingredientes.add(ing7);
		}
		
		trago.setIngredientes(ingredientes);
		
		return trago;
	}
}
