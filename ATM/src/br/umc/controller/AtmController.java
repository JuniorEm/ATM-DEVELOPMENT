package br.umc.controller;

import java.math.BigDecimal;

import br.umc.dao.AtmDAO;
import br.umc.model.Account;

public class AtmController {
	private AtmDAO dao;
	
	public AtmController() {
		dao = new AtmDAO();
	}
	
	public Account verifyCustomerAccount(final Account account) {
		return dao.verifyCustomerAccount(account);
	}
	
	public BigDecimal consult(final Account account) {
		return dao.consult(account);
	}
	
	public String withDraw(final Account account, final BigDecimal value) {
		return dao.withDraw(account, value);
	}
	
	public void deposit(final Account account, final BigDecimal value) {
		dao.deposit(account, value);
	}
	
	public void menuOption(final String option) {
		
	}
	
	public AtmDAO getDAO() {
		return dao;
	}
}
