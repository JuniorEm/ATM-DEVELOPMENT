package br.umc.controller;

import br.com.dao.AtmDAO;
import br.umc.model.Account;

public class AtmController {
	private AtmDAO dao;
	
	public AtmController() {
		dao = new AtmDAO();
	}
	
	public Account verifyCustomerAccount(final Account account) {
		return dao.verifyCustomerAccount(account);
	}
}
