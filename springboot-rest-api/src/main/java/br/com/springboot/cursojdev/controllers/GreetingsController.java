package br.com.springboot.cursojdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

		Usuario usuario = new Usuario();
		usuario.setNome(nome);

		usuarioRepository.save(usuario);

		return "Olá Mundo " + nome;
	}

	// lista de usuarios

	@GetMapping(value = "listatodos")
	@ResponseBody // retorna os dados para o corpo da resposta
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll(); // executa a consulta no banco de dados

		// retorna a lista de usuarios com o status
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // retorna a lista em JSON

	}

	// metodo salvar
	@PostMapping(value = "salvar") // mapeia a url
	@ResponseBody // descrisao da resposta
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { // receber os dados para salvar no banco
		Usuario user = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}
	
	// metodo atualizar
		@PutMapping(value = "atualizar") // mapeia a url
		@ResponseBody // descrisao da resposta
		public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) { // receber os dados para salvar no banco
			Usuario user = usuarioRepository.saveAndFlush(usuario);// atualiza direto no banco
			return new ResponseEntity<Usuario>(user, HttpStatus.OK);

		}

	// metodo delete
	@DeleteMapping(value = "delete") // mapeia a url
	@ResponseBody // descrisao da resposta
	public ResponseEntity<String> delete(@RequestParam Long id) { // receber os dados para salvar no banco
		usuarioRepository.deleteById(id);
		return new ResponseEntity<String>("Usuario deletado com sucesso!", HttpStatus.OK);

	}
	// buscar por id

	@GetMapping(value = "buscaruserid") // mapeia a url
	@ResponseBody // descrisao da resposta
	public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "id") Long id) { // receber os dados para salvar no
																						// banco
		Usuario user = usuarioRepository.findById(id).get();
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}
	
	

}
