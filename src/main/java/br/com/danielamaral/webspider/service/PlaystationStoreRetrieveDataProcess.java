package br.com.danielamaral.webspider.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import br.com.danielamaral.webspider.util.LoggerUtil;

@Service
public class PlaystationStoreRetrieveDataProcess {
	
	
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(PlaystationStoreRetrieveDataProcess.class);

	@Value("${store.domain}")
	private String domain;
	
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
		
		LOG.info("Obtendo dados de games");
		//não foi possivel obter o numero total de páginas após atualização da loja
		//int verifiedTotalPages = getTotalPages();
		//int numeroPaginas = verifiedTotalPages > 0 ? verifiedTotalPages : defaultTotalPages;
		
		int numeroPaginas = defaultTotalPages;

		HashSet<String> lista = new HashSet<>();

		for (int i = storeFirstPage; i <= numeroPaginas; i++) {

			try {

				Document doc = Jsoup.connect(domain+storeUri + i).get();

				Elements e1 = doc.select("div.ems-sdk-product-tile");

				for (Element e2 : e1) {
					String games = "";
					games += i +  ";";
					games = extrairNomeJogo(e2, games);
					games = extrairLinkDetalhes(e2, games);
					games = extrairPercentualDesconto(e2, games);
					games = extrairPrecoOriginal(e2, games);
					games = extrairPrecoAtual(e2, games);
					//games = extrairTipoConteudo(e2, games);
					games = extrairPlataforma(e2, games);
					games = extrairLinkImagem(e2, games);


					lista.add(games);
				}

			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtil.logError("getGameData", PlaystationStoreRetrieveDataProcess.class.getCanonicalName(), e.getMessage());
			}

		}

		return lista;
	}

	private int getTotalPages() {
		return 89;
		/*
		 * LOG.info("Obtendo número geral de páginas"); int totalPages = 0;
		 * 
		 * try {
		 * 
		 * Document doc = Jsoup.connect(storeUri + "1000").get();
		 * 
		 * String[] membersOfPath = doc.location().split("/");
		 * ArrayUtils.reverse(membersOfPath); totalPages =
		 * Integer.parseInt(membersOfPath[0]);
		 * 
		 * } catch (Exception e) { LoggerUtil.logError("getTotalPages",
		 * PlaystationStoreRetrieveDataProcess.class.getCanonicalName(),
		 * e.getMessage());
		 * 
		 * } return totalPages;
		 */
	}
	
	private String extrairLinkDetalhes(Element e2, String games) {
		try { 
		games+= e2.select("a.ems-sdk-product-tile-link").first().absUrl("href").concat(";");

		} catch (Exception ex) {
			games += "SEM_LINK_DETALHE".concat(";");
		}
		return games;
	}

	private String extrairLinkImagem(Element e2, String games) {
		try {
			games += e2.getElementsByClass("psw-media-frame psw-fill-x psw-image psw-aspect-1-1").get(0).select("img").get(1).absUrl("src").concat(";");
		} catch (Exception ex) {
			games += "SEM_LINK_IMAGEM".concat(";");

		}
		return games;
	}

	private String extrairPlataforma(Element e2, String games) {
		try {
			games += e2.getElementsByClass("psw-p-x-3xs ems-sdk-product-tile-image__badge").text()
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
			games += e2.getElementsByClass("psw-body-2 psw-truncate-text-2 psw-p-t-2xs").text().concat(";");
		} catch (Exception ex) {
			games += "SEM_NOME_JOGO".concat(";");
		}
		return games;
	}

	private String extrairPrecoAtual(Element e2, String games) {
		try {
			games += e2.getElementsByClass("price").first().text().concat(";");
		} catch (Exception ex) {
			games += "SEM_PRECO_ATUAL".concat(";");
		}
		return games;
	}

	private String extrairPrecoOriginal(Element e2, String games) {
		try {
			games += e2.getElementsByClass("price price--strikethrough psw-m-l-xs").text().concat(";");
		} catch (Exception ex) {
			games += "SEM_PRECO_ORIGINAL".concat(";");
		}
		return games;
	}

	private String extrairPercentualDesconto(Element e2, String games) {
		try {
			games += e2.getElementsByClass("psw-body-2 discount-badge discount-badge--undefined").text().concat(";");
		} catch (Exception ex) {
			games += "SEM_PERCENTUAL_DESCONTO".concat(";");
		}
		return games;
	}

	

}
