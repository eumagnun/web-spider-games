package br.com.danielamaral.webspider.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielamaral.webspider.job.PlaystationStoreJob;
import br.com.danielamaral.webspider.model.Game;
import br.com.danielamaral.webspider.model.GameDto;
import br.com.danielamaral.webspider.repository.GameRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/games")
public class GameApi {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlaystationStoreJob job;

	@ApiOperation(value = "Consultar um game por id da captura de informações")
	@GetMapping(value = "/{id}", produces = "application/json")
	public GameDto consultar(@PathVariable Long id) {
		return GameDto.parseGameDto(gameRepository.findById(id).get());
	}

	@ApiOperation(value = "Executar captura de informações")
	@GetMapping(value = "/runjob")
	public String run() {
		return job.run();
	}

	@ApiOperation(value = "Pesquisar ocorrências de captura de informações de games")
	@GetMapping(produces = "application/json")
	public List<GameDto> pesquisar() {
		return  GameDto.parseGameDtoList(gameRepository.findAll()) ;
	}

	@ApiOperation(value = "Pesquisar ocorrências de captura de informações de games")
	@GetMapping(value = "name/{name}", produces = "application/json")
	public List<GameDto> pesquisarPorNome(@PathVariable String name) {
		return GameDto.parseGameDtoList(gameRepository.findByNameIgnoreCaseContaining(name));
	}

	@ApiOperation(value = "Inserir informações de games")
	@PostMapping(consumes = "application/json")
	public GameDto salvar(@RequestBody GameDto dto) {
		return GameDto.parseGameDto(gameRepository.save(Game.parseGame(dto)));
	}

	@ApiOperation(value = "Apagar captura de informações de game por id")
	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable Long id) {
		gameRepository.deleteById(id);
	}

}
