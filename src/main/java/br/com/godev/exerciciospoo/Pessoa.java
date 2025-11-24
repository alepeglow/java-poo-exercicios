package br.com.godev.exerciciospoo;

/**
 * Classe que representa uma Pessoa.
 * Possui informações básicas como nome e CPF.
 */
public class Pessoa {
    private String nome;
    private String cpf;

    /**
     * Construtor completo da classe Pessoa.
     * 
     * @param nome Nome da pessoa
     * @param cpf CPF da pessoa
     */
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}

