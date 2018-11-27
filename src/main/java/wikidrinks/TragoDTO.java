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
    private Float graduacionMin;
    private Float graduacionMax;
    private List<String> tipos;
    private List<String> pasos;
    private String urlImagen;
    private String ing1;
    private String ing2;
    private String ing3;
    private String ing4;
    private String ing5;
    private String ing6;
    private String ing7;
    private String ing8;
    private String ing9;
    private String ing10;
    private float medIng1;
    private float medIng2;
    private float medIng3;
    private float medIng4;
    private float medIng5;
    private float medIng6;
    private float medIng7;
    private String textoPaso1;
    private String textoPaso2;
    private String textoPaso3;
    private String textoPaso4;
    private String textoPaso5;
    private String textoPaso6;
    private String textoPaso7;
    private String textoPaso8;
    private String textoPaso9;
    private String textoPaso10;
    
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
		
		if(tipos != null) {
			List<TipoTrago> tiposTrago = new ArrayList<>();
			
			for (String nombre : tipos) {
				TipoTrago tt = new TipoTrago();
				tt.setNombre(nombre);
				tiposTrago.add(tt);
			}
			
			trago.setTipos(tiposTrago);
		}
		
		trago.setPasos(getPasosTrago());
		
		return trago;
	}
	public List<String> getTipos() {
		return tipos;
	}
	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}
	public String getIng8() {
		return ing8;
	}
	public void setIng8(String ing8) {
		this.ing8 = ing8;
	}
	public String getIng9() {
		return ing9;
	}
	public void setIng9(String ing9) {
		this.ing9 = ing9;
	}
	public String getIng10() {
		return ing10;
	}
	public void setIng10(String ing10) {
		this.ing10 = ing10;
	}
	public List<Ingrediente> getIngredientes(){
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
		
		if(!StringUtils.isBlank(getIng8())) {
			Ingrediente ing8 = new Ingrediente();
			ing8.setNombre(getIng8());
			ingredientes.add(ing8);
		}
		if(!StringUtils.isBlank(getIng9())) {
			Ingrediente ing9 = new Ingrediente();
			ing9.setNombre(getIng9());
			ingredientes.add(ing9);
		}
		if(!StringUtils.isBlank(getIng10())) {
			Ingrediente ing10 = new Ingrediente();
			ing10.setNombre(getIng10());
			ingredientes.add(ing10);
		}
		
		return ingredientes;
	}
	
	public List<String> getNombresIngredientesDTO(){
		List<String> nombres = new ArrayList<>();
		
		if(!StringUtils.isBlank(getIng1())) {
			nombres.add(getIng1().toUpperCase().replaceAll("\\s",""));
		}
		if(!StringUtils.isBlank(getIng2())) {
			nombres.add(getIng2().toUpperCase().replaceAll("\\s",""));
		}
		if(!StringUtils.isBlank(getIng3())) {
			nombres.add(getIng3().toUpperCase().replaceAll("\\s",""));
		}
		if(!StringUtils.isBlank(getIng4())) {
			nombres.add(getIng4().toUpperCase().replaceAll("\\s",""));
		}
		if(!StringUtils.isBlank(getIng5())) {
			nombres.add(getIng5().toUpperCase().replaceAll("\\s",""));
		}
		if(!StringUtils.isBlank(getIng6())) {
			nombres.add(getIng6().toUpperCase().replaceAll("\\s",""));
		}
		if(!StringUtils.isBlank(getIng7())) {
			nombres.add(getIng7().toUpperCase().replaceAll("\\s",""));
		}
		if(!StringUtils.isBlank(getIng8())) {
			nombres.add(getIng8().toUpperCase().replaceAll("\\s",""));
		}
		if(!StringUtils.isBlank(getIng9())) {
			nombres.add(getIng9().toUpperCase().replaceAll("\\s",""));
		}
		if(!StringUtils.isBlank(getIng10())) {
			nombres.add(getIng10().toUpperCase().replaceAll("\\s",""));
		}
		
		return nombres;
	}
	public Float getGraduacionMin() {
		return graduacionMin;
	}
	public void setGraduacionMin(Float graduacionMin) {
		this.graduacionMin = graduacionMin;
	}
	public Float getGraduacionMax() {
		return graduacionMax;
	}
	public void setGraduacionMax(Float graduacionMax) {
		this.graduacionMax = graduacionMax;
	}
	public boolean esMedidaValida() {
		return medIng1+medIng2+medIng3+medIng4+medIng5+medIng6+medIng7 > 100 ? false : true;
	}
	public List<String> getPasos() {
		return pasos;
	}
	public void setPasos(List<String> pasos) {
		this.pasos = pasos;
	}
	public String getTextoPaso1() {
		return textoPaso1;
	}
	public void setTextoPaso1(String textoPaso1) {
		this.textoPaso1 = textoPaso1;
	}
	public String getTextoPaso2() {
		return textoPaso2;
	}
	public void setTextoPaso2(String textoPaso2) {
		this.textoPaso2 = textoPaso2;
	}
	public String getTextoPaso3() {
		return textoPaso3;
	}
	public void setTextoPaso3(String textoPaso3) {
		this.textoPaso3 = textoPaso3;
	}
	public String getTextoPaso4() {
		return textoPaso4;
	}
	public void setTextoPaso4(String textoPaso4) {
		this.textoPaso4 = textoPaso4;
	}
	public String getTextoPaso5() {
		return textoPaso5;
	}
	public void setTextoPaso5(String textoPaso5) {
		this.textoPaso5 = textoPaso5;
	}
	public String getTextoPaso6() {
		return textoPaso6;
	}
	public void setTextoPaso6(String textoPaso6) {
		this.textoPaso6 = textoPaso6;
	}
	public String getTextoPaso7() {
		return textoPaso7;
	}
	public void setTextoPaso7(String textoPaso7) {
		this.textoPaso7 = textoPaso7;
	}
	public String getTextoPaso8() {
		return textoPaso8;
	}
	public void setTextoPaso8(String textoPaso8) {
		this.textoPaso8 = textoPaso8;
	}
	public String getTextoPaso9() {
		return textoPaso9;
	}
	public void setTextoPaso9(String textoPaso9) {
		this.textoPaso9 = textoPaso9;
	}
	public String getTextoPaso10() {
		return textoPaso10;
	}
	public void setTextoPaso10(String textoPaso10) {
		this.textoPaso10 = textoPaso10;
	}
	public List<PasoTrago> getPasosTrago(){
		List<PasoTrago> pasos = new ArrayList<>();
		
		if(!StringUtils.isBlank( getTextoPaso1() ) ) {
			PasoTrago paso1 = new PasoTrago();
			paso1.setOrden(1);
			paso1.setTexto(textoPaso1);
			pasos.add(paso1);
		}
		if(!StringUtils.isBlank( getTextoPaso2() ) ) {
			PasoTrago paso2 = new PasoTrago();
			paso2.setOrden(2);
			paso2.setTexto(textoPaso2);
			pasos.add(paso2);
		}
		if(!StringUtils.isBlank( getTextoPaso3() ) ) {
			PasoTrago paso3 = new PasoTrago();
			paso3.setOrden(3);
			paso3.setTexto(textoPaso3);
			pasos.add(paso3);
		}
		if(!StringUtils.isBlank( getTextoPaso4() ) ) {
			PasoTrago paso4 = new PasoTrago();
			paso4.setOrden(4);
			paso4.setTexto(textoPaso4);
			pasos.add(paso4);
		}
		if(!StringUtils.isBlank( getTextoPaso5() ) ) {
			PasoTrago paso5 = new PasoTrago();
			paso5.setOrden(5);
			paso5.setTexto(textoPaso5);
			pasos.add(paso5);
		}
		if(!StringUtils.isBlank( getTextoPaso6() ) ) {
			PasoTrago paso6 = new PasoTrago();
			paso6.setOrden(6);
			paso6.setTexto(textoPaso6);
			pasos.add(paso6);
		}
		if(!StringUtils.isBlank( getTextoPaso7() ) ) {
			PasoTrago paso7 = new PasoTrago();
			paso7.setOrden(7);
			paso7.setTexto(textoPaso7);
			pasos.add(paso7);
		}
		if(!StringUtils.isBlank( getTextoPaso8() ) ) {
			PasoTrago paso8 = new PasoTrago();
			paso8.setOrden(8);
			paso8.setTexto(textoPaso8);
			pasos.add(paso8);
		}
		if(!StringUtils.isBlank( getTextoPaso9() ) ) {
			PasoTrago paso9 = new PasoTrago();
			paso9.setOrden(9);
			paso9.setTexto(textoPaso9);
			pasos.add(paso9);
		}
		if(!StringUtils.isBlank( getTextoPaso10() ) ) {
			PasoTrago paso10 = new PasoTrago();
			paso10.setOrden(10);
			paso10.setTexto(textoPaso10);
			pasos.add(paso10);
		}

		return pasos;
	}
		
}
