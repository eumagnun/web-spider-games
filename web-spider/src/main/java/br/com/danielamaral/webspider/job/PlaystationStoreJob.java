package br.com.danielamaral.webspider.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.danielamaral.webspider.model.Game;
import br.com.danielamaral.webspider.repository.GameRepository;
import br.com.danielamaral.webspider.service.PlaystationStoreRetrieveDataProcess;
import br.com.danielamaral.webspider.util.LoggerUtil;

@Service
public class PlaystationStoreJob {
	@Autowired
	private PlaystationStoreRetrieveDataProcess etl;

	@Autowired
	private GameRepository gameRepository;

	private List<Game> listaGames = new ArrayList<>();

	@Scheduled(cron = "0 01 23 1/1 * *")
	public String run() {

		try {
			Set<String> gameData = etl.getGameData();

			for (String linha : gameData) {

				String[] gameDataValues = linha.replace(";;", ";NA;").split(";");

				if (gameDataValues.length > 8) {
					Game g = new Game();
					g.setPage(gameDataValues[0]);
					g.setDetailsLink(gameDataValues[1]);
					g.setDisccountPercent(gameDataValues[2]);
					g.setOriginalPrice(gameDataValues[3]);
					g.setActualPrice(gameDataValues[4]);
					g.setName(gameDataValues[5]);
					g.setContentType(gameDataValues[6]);
					g.setConsole(gameDataValues[7]);
					g.setImageLink(gameDataValues[8]);
					listaGames.add(g);
				}
			}

			gameRepository.saveAll(listaGames);
			return "sucesso";
		} catch (IOException e) {
			LoggerUtil.logError("run", PlaystationStoreJob.class.getCanonicalName(), e.getMessage());
		}
		return "falha";

	}
}
