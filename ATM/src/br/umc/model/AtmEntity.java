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
public class AtmEntity implements AtmOperations {
    
    /**
     * Verifica se a conta do cliente bate com o que possui no Banco de Dados
     * @param account O objeto conta.
     * @return Caso a operação seja verdadeira ou falsa.
     */
    @Override
    public boolean verifyCustomerAccount(final Account account) {
        return account.getAccountNumber().equals("123456")
                && account.getPinNumber().equals("123456");
    }

    @Override
    public void withDraw(BigDecimal value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deposit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
