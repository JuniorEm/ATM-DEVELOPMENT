package br.com.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.data.AtmData;
import br.umc.model.Account;
import br.umc.model.AtmOperations;

public class AtmDAO implements AtmOperations {
	
    /**
     * Verifica se a conta do cliente bate com o que possui no Banco de Dados e retorna o
     * respectivo objeto.
     * @param account O objeto conta.
     * @return Caso a operação seja verdadeira ou falsa.
     */
    public Account verifyCustomerAccount(final Account account) {
    	final List<Account> accounts = AtmData.getAccounts();
    	
    	for (Account selected : accounts) {
    		if (selected.getAccountNumber().equals(account.getAccountNumber()) && 
    				selected.getPinNumber().equals(account.getPinNumber()))
    			return selected;
    	}
    	
    	return null;
    }

	@Override
	public void withDraw(BigDecimal value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consult() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		
	}
}
