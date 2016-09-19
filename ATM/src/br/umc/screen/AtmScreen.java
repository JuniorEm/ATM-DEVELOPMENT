package br.umc.screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.umc.dao.AccountDAO;
import br.umc.io.factory.BufferedReaderFactory;
import br.umc.entity.Account;
import br.umc.util.AtmUtil;

/**
 * Tela do ATM.
 * @author 12141500872
 */
public class AtmScreen {
    
	/**
	 * Um objeto conta.
	 */
	private Account account;
	
        /**
         * Data Access Object de Account.
         */
        private AccountDAO dao;
        
	/**
	 * BufferedReader para entrada de dados.
	 */
	private BufferedReader buffered;
	
	/**
	 * Opcao para consulta.
	 */
	private static final String CONSULT = "1";
	
	/**
	 * Opcao para saque.
	 */
	private static final String WITHDRAW = "2";
	
	/**
	 * Opcao para deposito.
	 */
	private static final String DEPOSIT = "3";
	
	/**
	 * Opcao para saida.
	 */
	private static final String EXIT = "4";
	
	/**
	 * Opcao para voltar.
	 */
	private static final String BACK = "6";
	
	public AtmScreen() {
                dao = new AccountDAO();
		buffered = new BufferedReaderFactory().getBufferedReader();
		account = new Account();
	}
	
	/**
	 * Metodo responsavel por dar inicio ao sistema.
	 */
	public void openTerminal() {
		AtmUtil.showMessage("Bem vindo");
		navigate();
	}
	
	/**
	 * Metodo responsavel por efetuar a navegacao do sistema.
	 */
	private void navigate() {
		try {
			account = requestAndVerifyPinAndAccountNumber();
			
			if (account == null) {
				AtmUtil.showMessage("Numero da conta/pin invalido");
				navigate();
			}
			else openMenu();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Verifica o numero da conta e o numero do pin.
	 * @return O objeto conta.
	 * @throws IOException Um erro lan�ado pelo uso do BufferedReader.
	 */
	private Account requestAndVerifyPinAndAccountNumber() throws IOException {
		AtmUtil.showMessage("Insira o numero da sua conta: ");
		final String accountNumber = buffered.readLine();
		AtmUtil.showMessage("Insira o numero do pin: ");
		final String pinNumber = buffered.readLine();
		final Account account = new Account(accountNumber, pinNumber);
		return dao.verifyCustomerAccount(account);
	}
	
	/**
	 * Abre o menu.
	 * @throws IOException Erro lan�ado pelo uso do BufferedReader.
	 */
	private void openMenu() throws IOException {
		System.out.println(buildMenu());
		final String option = buffered.readLine();
		
		switch (option) {
		case CONSULT:
			showBalance();
			break;
		case WITHDRAW:
			buildWithDrawScreen();
			break;
		case DEPOSIT:
			buildDepositScreen();
			break;
		case EXIT:
			System.exit(0);
			break;
		default: {
			showInvalidOptionMessage();
			openMenu();
		}
		}
	}
	
	/**
	 * Metodo que exibe uma mensagem de opcao invalida.
	 */
	private void showInvalidOptionMessage() {
		AtmUtil.showMessage("OPCAO INVALIDA");
	}
	
	/**
	 * Constroi a tela de menu.
	 * @return O menu construido.
	 */
	private String buildMenu() {
		return new StringBuilder()
		.append("\n---------------- MENU --------------")
		.append("\n1 - CONSULTA DE SALDO")
		.append("\n2 - SAQUE")
		.append("\n3 - DEPOSITO")
		.append("\n4 - SAIR").toString();
	}
	
	/**
	 * Mostra o saldo do individuo.
	 */
	private void showBalance() {
		System.out.println(new StringBuilder("O seu saldo e de: R$").append(executeConsultOperation())
				.append("\nPara sair digite 4, para voltar digite 6").toString());
		try {
			requestOption();
		} catch(final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Metodo que pergunta se o usuario deseja sair ou continuar.
	 * @throws IOException
	 */
	private void requestOption() throws IOException {
		final String option = buffered.readLine();
		
		if (option.equals(EXIT))
				System.exit(0);
		else if (option.equals(BACK))
			openMenu();
		else {
			showInvalidOptionMessage();
			requestOption();
		}
	}
	
	/**
	 * Executa a operacao de consulta.
	 * @return O valor da conta do individuo.
	 */
	private BigDecimal executeConsultOperation() {
		return dao.consult(account);
	}
	
	/**
	 * Constroi a tela de saque.
	 */
	private void buildWithDrawScreen() {
		System.out.println(new StringBuilder()
				.append("------------ Withdrawal Menu ---------")
				.append("\n1 - R$20").append("\t4 - R$100")
				.append("\n2 - R$40").append("\t5 - R$200")
				.append("\n3 - R$60").append("\t6 - CANCELAR TRANSACAO")
				.append("\nEscolha uma quantia de saque: ").toString());
		
		try {
			withDraw();
		} catch(final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Implementa a l�gica de saque.
	 * @throws IOException Uma exce��o do BufferedReader.
	 */
	private void withDraw() throws IOException {
		final Map<String, BigDecimal> map = new HashMap<>();
		map.put("1", new BigDecimal("20"));
		map.put("2", new BigDecimal("40"));
		map.put("3", new BigDecimal("60"));
		map.put("4", new BigDecimal("100"));
		map.put("5", new BigDecimal("200"));
		
		final String option = buffered.readLine();
		
		if (option.equals(BACK)) {
			openMenu();
		} else {
			final BigDecimal value = map.get(option);
			
			if (value == null) {
				showInvalidOptionMessage();
				withDraw();
			} else {
				final String result = dao.withDraw(account, value);
				if (verifyWithDraw(result))
					AtmUtil.showMessage("Saque realizado com sucesso, n�o esque�a de retirar o seu dinheiro!");
				openMenu();
			}
		}
	}
	
	/**
	 * Verifica se o individuo esta apto para saque.
	 * @param value O valor da operacao.
	 * @return Um booleano.
	 */
	private boolean verifyWithDraw(final String value) {
		if (value.equals("1")) {
			AtmUtil.showMessage("O caixa eletronico esta com poucas cedulas no momento, digite um valor menor");
			buildWithDrawScreen();
			return false;
		} else if (value.equals("2")) {
			AtmUtil.showMessage("Saldo insuficiente, digite um valor abaixo");
			buildWithDrawScreen();
			return false;
		}
		return true;
	}
		
	/**
	 * Constroi a tela de deposito.
	 */
	private void buildDepositScreen() {
		System.out.println(new StringBuilder().append("---------DEPOSIT MENU------")
		.append("\nDigite o valor que deseja depositar ou digite 6 para cancelar"));
		
		try {
			deposit();
		} catch(final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Metodo que realiza o deposito e sua logica.
	 * @throws IOException BufferedReader pode disparar uma excecao de valor invalido.
	 */
	private void deposit() throws IOException {
		final String option = buffered.readLine();
		
		if (option.equals(BACK))
			openMenu();
		else {
			final BigDecimal value = new BigDecimal(option);
			dao.deposit(account, value);
			AtmUtil.showMessage("Valor depositado com sucesso!");
			openMenu();
		}
	}
}
