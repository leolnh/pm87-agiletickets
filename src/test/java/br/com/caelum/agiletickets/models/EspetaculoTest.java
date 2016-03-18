package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	
	@Test
	public void deveCadastrarONumeroCertoDeSessoesSemanais() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setTipo(TipoDeEspetaculo.TEATRO);
		
		LocalDate dataInicio = new LocalDate(2011, 1, 9);
		LocalDate dataFinal = new LocalDate(2011, 1, 24);
		LocalTime horario = new LocalTime(17, 0);
		
		espetaculo.criaSessoes(dataInicio, dataFinal, horario, Periodicidade.SEMANAL);
		
		Assert.assertEquals(3, espetaculo.getSessoes().size());
		
	}
	
	@Test
	public void deveCadastrarONumeroCertoDeSessoesDiarias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setTipo(TipoDeEspetaculo.TEATRO);
		
		LocalDate dataInicio = new LocalDate(2011, 1, 9);
		LocalDate dataFinal = new LocalDate(2011, 1, 23);
		LocalTime horario = new LocalTime(17, 0);
		espetaculo.criaSessoes(dataInicio, dataFinal, horario, Periodicidade.DIARIA);
		Assert.assertEquals(15, espetaculo.getSessoes().size());
		
	}
	
	@Test
	public void deveCadastrarAsSessoesSemanais() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setTipo(TipoDeEspetaculo.TEATRO);
		
		LocalDate dataInicio = new LocalDate(2011, 1, 9);
		LocalDate dataFinal = new LocalDate(2011, 1, 24);
		LocalTime horario = new LocalTime(17, 0);
		
		espetaculo.criaSessoes(dataInicio, dataFinal, horario, Periodicidade.SEMANAL);
		
		Sessao primeira = espetaculo.getSessoes().get(0);
		DateTime dataPrimeira = new DateTime(2011, 1, 9, 17, 0);
		Assert.assertEquals(dataPrimeira, primeira.getInicio());
		
		Sessao segunda = espetaculo.getSessoes().get(1);
		DateTime dataSegunda = new DateTime(2011, 1, 16, 17, 0);
		Assert.assertEquals(dataSegunda, segunda.getInicio());
		
		Sessao terceira = espetaculo.getSessoes().get(2);
		DateTime dataTerceira = new DateTime(2011, 1, 23, 17, 0);
		Assert.assertEquals(dataTerceira, terceira.getInicio());
	}
	
	@Test
	public void deveCadastrarAsSessoesDiaria() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setTipo(TipoDeEspetaculo.TEATRO);
		
		LocalDate dataInicio = new LocalDate(2011, 1, 9);
		LocalDate dataFinal = new LocalDate(2011, 1, 11);
		LocalTime horario = new LocalTime(17, 0);
		
		espetaculo.criaSessoes(dataInicio, dataFinal, horario, Periodicidade.DIARIA);
		
		Sessao primeira = espetaculo.getSessoes().get(0);
		DateTime dataPrimeira = new DateTime(2011, 1, 9, 17, 0);
		Assert.assertEquals(dataPrimeira, primeira.getInicio());
		
		Sessao segunda = espetaculo.getSessoes().get(1);
		DateTime dataSegunda = new DateTime(2011, 1, 10, 17, 0);
		Assert.assertEquals(dataSegunda, segunda.getInicio());
		
		Sessao terceira = espetaculo.getSessoes().get(2);
		DateTime dataTerceira = new DateTime(2011, 1, 11, 17, 0);
		Assert.assertEquals(dataTerceira, terceira.getInicio());
	}
	
}
