package wikidrinks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consejos")
public class Consejo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_consejo", nullable = false, updatable = false)
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="texto")
	private String texto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
