package br.com.caelum.agiletickets.acceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.agiletickets.acceptance.page.ReservasPage;

public class ReservaTest {

	private WebDriver browser;
	private ReservasPage reservas;
	
	@Before
	public void setUp() throws Exception {
		browser = new FirefoxDriver();
		reservas = new ReservasPage(browser);
	}
	
	@After
	public void tearDown() {
		browser.close();
	}
	
	@Test
	public void cobraDezPorcentoAMaisParaUltimosCincoIngressos() {
		reservas.abreSessaoComCincoIngressosRestantes();
		reservas.reservaUmIngresso();
		reservas.verificaAcrescimoDeDezPorcento();
	}
}
