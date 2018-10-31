package comprobantes;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "comprobantes")
public class Comprobante {
	@Id
	@GeneratedValue
	@Column(name="id_comprobante")
	private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_proveedor")
	private Proveedor proveedor;
    @Column(name="estado")
	private String estado;
    @Column(name="fecha")
	private Date fecha;
    @Column(name="importe")
	private float importe;
    @Column(name="observaciones")
	private String observaciones;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id_comprobante) {
		this.id = id_comprobante;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
}
