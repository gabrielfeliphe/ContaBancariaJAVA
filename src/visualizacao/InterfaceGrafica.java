package visualizacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JOptionPane;

import modelo.ContaBancaria;
import modelo.Movimentacao;

public class InterfaceGrafica {

	public static String[] solicitarInformacoesDeposito(int idConta) { // � um vetor String pois pode armazenar mais dados e fazemos a tratativa

		String infoDeposito[] = new String[3];
		boolean validaDado = false;

		String[] options = { "Favorecido", "Terceiros" }; // 0,1

		int selecao = JOptionPane.showOptionDialog(null, "Selecione a op��o de deposito desejada", "Bank System v2",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		if (selecao == 0) {
			infoDeposito[0] = Integer.toString(idConta);
		} else {
			infoDeposito[0] = JOptionPane.showInputDialog("Digite o n�mero da conta");
			validaDado = validaSaqueDeposito(infoDeposito[0]);

			if (Integer.parseInt(infoDeposito[0]) != 0) {
				while (validaDado == false) {
					JOptionPane.showMessageDialog(null,
							"Valores negativos n�o s�o aceitos no n�mero da conta ou letras");
					infoDeposito[0] = JOptionPane.showInputDialog("Digite o n�mero da conta");
					validaDado = validaSaqueDeposito(infoDeposito[0]);
				}
			}
		}

		infoDeposito[1] = JOptionPane.showInputDialog("Digite o valor desejado");
		validaDado = validaSaqueDeposito(infoDeposito[1]);
		while (validaDado == false) {
			JOptionPane.showMessageDialog(null, "Valores negativos n�o s�o aceitos no n�mero da conta ou letras");
			infoDeposito[1] = JOptionPane.showInputDialog("Digite o n�mero da conta");
			validaDado = validaSaqueDeposito(infoDeposito[1]);
		}
		infoDeposito[2] = obterData();

		return infoDeposito;
	}

	public static String[] solicitarInformacoesSaque() { // pegamos tudo e jogamos em um vetor, n�o se preucupe o mesmo
															// � processado na classe
															// Validacoes onde ele ira pegar as posicoes do vetor e
															// processa-las adequadamente

		String infoSaque[] = new String[2];
		boolean validaDado = false;

		infoSaque[0] = JOptionPane.showInputDialog("Digite o valor desejado");
		validaDado = validaSaqueDeposito(infoSaque[0]);
		while (validaDado == false) {
			JOptionPane.showMessageDialog(null, "Valores negativos e letras n�o sao aceitos");
			infoSaque[0] = JOptionPane.showInputDialog("Digite o n�mero da conta");
			validaDado = validaSaqueDeposito(infoSaque[0]);
		}
		infoSaque[1] = obterData();

		System.out.println("Teste :    " + Arrays.toString(infoSaque)); // teste de output do programador :)

		return infoSaque;

	}

	public static void exibirSaldo(double saldoConta) {
		JOptionPane.showMessageDialog(null, "Saldo R$: " + saldoConta);
	}

	public static void exibirDadosDaConta(int titular, int tipo, double saldoConta, String nomeTitular) {
		JOptionPane.showMessageDialog(null, "Bem vindo(a), "+nomeTitular+"\nConta N�: " + (titular-1) + "\nTipo: " + tipo + "\nSaldo R$:" + saldoConta);
	}

	public static void exibirExtratoCompleto(String extratoCompleto) {
		JOptionPane.showMessageDialog(null, extratoCompleto);
	}

	public static void exibirExtratoDeSaques(String extratoSaques) {
		JOptionPane.showMessageDialog(null, extratoSaques);
	}

	public static void exibirExtratoDeDepositos(String extratoDepositos) {
		JOptionPane.showMessageDialog(null, extratoDepositos);
	}

	public static int qstOperacaoMenu() {

		String[] options = { "Consultar Saldo", "Sacar", "Depositar", "Extratos", "Sair" }; // 0,1,2,3,4

		int selecao = JOptionPane.showOptionDialog(null, "Selecione a op��o desejada", "Bank System v2",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		return selecao;

	}

	public static String solicitaContaUsuario() {
		String contaUser = JOptionPane.showInputDialog("Digite o usuario");
		return contaUser;
	}
	// MUDAR NOME PARA SABER A FUN��O CONDIZENTE
	private static boolean validaSaqueDeposito(String validaNum) { // valida��o basica propria de nossa
																			// Interface grafica
		boolean checkNumero = true;

		try { // BLOCO BOBCAT DIGO TRY-CATCH VALIDA SE � NUMERO OU LETRA INSIRIDO NA MINHA
				// STRING FAZ TUDO
			Double.parseDouble(validaNum);
		} catch (NumberFormatException e) {
			checkNumero = false;
		}

		if (checkNumero == true) {
			if (Double.parseDouble(validaNum) <= 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public static String obterData() { // mudar nome m�todo para um condizente

		Calendar dataAtual = Calendar.getInstance();
		Integer ano = dataAtual.get(Calendar.YEAR);
		Integer mes = dataAtual.get(Calendar.MONTH);
		Integer diaDoMes = dataAtual.get(Calendar.DAY_OF_MONTH);

		if (mes == 12) {
			mes = 1;
		} else {
			mes += 1;
		}

		String dataHoje = diaDoMes + "/" + mes + "/" + ano;

		return dataHoje;
	}

	public static int menuContaDeslogada() { // Sele��o basica com bot�es modificados, usaremos bastante nesse programa pois eu gosto igual chocolate

		String[] options = { "Logar", "Criar Conta", "Sair" }; // 0,1,2

		int selecao = JOptionPane.showOptionDialog(null, "Selecione a op��o desejada", "Bank System v2",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		return selecao;

	}

	public static int logarContaBanco() {
		int idConta;
		idConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta"));
		while (idConta < 0) {
			JOptionPane.showMessageDialog(null, "Erro! o id da conta n�o pode ser negativo!");
			idConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta"));
		}

		return idConta;
	}

	public static void mostraMsgErroLogar() {
		JOptionPane.showMessageDialog(null, "Erro a conta n�o existe!");
	}

	public static void msgContaCriada(int size) {
		JOptionPane.showMessageDialog(null, "Parab�ns a conta foi criada o n�mero �: " + (size - 1));
	}

	public static int setTipoConta() {
		String[] options = { "Poupan�a", "Corrente" }; // 0,1 +1

		int selecao = JOptionPane.showOptionDialog(null, "Selecione a op��o desejada", "Bank System v2",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		return selecao + 1;

	}

	public static void msgErroSaque(int op) {
		String msgErro = "";
		switch (op) {
		case 1:
			msgErro = "Erro n�o foi possivel completar a transa��o devido ao seu saldo ser inferior ao saque!";
			break;
		case 2:
			msgErro = "Erro n�o foi possivel completar a trasa��o devido a mesma ultrapassar o limite!";
			break;
		}
		JOptionPane.showMessageDialog(null, msgErro);
	}

	public static void msgSaqueConcluido(double saldo) {
		JOptionPane.showMessageDialog(null, "Seu saque foi feito com sucesso seu novo saldo � de R$: " + saldo);
	}

	public static void msgErroDeposito() {

		JOptionPane.showMessageDialog(null,
				"Erro n�o foi possivel completar a transa��o devido a conta ser inexistente ou incorreta!");
	}

	public static void msgDepositoConcluido(ContaBancaria contaBancaria) {
		int size = contaBancaria.getListaDeMovimentacao().size() - 1;

		String comprovante = "Data" + contaBancaria.getListaDeMovimentacao().get(size).getData() + "\n";
		comprovante += "Beneficiario conta n�: " + contaBancaria.getListaDeMovimentacao().get(size).getTipo() + "\n";
		comprovante += "Valor R$: " + contaBancaria.getListaDeMovimentacao().get(size).getValor();

		JOptionPane.showMessageDialog(null, "Seu deposito foi feito com sucesso ! o comprovante ser� gerado");
		JOptionPane.showMessageDialog(null, "Favorecido : " + comprovante);
	}

	public static int qstOperExtrato() {

		String[] options = { "Extrato Completo", "Extrato de Depositos", "Extrato de Saques" }; // 0,1,2

		int selecao = JOptionPane.showOptionDialog(null, "Selecione a op��o de extrato que deseja", "Bank System v2",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		return selecao;
	}

	public static int qstSair() {
		String[] options = { "Sair da Conta", "Sair do Bank System" }; // 0,1

		int selecao = JOptionPane.showOptionDialog(null, "Selecione a op��o", "Bank System v2",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		return selecao;
	}

	public static String qstNomeTitular() {
		String nomeTitular = JOptionPane.showInputDialog("Digite o nome do titular da conta");
		return nomeTitular;
	}

}
