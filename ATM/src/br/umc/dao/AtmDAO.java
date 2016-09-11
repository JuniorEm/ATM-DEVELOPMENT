package br.umc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.umc.data.AtmData;
import br.umc.model.Account;
import br.umc.model.Atm;
import br.umc.model.AtmOperation;

public class AtmDAO implements AtmOperation {
	private Atm atm;
	
	public AtmDAO() {
		atm = new Atm();
	}
    /**
     * Verifica se a conta do cliente bate com o que possui no Banco de Dados e retorna o
     * respectivo objeto.
     * @param account O objeto conta.
     * @return Caso a operação seja verdadeira ou falsa.
     */
    public Account verifyCustomerAccount(final Account account) {
    	final List<Account> accounts = AtmData.getAccounts();
    	    	
    	final Optional<Account> optionalObtained = accounts.stream().filter(s -> s.getAccountNumber().equals(account.getAccountNumber()) 
    			&& s.getPinNumber().equals(account.getPinNumber())).findAny();
    	
    	final Account obtained = optionalObtained.isPresent() ? optionalObtained.get() : null;
    
    	return obtained;
    }

	@Override
	public String withDraw(final Account account, final BigDecimal value) {
		if (verifyCustomerBalance(account, value) && verifyAtmBalance(value)) {
			account.setBalance(account.getBalance().subtract(value));
			return "0";
		} else if (verifyCustomerBalance(account, value)) {
			return "1";
		}
		return "2";
	}
	
	public boolean verifyCustomerBalance(final Account account, final BigDecimal value) {
		return value.compareTo(account.getBalance()) <= 0;
	}
	
	public boolean verifyAtmBalance(final BigDecimal value) {
		return value.compareTo(atm.getCurrentBalance()) <= 0;
	}

	@Override
	public void deposit(final Account account, final BigDecimal value) {
		// TODO Auto-generated method stub
		
	}
}
