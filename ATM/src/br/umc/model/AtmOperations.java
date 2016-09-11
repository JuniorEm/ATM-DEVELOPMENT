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
public interface AtmOperations {
    
    Account verifyCustomerAccount(final Account account);
    void withDraw(final BigDecimal value);
    void consult();
    void deposit();
}
