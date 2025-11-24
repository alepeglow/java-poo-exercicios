package br.com.godev.exerciciospoo;

/**
 * Classe que representa um Gerente.
 * Herda de Funcionario e adiciona atributos e comportamentos específicos.
 * 
 * Regra de negócio: Gerente recebe bonificação de 10% sobre o salário.
 */
public class Gerente extends Funcionario {
    private String senha;
    private int numeroDeGerenciados;

    /**
     * Construtor completo da classe Gerente.
     * 
     * @param nome Nome do gerente
     * @param cpf CPF do gerente
     * @param salario Salário do gerente
     * @param senha Senha do gerente
     * @param numeroDeGerenciados Número de funcionários gerenciados
     */
    public Gerente(String nome, String cpf, double salario, String senha, int numeroDeGerenciados) {
        super(nome, cpf, salario);
        this.senha = senha;
        this.numeroDeGerenciados = numeroDeGerenciados;
    }

    /**
     * Calcula a bonificação do gerente.
     * 
     * Regra: Gerente recebe 10% do salário como bonificação.
     * 
     * @return Valor da bonificação
     */
    @Override
    public double getBonificacao() {
        return getSalario() * 0.10;
    }

    /**
     * Autentica o gerente verificando a senha.
     * 
     * @param senha Senha a ser verificada
     * @return true se a senha estiver correta, false caso contrário
     */
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    // Getters e Setters
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNumeroDeGerenciados() {
        return numeroDeGerenciados;
    }

    public void setNumeroDeGerenciados(int numeroDeGerenciados) {
        this.numeroDeGerenciados = numeroDeGerenciados;
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", salario=" + getSalario() +
                ", numeroDeGerenciados=" + numeroDeGerenciados +
                '}';
    }
}

