package br.umc.model;

import java.math.BigDecimal;

public class Atm {
	private BigDecimal currentBalance;
	
	public Atm() {
		currentBalance = new BigDecimal("50");
	}
	
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	
	public void setCurrentBalance(final BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
}
