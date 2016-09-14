package br.umc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.umc.data.AtmData;
import br.umc.entity.Account;
import br.umc.entity.Atm;
import br.umc.entity.AccountOperation;

/**
 * Classe que representa o acesso aos dados.
 * @author Junior
 *
 */
public class AccountDAO implements AccountOperation {
	/**
	 * Um modelo atm.
	 */
	private Atm atm;
	
	/**
	 * O divisor.
	 */
	private static final BigDecimal DIVISOR = new BigDecimal("100");
	
	public AccountDAO() {
		atm = new Atm();
	}
	
    /**
     * Verifica se a conta do cliente bate com o que possui no Banco de Dados e retorna o
     * respectivo objeto.
     * @param account O objeto conta.
     * @return Caso a operacao seja verdadeira ou falsa.
     */
    @Override
    public Account verifyCustomerAccount(final Account account) {
    	final List<Account> accounts = AtmData.getAccounts();
    	    	
    	final Optional<Account> optionalObtained = accounts.stream().filter(s -> s.getAccountNumber().equals(account.getAccountNumber()) 
    			&& s.getPinNumber().equals(account.getPinNumber())).findAny();
    	    	
    	final Account obtained = optionalObtained.isPresent() ? optionalObtained.get() : null;
    
    	return obtained;
    }
    
    /**
     * Metodo responsavel por realizar saque. Verifica se existe dinheiro na conta do
     * individuo que quer sacar, e verifica se o caixa possui uma quantidade para ser sacada
     * @param account O objeto conta.
     * @param value O valor a ser sacado.
     * @return Uma string representando a numera��o de cada status.
     */
	@Override
	public String withDraw(final Account account, final BigDecimal value) {
		if (verifyAccountBalance(account, value) && verifyAtmBalance(value)) {
			account.setBalance(account.getBalance().subtract(value));
			atm.setCurrentBalance(atm.getCurrentBalance().subtract(value));
			return "0";
		} else if (verifyAccountBalance(account, value)) {
			return "1";
		}
		return "2";
	}
	
	/**
	 * Verifica o saldo da conta do cliente.
	 * @param account O objeto conta.
	 * @param value O valor a ser sacado.
	 * @return Um boolean caso a conta esteja apta a realizar saque ou nao.
	 */
	public boolean verifyAccountBalance(final Account account, final BigDecimal value) {
		return value.compareTo(account.getBalance()) <= 0;
	}
	
	/**
	 * Verifica o saldo do atm.
	 * @param value O valor a ser sacado.
	 * @return Um boolean caso o atm possua valor maior do que o desejado ou nao.
	 */
	public boolean verifyAtmBalance(final BigDecimal value) {
		return value.compareTo(atm.getCurrentBalance()) <= 0;
	}
        
        /**
         * Consulta o saldo da conta.
         * @param account O objeto conta.
         * @return O valor obtido.
         */
        public BigDecimal consult(final Account account) {
            final Account obtained = verifyCustomerAccount(account);
            return obtained.getBalance();
        }
            
	/**
	 * Realiza deposito.
	 * @param account O objeto conta.
	 * @param value O valor a ser depositado.
	 */
	@Override
	public void deposit(final Account account, final BigDecimal value) {
		final BigDecimal added = value.divide(DIVISOR);
		account.setBalance(account.getBalance().add(added));
	}
}
