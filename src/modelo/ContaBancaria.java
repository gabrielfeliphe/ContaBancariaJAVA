package modelo;

import java.util.ArrayList;

public class ContaBancaria {

//Atributos

	private int numeroConta; // numero da conta
	private int tipo; // (1-conta poupança, 2-conta corrente.)
	private double saldo;
	private String nomeTitular;

	private ArrayList<Movimentacao> listaDeMovimentacao = new ArrayList<Movimentacao>(); // MOVIMENTAÇÕES PERTENCENTES

//Métodos

	public void depositar(double valor, String data, String contaDepositante) {
		saldo = saldo + valor; 

		Deposito novoDeposito = new Deposito();
		novoDeposito.setTipo(Integer.parseInt(contaDepositante));
		novoDeposito.setValor(valor);
		novoDeposito.setData(data);
		listaDeMovimentacao.add(novoDeposito);
	}

	public void sacar(double valorSaque, String data) {
		saldo = saldo - valorSaque;

		Saque novoSaque = new Saque();
		novoSaque.setTipo(numeroConta - 1);
		novoSaque.setValor(-valorSaque);
		novoSaque.setData(data);
		listaDeMovimentacao.add(novoSaque);
	}

	public double consultarSaldo() {

		double saldoConta;
		saldoConta = getSaldo();
		return saldoConta;
	}

	public String gerarExtrato() { // pega o saque e deposito
		String extratoCompleto = "----- Extrato de Depositos -----\n";

		extratoCompleto += gerarExtratoDepositos();
		extratoCompleto += "----- Extrato de Saques ----- \n";
		extratoCompleto += gerarExtratoSaques();

		System.out.println("Tamanho do aray" + listaDeMovimentacao.size());

		return extratoCompleto;
	}

	public String gerarExtratoDepositos() {
		String depositosExtrato = "";

		for (Movimentacao depositos : listaDeMovimentacao) {

			if(depositos.getClass().getName() == "modelo.Deposito") { //CARA ISSO ME TIROU UM TEMPÃO XOREII 

			depositosExtrato += "Data : " + depositos.getData() + "\n";
			depositosExtrato += "Origem Deposito nº: " + depositos.getTipo() + "\n";
			depositosExtrato += "Valor : " + depositos.getValor() + "\n";}
		}

		return depositosExtrato;
	}

	public String gerarExtratoSaques() {
		String saquesExtrato = "";

		for (Movimentacao Saques : getListaDeMovimentacao()) {

			if (Saques.getClass().getName() == "modelo.Saque") { // ALELUIA IRMÃO É PRA GLORIFICAR DE PÉ, FOI MAIS DE 3
																// HORAS SO ATE ACHAR ISSO AQUI

				saquesExtrato += "Data : " + Saques.getData() + "\n";
				saquesExtrato += "Origem Saques nº: " + Saques.getTipo() + "\n";
				saquesExtrato += "Valor : " + Saques.getValor() + "\n";
			}

		}

		return saquesExtrato;
	}

// ABAIXO OS GETTERS E SETTERS

	public int getTitularDaConta() {
		return numeroConta;
	}

	public void setTitularDaConta(int titularDaConta) {
		this.numeroConta = titularDaConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Movimentacao> getListaDeMovimentacao() {
		return listaDeMovimentacao;
	}

	public void setListaDeMovimentacao(ArrayList<Movimentacao> listaDeMovimentacao) {
		this.listaDeMovimentacao = listaDeMovimentacao;
	}
	
	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
}
