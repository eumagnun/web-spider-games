package br.com.danielamaral.webspider.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.danielamaral.webspider.util.LoggerUtil;

@Service
public class PlaystationStoreRetrieveDataProcess {

	@Value("${store.uri}")
	private String storeUri;

	@Value("${store.firstPage}")
	private int storeFirstPage;

	@Value("${store.defaultTotalPages}")
	private int defaultTotalPages;

	public static void main(String[] args) throws IOException {
		PlaystationStoreRetrieveDataProcess data = new PlaystationStoreRetrieveDataProcess();
		data.getGameData();
	}

	public Set<String> getGameData() throws IOException {
		int verifiedTotalPages = getTotalPages();
		int numeroPaginas = verifiedTotalPages > 0 ? verifiedTotalPages : defaultTotalPages;

		HashSet<String> lista = new HashSet<>();

		for (int i = storeFirstPage; i <= numeroPaginas; i++) {

			try {

				Document doc = Jsoup.connect(storeUri + i).get();

				Elements e1 = doc.getElementsByClass("__desktop-presentation__grid-cell__base__0ba9f ember-view");

				for (Element e2 : e1) {
					String games = "";
					games += i + ";";
					games = extrairLinkDetalhes(e2, games);
					games = extrairPercentualDesconto(e2, games);
					games = extrairPrecoOriginal(e2, games);
					games = extrairPrecoAtual(e2, games);
					games = extrairNomeJogo(e2, games);
					games = extrairTipoConteudo(e2, games);
					games = extrairPlataforma(e2, games);
					games = extrairLinkImagem(e2, games);


					lista.add(games);
				}

			} catch (Exception e) {
				LoggerUtil.logError("getGameData", PlaystationStoreRetrieveDataProcess.class.getCanonicalName(), e.getMessage());
			}

		}

		return lista;
	}

	private int getTotalPages() {
		LoggerUtil.logInfo("getTotalPages", "PlaystationStoreRetrieveDataProcess");
		int totalPages = 0;

		try {

			Document doc = Jsoup.connect(storeUri + "1000").get();

			String[] membersOfPath = doc.location().split("/");
			ArrayUtils.reverse(membersOfPath);
			totalPages = Integer.parseInt(membersOfPath[0]);

		} catch (Exception e) {
			LoggerUtil.logError("getTotalPages", PlaystationStoreRetrieveDataProcess.class.getCanonicalName(), e.getMessage());

		}
		return totalPages;
	}

	private String extrairLinkImagem(Element e2, String games) {
		try {
			games += e2.getElementsByClass("grid-cell--game").get(0).select("img").get(1).absUrl("src").concat(";");
		} catch (Exception ex) {
			games += "SEM_LINK_IMAGEM".concat(";");

		}
		return games;
	}

	private String extrairPlataforma(Element e2, String games) {
		try {
			games += e2.getElementsByClass("grid-cell__left-detail grid-cell__left-detail--detail-1").text()
					.concat(";");
		} catch (Exception ex) {
			games += "SEM_PLATAFORMA".concat(";");
		}
		return games;
	}

	private String extrairTipoConteudo(Element e2, String games) {
		try {
			games += e2.getElementsByClass("grid-cell__left-detail grid-cell__left-detail--detail-2").text()
					.concat(";");
		} catch (Exception ex) {
			games += "_TIPO_CONTEUDO".concat(";");
		}
		return games;
	}

	private String extrairNomeJogo(Element e2, String games) {
		try {
			games += e2.getElementsByClass("grid-cell__title ").text().concat(";");
		} catch (Exception ex) {
			games += "SEM_NOME_JOGO".concat(";");
		}
		return games;
	}

	private String extrairPrecoAtual(Element e2, String games) {
		try {
			games += e2.getElementsByClass("price-display__price").text().concat(";");
		} catch (Exception ex) {
			games += "SEM_PRECO_ATUAL".concat(";");
		}
		return games;
	}

	private String extrairPrecoOriginal(Element e2, String games) {
		try {
			games += e2.getElementsByClass("price").text().concat(";");
		} catch (Exception ex) {
			games += "SEM_PRECO_ORIGINAL".concat(";");
		}
		return games;
	}

	private String extrairPercentualDesconto(Element e2, String games) {
		try {
			games += e2.getElementsByClass("discount-badge__message").text().concat(";");
		} catch (Exception ex) {
			games += "SEM_PERCENTUAL_DESCONTO".concat(";");
		}
		return games;
	}

	private String extrairLinkDetalhes(Element e2, String games) {
		try {
			games += e2.getElementsByClass("grid-cell--game").first().select("a").get(1).absUrl("href").concat(";");
		} catch (Exception ex) {
			games += "SEM_LINK_DETALHE".concat(";");
		}
		return games;
	}

}
