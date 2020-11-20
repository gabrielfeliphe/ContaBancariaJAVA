import controle.Controladora;

// Adicionar o nome do titular
// Refatorar nomes metodos

public class Principal { // Classe principal que invoca nossa controladora
	
	public static void main(String[] args) { // main que cria objeto controle onde invoca a ixibição do menu
		Controladora controle = new Controladora();
		controle.exibirMenu();
	}

}
