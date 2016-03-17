package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = sessao.getPreco();
		switch (sessao.getEspetaculo().getTipo()) {
		case CINEMA:
		case SHOW:
			//quando estiver acabando os ingressos... 
			if(estaNosUltimos(sessao, 0.05)) { 
				preco = aumentaPreco(sessao, 0.10);
			}
			break;
		case BALLET:
			if(estaNosUltimos(sessao, 0.50)) { 
				preco = aumentaPreco(sessao, 0.20);
				
			} 
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = aumentaPreco(sessao, 0.10);
			}
			break;
		case ORQUESTRA:
			if(estaNosUltimos(sessao, 0.50)) {
				preco = aumentaPreco(sessao, 0.20);
			} 

			if(sessao.getDuracaoEmMinutos() > 60){
				//preco = aumentaPreco(sessao, 0.10);
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
			break;
		}
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal aumentaPreco(Sessao sessao, double porcentagemDeAumento) {
		return  sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(porcentagemDeAumento)));
	}

	private static boolean estaNosUltimos(Sessao sessao, Double porcentagemDeMaisCaros) {
		int ingressosDisponiveis = sessao.getTotalIngressos() - sessao.getIngressosReservados();
		double porcentagemDeIngressosDisponiveis = ingressosDisponiveis / sessao.getTotalIngressos().doubleValue();
		return porcentagemDeIngressosDisponiveis <= porcentagemDeMaisCaros;
	}

}