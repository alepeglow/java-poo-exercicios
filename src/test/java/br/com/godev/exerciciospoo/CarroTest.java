package br.com.godev.exerciciospoo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Carro.
 * Testa cálculo de tempo de uso, valor de revenda e IPVA.
 */
class CarroTest {

    /**
     * Testa o cálculo do tempo de uso do carro.
     */
    @ParameterizedTest
    @CsvSource({
        "2020, 2025, 5",
        "2015, 2025, 10",
        "2010, 2025, 15",
        "2024, 2025, 1",
        "2025, 2025, 0"
    })
    void deveCalcularTempoDeUsoEmAnos(int anoFabricacao, int anoAtual, int tempoEsperado) {
        Pessoa proprietario = new Pessoa("João", "123.456.789-00");
        Carro carro = new Carro("Toyota", "Corolla", "Prata", anoFabricacao, 50000.0, proprietario);
        
        assertEquals(tempoEsperado, carro.calcularTempoDeUsoEmAnos(anoAtual));
    }

    /**
     * Testa o cálculo do valor de revenda com depreciação de 5% ao ano.
     */
    @Test
    void deveCalcularValorRevendaComDepreciacao() {
        Pessoa proprietario = new Pessoa("Maria", "987.654.321-00");
        Carro carro = new Carro("Honda", "Civic", "Preto", 2020, 100000.0, proprietario);
        
        // Após 5 anos: 100000 * 0.95^5 = 77378.09
        double valorRevenda = carro.calcularValorRevenda();
        
        // Verifica se está próximo ao valor esperado (com margem de erro de 1)
        assertTrue(valorRevenda > 77000 && valorRevenda < 78000);
    }

    /**
     * Testa que o valor de revenda não pode ser negativo.
     */
    @Test
    void valorRevendaNaoDeveSerNegativo() {
        Pessoa proprietario = new Pessoa("Carlos", "111.222.333-44");
        // Carro muito antigo (30 anos) - valor de revenda deve ser >= 0
        Carro carro = new Carro("Fiat", "Uno", "Branco", 1995, 10000.0, proprietario);
        
        double valorRevenda = carro.calcularValorRevenda();
        
        assertTrue(valorRevenda >= 0);
    }

    /**
     * Testa o cálculo do IPVA para carros com até 10 anos.
     * IPVA = 4% do valor de revenda.
     */
    @Test
    void deveCalcularIpvaParaCarroComMenosDe10Anos() {
        Pessoa proprietario = new Pessoa("Ana", "555.666.777-88");
        // Carro novo (1 ano)
        Carro carro = new Carro("Volkswagen", "Golf", "Azul", 2024, 80000.0, proprietario);
        
        double ipva = carro.calcularIPVA();
        double valorRevenda = carro.calcularValorRevenda();
        double ipvaEsperado = valorRevenda * 0.04;
        
        assertEquals(ipvaEsperado, ipva, 0.01);
        assertTrue(ipva > 0);
    }

    /**
     * Testa que carros com mais de 10 anos não pagam IPVA.
     */
    @ParameterizedTest
    @CsvSource({
        "2014, 2025, 11",  // 11 anos
        "2010, 2025, 15",  // 15 anos
        "2000, 2025, 25"   // 25 anos
    })
    void naoDeveCalcularIpvaParaCarroComMaisDe10Anos(int anoFabricacao, int anoAtual, int idade) {
        Pessoa proprietario = new Pessoa("Pedro", "999.888.777-66");
        Carro carro = new Carro("Chevrolet", "Corsa", "Vermelho", anoFabricacao, 30000.0, proprietario);
        
        double ipva = carro.calcularIPVA();
        
        assertEquals(0.0, ipva);
    }

    /**
     * Testa o IPVA para carro com exatamente 10 anos.
     * Carro com 10 anos ainda paga IPVA.
     */
    @Test
    void deveCalcularIpvaParaCarroCom10Anos() {
        Pessoa proprietario = new Pessoa("Lucia", "444.333.222-11");
        Carro carro = new Carro("Ford", "Focus", "Cinza", 2015, 50000.0, proprietario);
        
        int tempoDeUso = carro.calcularTempoDeUsoEmAnos(2025);
        assertEquals(10, tempoDeUso);
        
        double ipva = carro.calcularIPVA();
        assertTrue(ipva > 0); // Ainda paga IPVA
    }

    /**
     * Testa integração completa: proprietário, carro e todos os cálculos.
     */
    @Test
    void deveIntegrarProprietarioComCarro() {
        Pessoa proprietario = new Pessoa("Roberto Silva", "123.321.456-78");
        Carro carro = new Carro(
            "Nissan",
            "Sentra",
            "Prata",
            2020,
            70000.0,
            proprietario
        );
        
        assertNotNull(carro.getProprietario());
        assertEquals("Roberto Silva", carro.getProprietario().getNome());
        assertEquals("123.321.456-78", carro.getProprietario().getCpf());
        
        // Verificar que os cálculos funcionam com proprietário definido
        assertTrue(carro.calcularValorRevenda() > 0);
        assertTrue(carro.calcularIPVA() >= 0);
    }
}

