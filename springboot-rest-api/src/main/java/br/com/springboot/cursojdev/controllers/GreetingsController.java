package br.com.springboot.cursojdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.cursojdev.model.Usuario;
import br.com.springboot.cursojdev.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	// declaracao dos repositorios
	@Autowired // implementa todos os dados de injeceao de dependencias CDI ou IC/CD
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Curso Spring Boot API " + name + "!";
	}

	// criando uma nova rota
	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	public String retornaOlaMundo(@PathVariable String nome) {
		
		Usuario usuario =  new Usuario();
		usuario.setNome(nome);
		
		usuarioRepository.save(usuario);
		
		
		return "Olá Mundo " + nome;
	}

}
