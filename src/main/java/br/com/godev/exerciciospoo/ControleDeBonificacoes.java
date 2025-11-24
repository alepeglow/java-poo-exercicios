package br.com.godev.exerciciospoo;

/**
 * Classe responsável por controlar o total de bonificações pagas aos funcionários.
 * Acumula as bonificações de cada funcionário registrado.
 */
public class ControleDeBonificacoes {
    private double totalBonificacao;

    /**
     * Construtor padrão.
     * Inicializa o total de bonificações como 0.
     */
    public ControleDeBonificacoes() {
        this.totalBonificacao = 0.0;
    }

    /**
     * Registra a bonificação de um funcionário.
     * Soma a bonificação do funcionário ao total acumulado.
     * 
     * @param funcionario Funcionário cuja bonificação será registrada
     */
    public void registrar(Funcionario funcionario) {
        this.totalBonificacao += funcionario.getBonificacao();
    }

    /**
     * Retorna o total de bonificações acumuladas.
     * 
     * @return Total de bonificações
     */
    public double getTotalBonificacao() {
        return totalBonificacao;
    }

    @Override
    public String toString() {
        return "ControleDeBonificacoes{" +
                "totalBonificacao=" + totalBonificacao +
                '}';
    }
}

