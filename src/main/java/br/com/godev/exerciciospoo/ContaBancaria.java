package br.com.godev.exerciciospoo;

/**
 * Classe que representa uma Conta Bancária.
 * Suporta operações de depósito e saque com regras específicas por tipo de conta.
 * 
 * Regras de negócio:
 * - Conta Corrente: cobra taxa de R$ 0,50 por saque
 * - Poupança: não cobra taxa de saque
 */
public class ContaBancaria {
    private String numero;
    private String agencia;
    private double saldo;
    private TipoConta tipoConta;

    /**
     * Taxa fixa cobrada por saque em conta corrente.
     */
    private static final double TAXA_SAQUE_CONTA_CORRENTE = 0.50;

    /**
     * Construtor completo da classe ContaBancaria.
     * 
     * @param numero Número da conta
     * @param agencia Agência da conta
     * @param saldo Saldo inicial da conta
     * @param tipoConta Tipo da conta (CONTA_CORRENTE ou POUPANCA)
     */
    public ContaBancaria(String numero, String agencia, double saldo, TipoConta tipoConta) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }

    /**
     * Realiza um depósito na conta.
     * 
     * Regra: Apenas valores positivos podem ser depositados.
     * 
     * @param valor Valor a ser depositado
     * @return true se o depósito foi realizado com sucesso, false caso contrário
     */
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return true;
        }
        return false;
    }

    /**
     * Realiza um saque na conta.
     * 
     * Regras:
     * - Conta Corrente: cobra taxa de R$ 0,50 por saque
     * - Poupança: não cobra taxa
     * - Precisa haver saldo suficiente para o valor + taxa (se houver)
     * 
     * @param valor Valor a ser sacado
     * @return true se o saque foi realizado com sucesso, false caso contrário
     */
    public boolean sacar(double valor) {
        if (valor <= 0) {
            return false;
        }

        double valorTotal = valor;

        // Aplica taxa de saque para conta corrente
        if (tipoConta == TipoConta.CONTA_CORRENTE) {
            valorTotal += TAXA_SAQUE_CONTA_CORRENTE;
        }

        // Verifica se há saldo suficiente
        if (saldo >= valorTotal) {
            saldo -= valorTotal;
            return true;
        }

        return false;
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    @Override
    public String toString() {
        return "ContaBancaria{" +
                "numero='" + numero + '\'' +
                ", agencia='" + agencia + '\'' +
                ", saldo=" + saldo +
                ", tipoConta=" + tipoConta +
                '}';
    }
}

