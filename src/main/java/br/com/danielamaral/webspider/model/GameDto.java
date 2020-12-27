package br.com.danielamaral.webspider.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String page;
	private String detailsLink;
	private String disccountPercent;
	private String originalPrice;
	private String actualPrice;
	private String name;
	private String contentType;
	private String console;
	private String imageLink;
	private Date createdTime=new Date();

	public Long getId() {
		return id;
	}


	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getDetailsLink() {
		return detailsLink;
	}

	public void setDetailsLink(String detailsLink) {
		this.detailsLink = detailsLink;
	}

	public String getDisccountPercent() {
		return disccountPercent;
	}

	public void setDisccountPercent(String disccountPercent) {
		this.disccountPercent = disccountPercent;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public static List<GameDto> parseGameDtoList(List<Game> list){
		List<GameDto> lista = new ArrayList<>();
		
		for (Game g: list) {
			lista.add(parseGameDto(g));
		}
		
		return lista;
	}

	public static GameDto parseGameDto(Game game) {
		GameDto dto = new GameDto();
		dto.setActualPrice(game.getActualPrice());
		dto.setConsole(game.getConsole());
		dto.setContentType(game.getContentType());
		dto.setCreatedTime(game.getCreatedTime());
		dto.setDetailsLink(game.getDetailsLink());
		dto.setDisccountPercent(game.getDisccountPercent());
		dto.setImageLink(game.getImageLink());
		dto.setName(game.getName());
		dto.setOriginalPrice(game.getOriginalPrice());
		dto.setPage(game.getPage());
		
		return dto;
	}
}
