package comprobantes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ComprobanteRepository extends CrudRepository<Comprobante, Integer> {
	List<Comprobante> findAll();
	Comprobante findById(Integer id);
	void deleteById(Integer id);
}
