package br.umc.model;

/**
 * Classe que representa o cliente
 * @author leonardolopes
 */
public class Customer {
    private Long id;
    private String name;
    private Account account;
    
    public Customer(){}
    
    public Customer (final Long id, final String name, final Account account)
    {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    /**
     * Método que retorna o ID do usuário
     * @return O ID do usuário
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que define o ID do usuário
     * @param id O ID do usuário
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Método que retorna o nome do usuário
     * @return O nome do usuário
     */
    public String getName() {
        return name;
    }

    /**
     * Método que define o nome do usuário
     * @param name Nome do usuário
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Método que retorna a conta
     * @return Retorna a conta
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Método que vai definir a conta
     * @param account Conta 
     */
    public void setAccount(final Account account) {
        this.account = account;
    }
}
