package comprobantes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {
	List<Proveedor> findAll();
	Proveedor findById(Integer id);
	Proveedor findByCuit(String cuit);
}


