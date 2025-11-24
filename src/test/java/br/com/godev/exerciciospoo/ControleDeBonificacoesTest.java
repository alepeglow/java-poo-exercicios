package br.com.godev.exerciciospoo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o sistema de bonificações.
 * Testa Funcionario, Gerente e ControleDeBonificacoes.
 */
class ControleDeBonificacoesTest {

    /**
     * Testa bonificação de funcionário comum (5% do salário).
     */
    @ParameterizedTest
    @CsvSource({
        "1000.0, 50.0",
        "2000.0, 100.0",
        "3500.0, 175.0",
        "5000.0, 250.0"
    })
    void deveCalcularBonificacaoDeFuncionarioComum(double salario, double bonificacaoEsperada) {
        Funcionario funcionario = new Funcionario("João", "123.456.789-00", salario);
        
        assertEquals(bonificacaoEsperada, funcionario.getBonificacao(), 0.01);
    }

    /**
     * Testa bonificação de gerente (10% do salário).
     */
    @ParameterizedTest
    @CsvSource({
        "5000.0, 500.0",
        "8000.0, 800.0",
        "10000.0, 1000.0",
        "12500.0, 1250.0"
    })
    void deveCalcularBonificacaoDeGerente(double salario, double bonificacaoEsperada) {
        Gerente gerente = new Gerente("Maria", "987.654.321-00", salario, "senha123", 5);
        
        assertEquals(bonificacaoEsperada, gerente.getBonificacao(), 0.01);
    }

    /**
     * Testa que gerente herda de funcionário.
     */
    @Test
    void gerenteDeveHerdarDeFuncionario() {
        Gerente gerente = new Gerente("Carlos", "111.222.333-44", 7000.0, "senha", 3);
        
        assertTrue(gerente instanceof Funcionario);
        assertNotNull(gerente.getNome());
        assertNotNull(gerente.getCpf());
        assertTrue(gerente.getSalario() > 0);
    }

    /**
     * Testa que gerente sobrescreve o método getBonificacao.
     */
    @Test
    void gerenteDeveSobrescreverGetBonificacao() {
        double salario = 5000.0;
        
        Funcionario funcionario = new Funcionario("Ana", "555.666.777-88", salario);
        Gerente gerente = new Gerente("Pedro", "999.888.777-66", salario, "senha", 10);
        
        // Funcionário: 5% = 250.0
        assertEquals(250.0, funcionario.getBonificacao(), 0.01);
        
        // Gerente: 10% = 500.0
        assertEquals(500.0, gerente.getBonificacao(), 0.01);
        
        // Gerente deve ter o dobro da bonificação
        assertTrue(gerente.getBonificacao() > funcionario.getBonificacao());
    }

    /**
     * Testa registro de bonificação no controle.
     */
    @Test
    void deveRegistrarBonificacaoNoControle() {
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        Funcionario funcionario = new Funcionario("Roberto", "123.123.123-12", 4000.0);
        
        assertEquals(0.0, controle.getTotalBonificacao(), 0.01);
        
        controle.registrar(funcionario);
        
        // Bonificação = 4000 * 0.05 = 200.0
        assertEquals(200.0, controle.getTotalBonificacao(), 0.01);
    }

    /**
     * Testa registro de múltiplas bonificações.
     */
    @Test
    void deveAcumularMultiplasBonificacoes() {
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        
        Funcionario func1 = new Funcionario("João", "111.111.111-11", 3000.0);  // 150.0
        Funcionario func2 = new Funcionario("Maria", "222.222.222-22", 4000.0); // 200.0
        Gerente gerente1 = new Gerente("Carlos", "333.333.333-33", 8000.0, "s1", 5); // 800.0
        
        controle.registrar(func1);
        controle.registrar(func2);
        controle.registrar(gerente1);
        
        // Total: 150 + 200 + 800 = 1150.0
        assertEquals(1150.0, controle.getTotalBonificacao(), 0.01);
    }

    /**
     * Testa controle com apenas gerentes.
     */
    @Test
    void deveCalcularTotalApenasComGerentes() {
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        
        Gerente g1 = new Gerente("Ana", "111.111.111-11", 10000.0, "senha1", 8);   // 1000.0
        Gerente g2 = new Gerente("Pedro", "222.222.222-22", 12000.0, "senha2", 10); // 1200.0
        
        controle.registrar(g1);
        controle.registrar(g2);
        
        assertEquals(2200.0, controle.getTotalBonificacao(), 0.01);
    }

    /**
     * Testa controle com apenas funcionários comuns.
     */
    @Test
    void deveCalcularTotalApenasComFuncionarios() {
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        
        Funcionario f1 = new Funcionario("Luis", "111.111.111-11", 2000.0);  // 100.0
        Funcionario f2 = new Funcionario("Carla", "222.222.222-22", 3000.0); // 150.0
        Funcionario f3 = new Funcionario("José", "333.333.333-33", 2500.0);  // 125.0
        
        controle.registrar(f1);
        controle.registrar(f2);
        controle.registrar(f3);
        
        assertEquals(375.0, controle.getTotalBonificacao(), 0.01);
    }

    /**
     * Testa cenário complexo com muitos funcionários.
     */
    @Test
    void deveCalcularTotalComMuitosFuncionarios() {
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        
        // 3 funcionários comuns
        Funcionario f1 = new Funcionario("Func1", "111.111.111-11", 3000.0);  // 150.0
        Funcionario f2 = new Funcionario("Func2", "222.222.222-22", 3500.0);  // 175.0
        Funcionario f3 = new Funcionario("Func3", "333.333.333-33", 2800.0);  // 140.0
        
        // 2 gerentes
        Gerente g1 = new Gerente("Gerente1", "444.444.444-44", 8000.0, "s1", 5);  // 800.0
        Gerente g2 = new Gerente("Gerente2", "555.555.555-55", 9500.0, "s2", 8);  // 950.0
        
        controle.registrar(f1);
        controle.registrar(f2);
        controle.registrar(f3);
        controle.registrar(g1);
        controle.registrar(g2);
        
        // Total: 150 + 175 + 140 + 800 + 950 = 2215.0
        assertEquals(2215.0, controle.getTotalBonificacao(), 0.01);
    }

    /**
     * Testa polimorfismo: registrar funcionário e gerente usando tipo Funcionario.
     */
    @Test
    void deveUsarPolimorfismoNoRegistro() {
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        
        // Ambos podem ser tratados como Funcionario
        Funcionario f = new Funcionario("Ana", "111.111.111-11", 4000.0);
        Funcionario g = new Gerente("Pedro", "222.222.222-22", 6000.0, "senha", 5);
        
        controle.registrar(f);  // 200.0 (5%)
        controle.registrar(g);  // 600.0 (10%)
        
        assertEquals(800.0, controle.getTotalBonificacao(), 0.01);
    }

    /**
     * Testa atributos específicos do gerente.
     */
    @Test
    void gerenteDeveTerAtributosEspecificos() {
        Gerente gerente = new Gerente("Roberto", "123.456.789-00", 10000.0, "minhasenha", 15);
        
        assertEquals("Roberto", gerente.getNome());
        assertEquals("123.456.789-00", gerente.getCpf());
        assertEquals(10000.0, gerente.getSalario(), 0.01);
        assertEquals("minhasenha", gerente.getSenha());
        assertEquals(15, gerente.getNumeroDeGerenciados());
    }

    /**
     * Testa autenticação do gerente.
     */
    @Test
    void deveAutenticarGerenteComSenhaCorreta() {
        Gerente gerente = new Gerente("Ana", "987.654.321-00", 8000.0, "senha123", 10);
        
        assertTrue(gerente.autenticar("senha123"));
        assertFalse(gerente.autenticar("senhaerrada"));
        assertFalse(gerente.autenticar(""));
    }

    /**
     * Testa inicialização do controle de bonificações.
     */
    @Test
    void deveInicializarControleComTotalZero() {
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        
        assertEquals(0.0, controle.getTotalBonificacao());
    }
}

