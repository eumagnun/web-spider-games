package br.com.danielamaral.webspider.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Game implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@ApiModelProperty(value = "Identificador único da captura de informações do game")
	private Long id;
	
	@ApiModelProperty(value = "Página a partir da qual a o game foi obtido")
	private String page;
	
	@ApiModelProperty(value = "Link externo com detalhes do game")
	private String detailsLink;
	
	@ApiModelProperty(value = "Percentual de desconto na data da captura de informações")
	private String disccountPercent;
	
	@ApiModelProperty(value = "Preço original do game na ocasião da captura de informações")
	private String originalPrice;
	
	@ApiModelProperty(value = "Preço atual do game na ocasião da captura de informações")
	private String actualPrice;
	
	@ApiModelProperty(value = "Nome do game")
	private String name;
	
	@ApiModelProperty(value = "Tipo de conteúdo do game")
	private String contentType;
	
	@ApiModelProperty(value = "Console do game")
	private String console;
	
	@ApiModelProperty(value = "Link externo com imagem do game")
	private String imageLink;
	
	@ApiModelProperty(value = "Data de captura de informações do game")
	@Temporal(TemporalType.TIMESTAMP)
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

	
	public interface GameResume {

		  String getName();
		  String getConsole();
		  String getActualPrice();
		  String getCreatedTime();
		}
	
	public static Game parseGame(GameDto dto) {
		Game g = new Game();
		g.setActualPrice(dto.getActualPrice());
		g.setConsole(dto.getConsole());
		g.setContentType(dto.getContentType());
		g.setCreatedTime(dto.getCreatedTime());
		g.setDetailsLink(dto.getDetailsLink());
		g.setDisccountPercent(dto.getDisccountPercent());
		g.setImageLink(dto.getImageLink());
		g.setName(dto.getName());
		g.setOriginalPrice(dto.getOriginalPrice());
		g.setPage(dto.getPage());
		
		return g;
	}
}
