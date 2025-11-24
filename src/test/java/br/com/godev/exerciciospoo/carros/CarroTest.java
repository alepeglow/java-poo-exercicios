package br.com.godev.exerciciospoo.carros;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CarroTest {

    // ----------- TESTES UNITÁRIOS (regras específicas) -----------

    @Test
    @DisplayName("Carro com mais de 10 anos deve ser isento de IPVA")
    void carroAntigoNaoPagaIpva() {
        Pessoa proprietario = new Pessoa("Maria", "111.222.333-44");
        Carro carro = new Carro("VW", "Gol", "Branco",
                2000, 20000.0, proprietario);

        double ipva = carro.calcularIpva(2021); // 21 anos de uso

        assertEquals(0.0, ipva);
    }

    @Test
    @DisplayName("Carro com até 10 anos deve pagar IPVA maior que zero")
    void carroMaisNovoPagaIpva() {
        Pessoa proprietario = new Pessoa("João", "123.456.789-00");
        Carro carro = new Carro("Fiat", "Uno", "Prata",
                2015, 30000.0, proprietario);

        int anoAtual = 2020; // 5 anos de uso
        double valorRevendaEsperado = 30000.0 * Math.pow(0.95, 5);
        double ipvaEsperado = valorRevendaEsperado * 0.04;

        double ipva = carro.calcularIpva(anoAtual);

        assertEquals(ipvaEsperado, ipva, 0.0001);
        assertTrue(ipva > 0.0);
    }

    @Test
    @DisplayName("Calcula corretamente o tempo de uso em anos")
    void calcularTempoDeUsoEmAnos_valorValido() {
        Pessoa proprietario = new Pessoa("Carlos", "000.111.222-33");
        Carro carro = new Carro("Chevrolet", "Onix", "Preto",
                2018, 60000.0, proprietario);

        int anosUso = carro.calcularTempoDeUsoEmAnos(2023);

        assertEquals(5, anosUso);
    }

    @Test
    @DisplayName("Lança exceção quando ano atual é menor que ano de fabricação")
    void calcularTempoDeUsoEmAnos_anoAtualMenorDeveLancarExcecao() {
        Pessoa proprietario = new Pessoa("Ana", "999.888.777-66");
        Carro carro = new Carro("Hyundai", "HB20", "Prata",
                2018, 50000.0, proprietario);

        assertThrows(IllegalArgumentException.class,
                () -> carro.calcularTempoDeUsoEmAnos(2010));
    }

    @Test
    @DisplayName("Carro com mais de 20 anos de uso deve ter valor de revenda igual a zero")
    void carroComVidaUtilUltrapassadaTemValorRevendaZero() {
        Pessoa proprietario = new Pessoa("Bruno", "555.666.777-88");
        Carro carro = new Carro("Ford", "Ka", "Vermelho",
                2000, 25000.0, proprietario);

        double valorRevenda = carro.calcularValorRevenda(2025); // 25 anos de uso

        assertEquals(0.0, valorRevenda);
    }

    // ----------- TESTE PARAMETRIZADO (regra diferente: depreciação) -----------

    @ParameterizedTest
    @DisplayName("Depreciação de 5% ao ano deve ser aplicada corretamente para vários anos de uso")
    @CsvSource({
            "0, 30000.0",
            "1, 28500.0",
            "2, 27075.0",
            "3, 25721.25"
    })
    void depreciacaoCorretaParaVariosAnos(int anosUso, double valorEsperado) {
        Pessoa proprietario = new Pessoa("Felipe", "321.654.987-00");
        int anoFabricacao = 2015;
        int anoAtual = anoFabricacao + anosUso;

        Carro carro = new Carro("Fiat", "Uno", "Prata",
                anoFabricacao, 30000.0, proprietario);

        double valorRevenda = carro.calcularValorRevenda(anoAtual);

        assertEquals(valorEsperado, valorRevenda, 0.0001);
    }
}
