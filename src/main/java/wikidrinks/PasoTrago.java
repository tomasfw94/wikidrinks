package wikidrinks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pasos_trago")
public class PasoTrago {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_paso_trago", nullable = false, updatable = false)
	private Integer id;
	
	@Column(name="orden")
	private int orden;
	
	@Column(name="texto")
	private String texto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}
}
