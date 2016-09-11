package br.umc.model;

/**
 * Classe que representa o cliente
 * @author leonardolopes
 */
public class Customer {
    private Long id;
    private String name;
    
    public Customer(){}
    
    public Customer (final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Metodo que retorna o ID do usuario
     * @return O ID do usuario
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que define o ID do usuario
     * @param id O ID do usuario
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Metodo que retorna o nome do usuario
     * @return O nome do usuario
     */
    public String getName() {
        return name;
    }

    /**
     * Metodo que define o nome do usuario
     * @param name Nome do usuario
     */
    public void setName(final String name) {
        this.name = name;
    }
}
