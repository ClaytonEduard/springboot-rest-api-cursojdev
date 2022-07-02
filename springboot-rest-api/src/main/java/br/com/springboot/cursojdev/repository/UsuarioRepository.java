package br.com.springboot.cursojdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.cursojdev.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "select u from Usuario u where u.nome like %?1%")// ?1 somente para busca de um dado, no caso o nome 
	List<Usuario> buscarPorNome(String name);
	
	
}
