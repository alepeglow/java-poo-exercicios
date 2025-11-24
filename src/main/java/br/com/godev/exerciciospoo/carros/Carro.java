package br.com.godev.exerciciospoo.carros;

public class Carro {

    private String fabricante;
    private String modelo;
    private String cor;
    private int anoFabricacao;
    private double precoCompra;
    private Pessoa proprietario;

    public Carro(String fabricante, String modelo, String cor,
                 int anoFabricacao, double precoCompra, Pessoa proprietario) {

        this.fabricante = fabricante;
        this.modelo = modelo;
        this.cor = cor;
        this.anoFabricacao = anoFabricacao;
        this.precoCompra = precoCompra;
        this.proprietario = proprietario;
    }

    public int calcularTempoDeUsoEmAnos(int anoAtual) {
        int anos = anoAtual - anoFabricacao;
        if (anos < 0) {
            throw new IllegalArgumentException("Ano atual não pode ser menor que o ano de fabricação");
        }
        return anos;
    }

    public double calcularValorRevenda(int anoAtual) {
        int anosUso = calcularTempoDeUsoEmAnos(anoAtual);

        if (anosUso >= 20) {
            return 0.0;
        }

        return precoCompra * Math.pow(0.95, anosUso);
    }

    public double calcularIpva(int anoAtual) {
        int anosUso = calcularTempoDeUsoEmAnos(anoAtual);

        if (anosUso > 10) {
            return 0.0; // Isento
        }

        return calcularValorRevenda(anoAtual) * 0.04;
    }
}
