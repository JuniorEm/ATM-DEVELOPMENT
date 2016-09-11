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
import br.umc.view.option.OptionEnum;

public class AtmView {
	private Account account;
	private AtmController controller;
	private BufferedReader buffered;
	
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
				AtmUtil.showMessage("Número da conta inválido");
				navigate();
			}
			else openMenu();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Account requestAndVerifyPinAndAccountNumber() throws IOException {
		AtmUtil.showMessage("Insira o número da sua conta: ");
		final String accountNumber = buffered.readLine();
		AtmUtil.showMessage("Insira o número do pin: ");
		final String pinNumber = buffered.readLine();
		final Account account = new Account(accountNumber, pinNumber);
		return controller.verifyCustomerAccount(account);
	}
	
	private void openMenu() throws IOException {
		System.out.println(buildMenu());
		final String option = buffered.readLine();
		String screen = null;
		
		if (option.equals(OptionEnum.CONSULT.getCode())) {
			showBalance();
		} else if (option.equals(OptionEnum.WITHDRAW.getCode())) {
			buildWithDrawScreen();
		}
	}
	
	private String buildMenu() {
		return new StringBuilder()
		.append("\n---------------- MENU --------------")
		.append("\n1 - CONSULTA DE SALDO")
		.append("\n2 - SAQUE")
		.append("\n3 - DEPÓSITO")
		.append("\n4 - SAIR").toString();
	}
	
	private void showBalance() {
		System.out.println(new StringBuilder("O seu saldo é de: R$").append(executeConsultOperation())
				.append("\nPara sair digite 4, para voltar digite 6").toString());
		try {
			requestOption();
		} catch(final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void requestOption() throws IOException {
		final String option = buffered.readLine();
		
		if (option.equals(OptionEnum.EXIT.getCode()))
				System.exit(0);
		else if (option.equals(OptionEnum.BACK.getCode()))
			openMenu();
		else {
			AtmUtil.showMessage("OPÇÃO INVÁLIDA, TENTE NOVAMENTE");
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
				.append("\n3 - R$60").append("\t6 - CANCELAR TRANSAÇÃO")
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
		
		if (option.equals("6")) {
			openMenu();
		} else {
			final BigDecimal value = map.get(option);
			
			if (value == null) {
				AtmUtil.showMessage("OPÇÃO INVÁLIDA, TENTE NOVAMENTE");
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
			AtmUtil.showMessage("O caixa eletrônico está com poucas cédulas no momento, digite um valor menor");
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
