package modelo;

import java.util.ArrayList;

public class Validacoes { // CLASSE DE VALIDAÇÕES, SUA PROPOSTA É SIMPLES SER UMA CLASSE DE VALIDAÇOES

	public static boolean validaContaLogada(int idConta, ArrayList<ContaBancaria> contasBanco) {
		if (idConta >= contasBanco.size()) { // metodo simples onde compara se existe o indice calculando o tamanho do
												// array
			return false;
		} else {
			return true;
		}
	}

	public static int validaValorSaque(String[] dadosSaque, ContaBancaria contaBancaria) {
		int codOp = 0;
		double valorSaque = -Double.parseDouble(dadosSaque[0]);
			
		if (-1000 > (valorSaque+contaBancaria.getSaldo())) {
			codOp = 1;
		} else if (contaBancaria.getSaldo() <= -1000) {
			codOp = 2;
		} else {
			codOp = 3;
		}

		return codOp;
	}

	public static int validaValorDeposito(String[] infoDeposito, ArrayList<ContaBancaria> contasBanco) {
		int codOp = 1;
		
		if (contasBanco.size() == 1 && Integer.parseInt(infoDeposito[0]) != 0) { 
			codOp = 0;
		} else if (contasBanco.size() < Integer.parseInt(infoDeposito[0])) {
			codOp = 0;
		}
		return codOp;
	}

}
