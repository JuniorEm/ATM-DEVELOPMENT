package br.umc.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.umc.controller.AtmController;
import br.umc.io.factory.BufferedReaderFactory;
import br.umc.model.Account;
import br.umc.util.AtmUtil;

public class AtmView {
	private Account account;
	private AtmController controller;
	private BufferedReader buffered;
	private static final String CONSULT = "1";
	private static final String WITHDRAW = "2";
	private static final String DEPOSIT = "3";
	private static final String EXIT = "4";
	private static final String BACK = "6";
	
	public AtmView() {
		controller = new AtmController();
		buffered = new BufferedReaderFactory().getBufferedReader();
		account = new Account();
	}
	
	public void openTerminal() {
		AtmUtil.showMessage("Bem vindo");
		navigate();
	}
	
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
	
	private Account requestAndVerifyPinAndAccountNumber() throws IOException {
		AtmUtil.showMessage("Insira o numero da sua conta: ");
		final String accountNumber = buffered.readLine();
		AtmUtil.showMessage("Insira o numero do pin: ");
		final String pinNumber = buffered.readLine();
		final Account account = new Account(accountNumber, pinNumber);
		return controller.verifyCustomerAccount(account);
	}
	
	private void openMenu() throws IOException {
		System.out.println(buildMenu());
		final String option = buffered.readLine();
		String screen = null;
		
		switch(option) {
			case CONSULT: showBalance();
			break;
			case WITHDRAW: buildWithDrawScreen();
			break;
			case DEPOSIT: buildDepositScreen();
			break;
			case EXIT: System.exit(0);
			break;
			default: { showInvalidOptionMessage();
						openMenu();
			}
		}

	}
	
	private void showInvalidOptionMessage() {
		AtmUtil.showMessage("OPCAO INVALIDA");
	}
	
	private String buildMenu() {
		return new StringBuilder()
		.append("\n---------------- MENU --------------")
		.append("\n1 - CONSULTA DE SALDO")
		.append("\n2 - SAQUE")
		.append("\n3 - DEPOSITO")
		.append("\n4 - SAIR").toString();
	}
	
	private void showBalance() {
		System.out.println(new StringBuilder("O seu saldo e de: R$").append(executeConsultOperation())
				.append("\nPara sair digite 4, para voltar digite 6").toString());
		try {
			requestOption();
		} catch(final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
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
	
	private BigDecimal executeConsultOperation() {
		return controller.consult(account);
	}
	
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
				final String result = controller.withDraw(account, value);
				verifyWithDraw(result);
				openMenu();
			}
		}
	}
	
	private void verifyWithDraw(final String value) {
		if (value.equals("1")) {
			AtmUtil.showMessage("O caixa eletronico esta com poucas cedulas no momento, digite um valor menor");
			buildWithDrawScreen();
		} else if (value.equals("2")) {
			AtmUtil.showMessage("Saldo insuficiente, digite um valor abaixo");
			buildWithDrawScreen();
		}
	}
		
	private String buildDepositScreen() {
		return null;
	}
	
}
