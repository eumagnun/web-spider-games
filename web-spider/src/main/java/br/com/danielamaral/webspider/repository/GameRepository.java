package br.com.danielamaral.webspider.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielamaral.webspider.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
	  
	  List<Game> findByNameIgnoreCaseContaining(String name);
	  
	  List<Game> findByOriginalPriceBetween(String initValue, String endValue);


}
