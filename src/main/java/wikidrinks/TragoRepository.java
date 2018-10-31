package wikidrinks;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TragoRepository extends CrudRepository<Trago, Integer> {

	@Override
	List<Trago> findAll();
	Trago findById(Integer id);
	void deleteById(Integer id);
	Trago findByNombre(String nombre);
	Trago findByVaso(String vaso);
	Trago findByGraduacion(Integer graduacion);
	Trago findByPuntuacion(Integer puntuacion);
}
