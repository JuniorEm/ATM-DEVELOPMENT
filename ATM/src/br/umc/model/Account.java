/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.umc.model;

import java.util.Objects;

/**
 * Classe que representa a conta de um usuário no banco
 * @author leonardolopes
 */
public class Account {
    private String accountNumber;
    private String pinNumber;
    
    public Account() {}
    
    public Account(final String accountNumber, final String pinNumber) {
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
    }
    
    /**
     * Método que retorna o número da conta.
     * @return O número da conta.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Método que define o número da conta.
     * @param accountNumber Número da conta
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Método que retorna o PIM da conta
     * @return PIM da conta
     */
    public String getPinNumber() {
        return pinNumber;
    }

    /**
     * Método que define o PIM da conta
     * @param pinNumber PIM da conta
     */
    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.accountNumber);
        hash = 61 * hash + Objects.hashCode(this.pinNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.accountNumber, other.accountNumber)) {
            return false;
        }
        if (!Objects.equals(this.pinNumber, other.pinNumber)) {
            return false;
        }
        return true;
    }
}
