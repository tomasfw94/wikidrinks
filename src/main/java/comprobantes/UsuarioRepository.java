package comprobantes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	List<Usuario> findAll();
	Usuario findById(Integer id);
	void deleteById(Integer id);
	Usuario findByUsername(String username);
}
