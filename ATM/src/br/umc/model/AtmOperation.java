/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.umc.model;

import java.math.BigDecimal;

/**
 *
 * @author 12141100859
 */
public interface AtmOperation {
    
    Account verifyCustomerAccount(final Account account);
    
    String withDraw(final Account account, final BigDecimal value);
    
    default BigDecimal consult(final Account account) {
    	final Account obtained = verifyCustomerAccount(account);
    	return obtained.getBalance();
    }
    
    void deposit(final Account account, final BigDecimal value);
}
