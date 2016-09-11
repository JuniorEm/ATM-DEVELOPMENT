package br.umc.view;

import java.io.BufferedReader;
import java.io.IOException;

import br.umc.controller.AtmController;
import br.umc.io.factory.BufferedReaderFactory;
import br.umc.model.Account;
import br.umc.util.AtmUtil;
import br.umc.view.option.OptionEnum;

public class AtmView {
	private static final String EXIT_OPTION = "4";
	private AtmController controller;
	private BufferedReader buffered;
	
	public AtmView() {
		controller = new AtmController();
		buffered = new BufferedReaderFactory().getBufferedReader();
	}
	
	public void openTerminal() {
		AtmUtil.showMessage("Bem vindo");
		navigate();
	}
	
	private void navigate() {
		try {
			final Account found = requestAndVerifyPinAndAccountNumber();
			
			if (found == null) {
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
		System.out.println(getMenuBuild());
		final String option = buffered.readLine();
		OptionEnum.valueOf(option);
	}
	
	private String getMenuBuild() {
		return new StringBuilder()
		.append("---------------- MENU --------------")
		.append("\n1 - CONSULTA DE SALDO")
		.append("\n2 - SAQUE")
		.append("\n3 - DEPÓSITO")
		.append("\n4 - SAIR").toString();
	}
	
}
