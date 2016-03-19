package br.com.caelum.agiletickets.acceptance.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReservasPage {

	private static final String BASE_URL = "http://localhost:8080";
	private final WebDriver driver;

	public ReservasPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void abreSessaoComCincoIngressosRestantes() {
		driver.get(BASE_URL+"/sessao/16");
	}

	public void reservaUmIngresso() {
		driver.findElement(By.id("qtde")).sendKeys("1");
		driver.findElement(By.tagName("form")).submit();
	}

	public void verificaAcrescimoDeDezPorcento() {
		String mensagem = driver.findElement(By.id("message")).getText();
		assertThat(mensagem, is("Sessão reservada com sucesso por R$ 55,00"));
	}

}
