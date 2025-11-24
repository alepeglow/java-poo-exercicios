package br.com.godev.exerciciospoo;

import java.time.Year;

/**
 * Classe que representa um Carro.
 * Contém informações sobre o veículo e regras de negócio para cálculo de IPVA e valor de revenda.
 * 
 * Regras de negócio:
 * - Vida útil do carro: 20 anos
 * - Depreciação: 5% ao ano sobre o valor de compra
 * - IPVA: 4% do valor de revenda para carros com até 10 anos
 * - Carros com mais de 10 anos não pagam IPVA
 */
public class Carro {
    private String fabricante;
    private String modelo;
    private String cor;
    private int anoFabricacao;
    private double precoCompra;
    private Pessoa proprietario;

    /**
     * Construtor completo da classe Carro.
     * 
     * @param fabricante Fabricante do carro
     * @param modelo Modelo do carro
     * @param cor Cor do carro
     * @param anoFabricacao Ano de fabricação do carro
     * @param precoCompra Preço de compra original do carro
     * @param proprietario Proprietário do carro
     */
    public Carro(String fabricante, String modelo, String cor, int anoFabricacao, 
                 double precoCompra, Pessoa proprietario) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.cor = cor;
        this.anoFabricacao = anoFabricacao;
        this.precoCompra = precoCompra;
        this.proprietario = proprietario;
    }

    /**
     * Calcula o tempo de uso do carro em anos.
     * 
     * @param anoAtual Ano atual para cálculo
     * @return Número de anos de uso do carro
     */
    public int calcularTempoDeUsoEmAnos(int anoAtual) {
        return anoAtual - anoFabricacao;
    }

    /**
     * Calcula o valor de revenda do carro considerando a depreciação.
     * 
     * Regra: O carro perde 5% do valor de compra a cada ano.
     * O valor mínimo de revenda é 0 (não pode ser negativo).
     * 
     * @return Valor de revenda do carro
     */
    public double calcularValorRevenda() {
        int anoAtual = Year.now().getValue();
        int tempoDeUso = calcularTempoDeUsoEmAnos(anoAtual);
        
        // Aplica depreciação de 5% ao ano
        double valorRevenda = precoCompra;
        for (int i = 0; i < tempoDeUso; i++) {
            valorRevenda = valorRevenda * 0.95; // Reduz 5% ao ano
        }
        
        // Garante que o valor não seja negativo
        return Math.max(0, valorRevenda);
    }

    /**
     * Calcula o valor do IPVA do carro.
     * 
     * Regras:
     * - Carros com até 10 anos: IPVA = 4% do valor de revenda
     * - Carros com mais de 10 anos: IPVA = 0 (isentos)
     * 
     * @return Valor do IPVA a ser pago
     */
    public double calcularIPVA() {
        int anoAtual = Year.now().getValue();
        int tempoDeUso = calcularTempoDeUsoEmAnos(anoAtual);
        
        // Carros com mais de 10 anos não pagam IPVA
        if (tempoDeUso > 10) {
            return 0.0;
        }
        
        // IPVA = 4% do valor de revenda
        double valorRevenda = calcularValorRevenda();
        return valorRevenda * 0.04;
    }

    // Getters e Setters
    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Pessoa getProprietario() {
        return proprietario;
    }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cor='" + cor + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", precoCompra=" + precoCompra +
                ", proprietario=" + proprietario +
                '}';
    }
}

