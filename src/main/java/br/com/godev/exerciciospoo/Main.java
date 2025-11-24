package br.com.godev.exerciciospoo;

import java.time.Year;

/**
 * Classe principal para demonstração de todas as funcionalidades do sistema.
 * 
 * Demonstra:
 * 1. Sistema de Carro e Pessoa com cálculo de IPVA e valor de revenda
 * 2. Sistema de Funcionários e Bonificações
 * 3. Sistema de Vendas com Pedidos
 * 4. Sistema de Conta Bancária com diferentes tipos de conta
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("SISTEMA POO - DEMONSTRAÇÃO COMPLETA");
        System.out.println("=".repeat(80));
        
        demonstrarCarroEPessoa();
        demonstrarBonificacoes();
        demonstrarSistemaDeVendas();
        demonstrarContaBancaria();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("FIM DA DEMONSTRAÇÃO");
        System.out.println("=".repeat(80));
    }
    
    /**
     * Demonstra o módulo de Carro e Pessoa.
     * Testa cálculo de tempo de uso, valor de revenda e IPVA.
     */
    private static void demonstrarCarroEPessoa() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("1) MÓDULO CARRO E PESSOA");
        System.out.println("=".repeat(80));
        
        // Criar proprietários
        Pessoa pessoa1 = new Pessoa("João Silva", "123.456.789-00");
        Pessoa pessoa2 = new Pessoa("Maria Santos", "987.654.321-00");
        
        int anoAtual = Year.now().getValue();
        
        // Criar carro novo (menos de 10 anos)
        Carro carroNovo = new Carro(
            "Toyota",
            "Corolla",
            "Prata",
            anoAtual - 3,  // 3 anos de uso
            80000.0,
            pessoa1
        );
        
        System.out.println("\nCarro 1 - Relativamente Novo:");
        System.out.println("  Proprietário: " + carroNovo.getProprietario().getNome());
        System.out.println("  Modelo: " + carroNovo.getFabricante() + " " + carroNovo.getModelo());
        System.out.println("  Ano de fabricação: " + carroNovo.getAnoFabricacao());
        System.out.println("  Preço de compra: R$ " + String.format("%.2f", carroNovo.getPrecoCompra()));
        System.out.println("  Tempo de uso: " + carroNovo.calcularTempoDeUsoEmAnos(anoAtual) + " anos");
        System.out.println("  Valor de revenda: R$ " + String.format("%.2f", carroNovo.calcularValorRevenda()));
        System.out.println("  IPVA a pagar: R$ " + String.format("%.2f", carroNovo.calcularIPVA()));
        
        // Criar carro antigo (mais de 10 anos) - isento de IPVA
        Carro carroAntigo = new Carro(
            "Volkswagen",
            "Gol",
            "Branco",
            anoAtual - 15,  // 15 anos de uso
            25000.0,
            pessoa2
        );
        
        System.out.println("\nCarro 2 - Antigo (isento de IPVA):");
        System.out.println("  Proprietário: " + carroAntigo.getProprietario().getNome());
        System.out.println("  Modelo: " + carroAntigo.getFabricante() + " " + carroAntigo.getModelo());
        System.out.println("  Ano de fabricação: " + carroAntigo.getAnoFabricacao());
        System.out.println("  Preço de compra: R$ " + String.format("%.2f", carroAntigo.getPrecoCompra()));
        System.out.println("  Tempo de uso: " + carroAntigo.calcularTempoDeUsoEmAnos(anoAtual) + " anos");
        System.out.println("  Valor de revenda: R$ " + String.format("%.2f", carroAntigo.calcularValorRevenda()));
        System.out.println("  IPVA a pagar: R$ " + String.format("%.2f", carroAntigo.calcularIPVA()) + " (ISENTO)");
        
        // Criar carro com 10 anos exatos (último ano que paga IPVA)
        Carro carroDezAnos = new Carro(
            "Honda",
            "Civic",
            "Preto",
            anoAtual - 10,  // 10 anos de uso
            60000.0,
            pessoa1
        );
        
        System.out.println("\nCarro 3 - 10 anos (último ano com IPVA):");
        System.out.println("  Modelo: " + carroDezAnos.getFabricante() + " " + carroDezAnos.getModelo());
        System.out.println("  Tempo de uso: " + carroDezAnos.calcularTempoDeUsoEmAnos(anoAtual) + " anos");
        System.out.println("  Valor de revenda: R$ " + String.format("%.2f", carroDezAnos.calcularValorRevenda()));
        System.out.println("  IPVA a pagar: R$ " + String.format("%.2f", carroDezAnos.calcularIPVA()));
    }
    
    /**
     * Demonstra o módulo de Funcionários e Bonificações.
     * Testa bonificação de funcionários comuns e gerentes.
     */
    private static void demonstrarBonificacoes() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("2) MÓDULO BONIFICAÇÕES");
        System.out.println("=".repeat(80));
        
        // Criar controle de bonificações
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        
        // Criar funcionários comuns
        Funcionario func1 = new Funcionario("Carlos Oliveira", "111.222.333-44", 3000.0);
        Funcionario func2 = new Funcionario("Ana Paula", "555.666.777-88", 3500.0);
        Funcionario func3 = new Funcionario("Pedro Costa", "999.888.777-66", 2800.0);
        
        // Criar gerentes
        Gerente gerente1 = new Gerente("Roberto Alves", "123.123.123-12", 8000.0, "senha123", 5);
        Gerente gerente2 = new Gerente("Fernanda Lima", "456.456.456-45", 9500.0, "senha456", 8);
        
        System.out.println("\nFuncionários Comuns (bonificação de 5%):");
        System.out.println("  " + func1.getNome() + " - Salário: R$ " + 
                          String.format("%.2f", func1.getSalario()) + 
                          " | Bonificação: R$ " + String.format("%.2f", func1.getBonificacao()));
        
        System.out.println("  " + func2.getNome() + " - Salário: R$ " + 
                          String.format("%.2f", func2.getSalario()) + 
                          " | Bonificação: R$ " + String.format("%.2f", func2.getBonificacao()));
        
        System.out.println("  " + func3.getNome() + " - Salário: R$ " + 
                          String.format("%.2f", func3.getSalario()) + 
                          " | Bonificação: R$ " + String.format("%.2f", func3.getBonificacao()));
        
        System.out.println("\nGerentes (bonificação de 10%):");
        System.out.println("  " + gerente1.getNome() + " - Salário: R$ " + 
                          String.format("%.2f", gerente1.getSalario()) + 
                          " | Bonificação: R$ " + String.format("%.2f", gerente1.getBonificacao()) +
                          " | Gerencia: " + gerente1.getNumeroDeGerenciados() + " funcionários");
        
        System.out.println("  " + gerente2.getNome() + " - Salário: R$ " + 
                          String.format("%.2f", gerente2.getSalario()) + 
                          " | Bonificação: R$ " + String.format("%.2f", gerente2.getBonificacao()) +
                          " | Gerencia: " + gerente2.getNumeroDeGerenciados() + " funcionários");
        
        // Registrar bonificações
        controle.registrar(func1);
        controle.registrar(func2);
        controle.registrar(func3);
        controle.registrar(gerente1);
        controle.registrar(gerente2);
        
        System.out.println("\n>>> Total de bonificações do mês: R$ " + 
                          String.format("%.2f", controle.getTotalBonificacao()));
    }
    
    /**
     * Demonstra o módulo de Sistema de Vendas.
     * Testa criação de pedidos com múltiplos itens.
     */
    private static void demonstrarSistemaDeVendas() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("3) MÓDULO SISTEMA DE VENDAS");
        System.out.println("=".repeat(80));
        
        // Criar produtos
        Produto produto1 = new Produto(1, "Notebook Dell", 3500.0);
        Produto produto2 = new Produto(2, "Mouse Logitech", 80.0);
        Produto produto3 = new Produto(3, "Teclado Mecânico", 250.0);
        Produto produto4 = new Produto(4, "Monitor 24\"", 900.0);
        
        System.out.println("\nProdutos disponíveis:");
        System.out.println("  [" + produto1.getCodigo() + "] " + produto1.getNome() + 
                          " - R$ " + String.format("%.2f", produto1.getPreco()));
        System.out.println("  [" + produto2.getCodigo() + "] " + produto2.getNome() + 
                          " - R$ " + String.format("%.2f", produto2.getPreco()));
        System.out.println("  [" + produto3.getCodigo() + "] " + produto3.getNome() + 
                          " - R$ " + String.format("%.2f", produto3.getPreco()));
        System.out.println("  [" + produto4.getCodigo() + "] " + produto4.getNome() + 
                          " - R$ " + String.format("%.2f", produto4.getPreco()));
        
        // Criar pedido
        Pedido pedido = new Pedido();
        
        // Adicionar itens ao pedido
        ItemPedido item1 = new ItemPedido(produto1, 2);  // 2 notebooks
        ItemPedido item2 = new ItemPedido(produto2, 3);  // 3 mouses
        ItemPedido item3 = new ItemPedido(produto3, 2);  // 2 teclados
        ItemPedido item4 = new ItemPedido(produto4, 1);  // 1 monitor
        
        pedido.adicionarItem(item1);
        pedido.adicionarItem(item2);
        pedido.adicionarItem(item3);
        pedido.adicionarItem(item4);
        
        System.out.println("\nItens do Pedido:");
        for (ItemPedido item : pedido.getItens()) {
            System.out.println("  " + item.getProduto().getNome() + 
                              " | Qtd: " + item.getQuantidade() + 
                              " | Preço Unit: R$ " + String.format("%.2f", item.getProduto().getPreco()) +
                              " | Subtotal: R$ " + String.format("%.2f", item.getValorItem()));
        }
        
        System.out.println("\n>>> VALOR TOTAL DO PEDIDO: R$ " + 
                          String.format("%.2f", pedido.calcularValorTotal()));
    }
    
    /**
     * Demonstra o módulo de Conta Bancária.
     * Testa operações de depósito e saque em diferentes tipos de conta.
     */
    private static void demonstrarContaBancaria() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("4) MÓDULO CONTA BANCÁRIA");
        System.out.println("=".repeat(80));
        
        // Criar conta corrente
        ContaBancaria contaCorrente = new ContaBancaria("12345-6", "0001", 1000.0, TipoConta.CONTA_CORRENTE);
        
        System.out.println("\nConta Corrente:");
        System.out.println("  Agência: " + contaCorrente.getAgencia() + " | Conta: " + contaCorrente.getNumero());
        System.out.println("  Saldo inicial: R$ " + String.format("%.2f", contaCorrente.getSaldo()));
        
        // Testar depósito válido
        System.out.println("\n  Operação: Depositar R$ 500,00");
        boolean sucesso = contaCorrente.depositar(500.0);
        System.out.println("  Resultado: " + (sucesso ? "SUCESSO" : "FALHA"));
        System.out.println("  Saldo atual: R$ " + String.format("%.2f", contaCorrente.getSaldo()));
        
        // Testar depósito inválido
        System.out.println("\n  Operação: Depositar R$ -100,00 (valor inválido)");
        sucesso = contaCorrente.depositar(-100.0);
        System.out.println("  Resultado: " + (sucesso ? "SUCESSO" : "FALHA"));
        System.out.println("  Saldo atual: R$ " + String.format("%.2f", contaCorrente.getSaldo()));
        
        // Testar saque com taxa (conta corrente cobra R$ 0,50)
        System.out.println("\n  Operação: Sacar R$ 200,00 (taxa de R$ 0,50 será cobrada)");
        sucesso = contaCorrente.sacar(200.0);
        System.out.println("  Resultado: " + (sucesso ? "SUCESSO" : "FALHA"));
        System.out.println("  Saldo atual: R$ " + String.format("%.2f", contaCorrente.getSaldo()));
        System.out.println("  (Debitado: R$ 200,00 + R$ 0,50 de taxa = R$ 200,50)");
        
        // Testar saque sem saldo suficiente
        System.out.println("\n  Operação: Sacar R$ 2000,00 (saldo insuficiente)");
        sucesso = contaCorrente.sacar(2000.0);
        System.out.println("  Resultado: " + (sucesso ? "SUCESSO" : "FALHA"));
        System.out.println("  Saldo atual: R$ " + String.format("%.2f", contaCorrente.getSaldo()));
        
        // Criar conta poupança
        System.out.println("\n" + "-".repeat(80));
        ContaBancaria poupanca = new ContaBancaria("78910-1", "0001", 2000.0, TipoConta.POUPANCA);
        
        System.out.println("\nConta Poupança:");
        System.out.println("  Agência: " + poupanca.getAgencia() + " | Conta: " + poupanca.getNumero());
        System.out.println("  Saldo inicial: R$ " + String.format("%.2f", poupanca.getSaldo()));
        
        // Testar depósito
        System.out.println("\n  Operação: Depositar R$ 300,00");
        sucesso = poupanca.depositar(300.0);
        System.out.println("  Resultado: " + (sucesso ? "SUCESSO" : "FALHA"));
        System.out.println("  Saldo atual: R$ " + String.format("%.2f", poupanca.getSaldo()));
        
        // Testar saque sem taxa (poupança não cobra taxa)
        System.out.println("\n  Operação: Sacar R$ 500,00 (sem taxa)");
        sucesso = poupanca.sacar(500.0);
        System.out.println("  Resultado: " + (sucesso ? "SUCESSO" : "FALHA"));
        System.out.println("  Saldo atual: R$ " + String.format("%.2f", poupanca.getSaldo()));
        System.out.println("  (Debitado: R$ 500,00 - sem taxa)");
        
        // Testar saque que deixaria saldo exato em 0
        System.out.println("\n  Operação: Sacar R$ " + String.format("%.2f", poupanca.getSaldo()) + " (saldo total)");
        double saldoAtual = poupanca.getSaldo();
        sucesso = poupanca.sacar(saldoAtual);
        System.out.println("  Resultado: " + (sucesso ? "SUCESSO" : "FALHA"));
        System.out.println("  Saldo atual: R$ " + String.format("%.2f", poupanca.getSaldo()));
    }
}

