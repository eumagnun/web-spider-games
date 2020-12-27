package br.com.danielamaral.webspider.repository;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.danielamaral.webspider.model.Game;


@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTest {

	@Autowired
	private GameRepository repository;
	
	@Test
	public void saveGame(){
		Game entity = new Game();
		entity.setActualPrice("100");
		entity.setConsole("PS4");
		entity.setContentType("game");
		entity.setCreatedTime(new Date());
		entity.setDetailsLink("http://teste");
		entity.setDisccountPercent("10%");
		entity.setImageLink("http://link");
		entity.setName("Mortal Kombat");
		entity.setOriginalPrice("50");
		entity.setPage("1");

		repository.save(entity);		

		Game entity2 = new Game();
		entity2.setActualPrice("200");
		entity2.setConsole("PS4");
		entity2.setContentType("game");
		entity2.setCreatedTime(new Date());
		entity2.setDetailsLink("http://teste2");
		entity2.setDisccountPercent("10%");
		entity2.setImageLink("http://link");
		entity2.setName("Mortal Kombat 11");
		entity2.setOriginalPrice("350");
		entity2.setPage("3");
		
		Game gameSaved2 = repository.save(entity2);
		
		Assert.assertNotNull(gameSaved2);
		Assert.assertTrue(gameSaved2.getId()>0);
	}
	
	@Test
	public void searchWithResults(){
		Game entity = new Game();
		entity.setActualPrice("100");
		entity.setConsole("PS4");
		entity.setContentType("game");
		entity.setCreatedTime(new Date());
		entity.setDetailsLink("http://teste");
		entity.setDisccountPercent("10%");
		entity.setImageLink("http://link");
		entity.setName("Mortal Kombat");
		entity.setOriginalPrice("50");
		entity.setPage("1");

		repository.save(entity);			

		Game entity2 = new Game();
		entity2.setActualPrice("200");
		entity2.setConsole("PS4");
		entity2.setContentType("game");
		entity2.setCreatedTime(new Date());
		entity2.setDetailsLink("http://teste2");
		entity2.setDisccountPercent("10%");
		entity2.setImageLink("http://link");
		entity2.setName("Mortal Kombat 11");
		entity2.setOriginalPrice("350");
		entity2.setPage("3");
		
		repository.save(entity2);
		
		List<Game> list = repository.findByNameIgnoreCaseContaining("Mortal");		

		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 1);

	}

}
