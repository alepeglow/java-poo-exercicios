package br.com.godev.exerciciospoo;

/**
 * Classe que representa um Funcionário.
 * Contém informações básicas e o cálculo de bonificação.
 * 
 * Regra de negócio: Funcionário comum recebe bonificação de 5% sobre o salário.
 */
public class Funcionario {
    private String nome;
    private String cpf;
    private double salario;

    /**
     * Construtor completo da classe Funcionario.
     * 
     * @param nome Nome do funcionário
     * @param cpf CPF do funcionário
     * @param salario Salário do funcionário
     */
    public Funcionario(String nome, String cpf, double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    /**
     * Calcula a bonificação do funcionário.
     * 
     * Regra: Funcionário comum recebe 5% do salário como bonificação.
     * 
     * @return Valor da bonificação
     */
    public double getBonificacao() {
        return salario * 0.05;
    }

    // Getters e Setters
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salario=" + salario +
                '}';
    }
}

