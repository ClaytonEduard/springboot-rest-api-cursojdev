package br.com.springboot.cursojdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.cursojdev.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%") // ?1 somente para busca de um dado, no caso
																			// o nome / trim para retirar os espaços do
																			// vindo co banco
	// upper para tornar maiusculas
	List<Usuario> buscarPorNome(String name);

}
