package br.com.godev.exerciciospoo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe ContaBancaria.
 * Testa operações de depósito e saque com diferentes tipos de conta.
 */
class ContaBancariaTest {

    /**
     * Testa depósito válido em conta.
     */
    @ParameterizedTest
    @ValueSource(doubles = {100.0, 50.50, 1000.0, 0.01})
    void devePermitirDepositoValido(double valor) {
        ContaBancaria conta = new ContaBancaria("12345", "0001", 0.0, TipoConta.CONTA_CORRENTE);
        
        boolean resultado = conta.depositar(valor);
        
        assertTrue(resultado);
        assertEquals(valor, conta.getSaldo(), 0.001);
    }

    /**
     * Testa que não permite depósito de valor negativo ou zero.
     */
    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, -100.0, -0.01})
    void naoDevePermitirDepositoInvalido(double valor) {
        ContaBancaria conta = new ContaBancaria("12345", "0001", 100.0, TipoConta.POUPANCA);
        double saldoInicial = conta.getSaldo();
        
        boolean resultado = conta.depositar(valor);
        
        assertFalse(resultado);
        assertEquals(saldoInicial, conta.getSaldo());
    }

    /**
     * Testa saque em conta corrente com taxa de R$ 0,50.
     */
    @Test
    void deveAplicarTaxaDeSaqueEmContaCorrente() {
        ContaBancaria conta = new ContaBancaria("12345", "0001", 1000.0, TipoConta.CONTA_CORRENTE);
        
        boolean resultado = conta.sacar(100.0);
        
        assertTrue(resultado);
        // Saldo = 1000 - 100 - 0.50 = 899.50
        assertEquals(899.50, conta.getSaldo(), 0.001);
    }

    /**
     * Testa saque em poupança sem taxa.
     */
    @Test
    void naoDeveAplicarTaxaDeSaqueEmPoupanca() {
        ContaBancaria conta = new ContaBancaria("12345", "0001", 1000.0, TipoConta.POUPANCA);
        
        boolean resultado = conta.sacar(100.0);
        
        assertTrue(resultado);
        // Saldo = 1000 - 100 = 900.00 (sem taxa)
        assertEquals(900.0, conta.getSaldo(), 0.001);
    }

    /**
     * Testa que não permite saque com saldo insuficiente em conta corrente.
     */
    @ParameterizedTest
    @CsvSource({
        "100.0, 200.0",   // Tentar sacar mais que o saldo
        "100.0, 100.0",   // Saldo exato mas precisa de +0.50 para taxa
        "99.60, 99.20"    // Quase o valor, mas falta 0.50 de taxa
    })
    void naoDevePermitirSaqueSeSaldoInsuficienteNaContaCorrente(double saldo, double valorSaque) {
        ContaBancaria conta = new ContaBancaria("12345", "0001", saldo, TipoConta.CONTA_CORRENTE);
        double saldoInicial = conta.getSaldo();
        
        boolean resultado = conta.sacar(valorSaque);
        
        assertFalse(resultado);
        assertEquals(saldoInicial, conta.getSaldo());
    }

    /**
     * Testa que não permite saque com saldo insuficiente em poupança.
     */
    @Test
    void naoDevePermitirSaqueSeSaldoInsuficienteNaPoupanca() {
        ContaBancaria conta = new ContaBancaria("12345", "0001", 100.0, TipoConta.POUPANCA);
        
        boolean resultado = conta.sacar(200.0);
        
        assertFalse(resultado);
        assertEquals(100.0, conta.getSaldo(), 0.001);
    }

    /**
     * Testa saque de valor exato do saldo em poupança (sem taxa).
     */
    @Test
    void devePermitirSacarTodoSaldoDaPoupanca() {
        ContaBancaria conta = new ContaBancaria("12345", "0001", 500.0, TipoConta.POUPANCA);
        
        boolean resultado = conta.sacar(500.0);
        
        assertTrue(resultado);
        assertEquals(0.0, conta.getSaldo(), 0.001);
    }

    /**
     * Testa que não permite saque de valor negativo ou zero.
     */
    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, -100.0})
    void naoDevePermitirSaqueDeValorInvalido(double valor) {
        ContaBancaria conta = new ContaBancaria("12345", "0001", 1000.0, TipoConta.CONTA_CORRENTE);
        double saldoInicial = conta.getSaldo();
        
        boolean resultado = conta.sacar(valor);
        
        assertFalse(resultado);
        assertEquals(saldoInicial, conta.getSaldo());
    }

    /**
     * Testa múltiplas operações em sequência.
     */
    @Test
    void deveRealizarMultiplasOperacoesEmSequencia() {
        ContaBancaria conta = new ContaBancaria("12345", "0001", 1000.0, TipoConta.CONTA_CORRENTE);
        
        // Depositar 500
        assertTrue(conta.depositar(500.0));
        assertEquals(1500.0, conta.getSaldo(), 0.001);
        
        // Sacar 200 (taxa de 0.50)
        assertTrue(conta.sacar(200.0));
        assertEquals(1299.50, conta.getSaldo(), 0.001);
        
        // Depositar 100
        assertTrue(conta.depositar(100.0));
        assertEquals(1399.50, conta.getSaldo(), 0.001);
        
        // Sacar 1000 (taxa de 0.50)
        assertTrue(conta.sacar(1000.0));
        assertEquals(399.0, conta.getSaldo(), 0.001);
    }

    /**
     * Testa que conta corrente cobra taxa corretamente em múltiplos saques.
     */
    @Test
    void deveCobraTaxaEmCadaSaqueNaContaCorrente() {
        ContaBancaria conta = new ContaBancaria("12345", "0001", 1000.0, TipoConta.CONTA_CORRENTE);
        
        // Primeiro saque: 100 + 0.50
        conta.sacar(100.0);
        assertEquals(899.50, conta.getSaldo(), 0.001);
        
        // Segundo saque: 100 + 0.50
        conta.sacar(100.0);
        assertEquals(799.0, conta.getSaldo(), 0.001);
        
        // Terceiro saque: 100 + 0.50
        conta.sacar(100.0);
        assertEquals(698.50, conta.getSaldo(), 0.001);
    }

    /**
     * Testa cenário limite: saque que deixa saldo exato para cobrir valor + taxa.
     */
    @Test
    void devePermitirSaqueSeHouverSaldoExatoParaTaxaNaContaCorrente() {
        // Saldo de 100.50 deve permitir saque de 100.00 (100 + 0.50 de taxa)
        ContaBancaria conta = new ContaBancaria("12345", "0001", 100.50, TipoConta.CONTA_CORRENTE);
        
        boolean resultado = conta.sacar(100.0);
        
        assertTrue(resultado);
        assertEquals(0.0, conta.getSaldo(), 0.001);
    }

    /**
     * Testa comparação entre conta corrente e poupança.
     */
    @Test
    void deveCompararComportamentoEntreContaCorrenteEPoupanca() {
        ContaBancaria corrente = new ContaBancaria("11111", "0001", 1000.0, TipoConta.CONTA_CORRENTE);
        ContaBancaria poupanca = new ContaBancaria("22222", "0001", 1000.0, TipoConta.POUPANCA);
        
        // Mesmo saque em ambas
        corrente.sacar(100.0);
        poupanca.sacar(100.0);
        
        // Corrente deve ter 899.50 (taxa de 0.50)
        assertEquals(899.50, corrente.getSaldo(), 0.001);
        
        // Poupança deve ter 900.00 (sem taxa)
        assertEquals(900.0, poupanca.getSaldo(), 0.001);
    }
}

