package controle;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.ContaBancaria;
import modelo.Validacoes;
import visualizacao.*;

public class Controladora {

	ArrayList<ContaBancaria> contasBanco = new ArrayList<ContaBancaria>(); // Arraylist que contem nossas
																			// contasBancarias
	// Variaveis globais, eu gosto que nem pastel simples mais gostoso;
	int idConta; // posi��o do Array, usei uma global pois � facil e pratico que nem p�o com
					// maionese
	boolean contaLogada; // armazena se uma conta foi iniciada para acessarmos e escrevermos seus dados,
							// que nem lista de chamada, o nome ta la mais nao sabemos quem �

	public void exibirMenu() {

		int escolhaMenu; // Armazena nossas escolhas, variavel bem versatil pois a reutilizamos, que nem
							// sacola de supermercado
		contaLogada = false;

		if (contasBanco.size() == 0) { // cria uma conta pra gente testar e tal o programa, esta dentro de um if pois
										// reinvocamos esse menu em outra ocasiao
			criaPrimeiraConta();
		}

		while (contaLogada == false) {
			escolhaMenu = InterfaceGrafica.menuContaDeslogada(); // Vamos a nossa interfaceGrafica caso estiver conta
																	// deslogada, armazenando no int
			switch (escolhaMenu) {
			case 0:
				contaLogada = logarConta(); // chamamos nossos m�todo tipo boolean e ele retorna pra contalogada
				break;
			case 1:// criar conta
				criarConta();
				break;
			case 2:// sair
				System.exit(0);
				break;
			}
		}

		do {
			escolhaMenu = InterfaceGrafica.qstOperacaoMenu(); // MENU COM BOT�ES :)
			switch (escolhaMenu) { // MUDAR NOMES TIRAR O CHAMA DO M�TODO
			case 0:
				iniciaSaldo(); 
				break;
			case 1:
				iniciaSaque();
				break;
			case 2:
				iniciaDeposito();
				break;
			case 3:
				iniciaExtratos();
				break;
			case 4:
				iniciaSairConta();
				break;
			}

		} while (escolhaMenu != 4);

	}

	private void criarConta() { // Cria��o da conta
		ContaBancaria contaUsuario = new ContaBancaria();
		int numeroConta = contasBanco.size() + 1;
		contaUsuario.setTitularDaConta(numeroConta);
		contaUsuario.setTipo(InterfaceGrafica.setTipoConta());
		contaUsuario.setNomeTitular(InterfaceGrafica.qstNomeTitular());
		contasBanco.add(contaUsuario);
		InterfaceGrafica.msgContaCriada(contasBanco.size());

	}

	private boolean logarConta() { // serve para logar a conta

		idConta = InterfaceGrafica.logarContaBanco();
		while (Validacoes.validaContaLogada(idConta, contasBanco) == false) {
			InterfaceGrafica.mostraMsgErroLogar();
			idConta = InterfaceGrafica.logarContaBanco();
		}

		InterfaceGrafica.exibirDadosDaConta(contasBanco.get(idConta).getTitularDaConta(),
				contasBanco.get(idConta).getTipo(), contasBanco.get(idConta).getSaldo(),contasBanco.get(idConta).getNomeTitular());
		return true;
	}

	private void iniciaSairConta() { // Sai da conta ou do programa
		int escolha = InterfaceGrafica.qstSair();
		switch (escolha) {
		case 0:
			deslogarConta();
			break;
		case 1:
			System.exit(0);
		}
	}

	private void deslogarConta() { // desloga a conta
		contaLogada = false; // Setamos a variavel global para falso denovo e reinvocamos o menu, algo bem
								// pratico que nem miojo
		exibirMenu();
	}

	private void criaPrimeiraConta() { // Armazenamos a primeira conta no array de contas
		ContaBancaria contaUsuario = new ContaBancaria();
		int numeroConta = contasBanco.size() + 1;
		contaUsuario.setTitularDaConta(numeroConta);
		contaUsuario.setTipo(1); // vai ser conta poupan�a;
		contaUsuario.setNomeTitular("Administrador");
		contasBanco.add(contaUsuario);
		InterfaceGrafica.msgContaCriada(contasBanco.size());
	}

	public void iniciaSaque() { // Chamamos a implenta��o do saque

		int saqueValidado;
		String infoSaque[] = InterfaceGrafica.solicitarInformacoesSaque();
		saqueValidado = Validacoes.validaValorSaque(infoSaque, contasBanco.get(idConta));

		if (saqueValidado < 3) {
			JOptionPane.showMessageDialog(null, "Houve um erro com seu saque, por favor refa�a a opera��o !");
			saqueValidado = Validacoes.validaValorSaque(infoSaque, contasBanco.get(idConta));
			InterfaceGrafica.msgErroSaque(saqueValidado);
		} else {
			contasBanco.get(idConta).sacar(Double.parseDouble(infoSaque[0]), infoSaque[1]);
			InterfaceGrafica.msgSaqueConcluido(contasBanco.get(idConta).getSaldo());
		}
	}

	public void iniciaDeposito() { // Chamamos as implementa��es do deposito
		int depositoValidado;
		String infoDeposito[] = InterfaceGrafica.solicitarInformacoesDeposito(idConta);
		depositoValidado = Validacoes.validaValorDeposito(infoDeposito, contasBanco);

		while (depositoValidado < 1) {
			JOptionPane.showMessageDialog(null, "Houve um erro com o deposito, por favor refa�a a opera��o !");
			infoDeposito = InterfaceGrafica.solicitarInformacoesDeposito(idConta);
			depositoValidado = Validacoes.validaValorDeposito(infoDeposito, contasBanco);
			InterfaceGrafica.msgErroDeposito();
		}
		double valor = Double.parseDouble(infoDeposito[1]);
		contasBanco.get(Integer.parseInt(infoDeposito[0])).depositar(valor, infoDeposito[2], infoDeposito[0]);
		InterfaceGrafica.msgDepositoConcluido(contasBanco.get(Integer.parseInt(infoDeposito[0])));
	}

	public void iniciaSaldo() { // chama interface grafica so para mostrar o saldo
		InterfaceGrafica.exibirSaldo(contasBanco.get(idConta).consultarSaldo());
	}

	public void iniciaExtratos() { // Chamamos o extrato o mesmo possui varias subOp��es
		int operacao = InterfaceGrafica.qstOperExtrato();
		switch (operacao) {
		case 0:
			InterfaceGrafica.exibirExtratoCompleto(contasBanco.get(idConta).gerarExtrato());
			break;
		case 1:
			InterfaceGrafica.exibirExtratoDeDepositos(contasBanco.get(idConta).gerarExtratoDepositos());
			break;
		case 2:
			InterfaceGrafica.exibirExtratoDeSaques(contasBanco.get(idConta).gerarExtratoSaques());
			break;
		}
	}

}
