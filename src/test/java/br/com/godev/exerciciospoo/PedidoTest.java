package br.com.godev.exerciciospoo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para as classes Produto, ItemPedido e Pedido.
 * Testa o sistema de vendas completo.
 */
class PedidoTest {

    /**
     * Testa o cálculo do valor de um item de pedido.
     */
    @ParameterizedTest
    @CsvSource({
        "100.0, 2, 200.0",
        "50.50, 3, 151.50",
        "1000.0, 1, 1000.0",
        "25.75, 4, 103.0"
    })
    void deveCalcularValorDoItem(double preco, int quantidade, double valorEsperado) {
        Produto produto = new Produto(1, "Produto Teste", preco);
        ItemPedido item = new ItemPedido(produto, quantidade);
        
        assertEquals(valorEsperado, item.getValorItem(), 0.01);
    }

    /**
     * Testa o cálculo do valor total de um pedido com múltiplos itens.
     */
    @Test
    void deveCalcularValorTotalDoPedidoComVariosItens() {
        Produto p1 = new Produto(1, "Notebook", 3000.0);
        Produto p2 = new Produto(2, "Mouse", 50.0);
        Produto p3 = new Produto(3, "Teclado", 150.0);
        
        ItemPedido item1 = new ItemPedido(p1, 1);  // 3000.0
        ItemPedido item2 = new ItemPedido(p2, 2);  // 100.0
        ItemPedido item3 = new ItemPedido(p3, 1);  // 150.0
        
        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);
        pedido.adicionarItem(item2);
        pedido.adicionarItem(item3);
        
        double valorTotal = pedido.calcularValorTotal();
        
        assertEquals(3250.0, valorTotal, 0.01);
    }

    /**
     * Testa pedido vazio (sem itens).
     */
    @Test
    void deveRetornarZeroParaPedidoVazio() {
        Pedido pedido = new Pedido();
        
        assertEquals(0.0, pedido.calcularValorTotal());
        assertTrue(pedido.getItens().isEmpty());
    }

    /**
     * Testa pedido com um único item.
     */
    @Test
    void deveCalcularValorDePedidoComUmItem() {
        Produto produto = new Produto(1, "Monitor", 800.0);
        ItemPedido item = new ItemPedido(produto, 2);
        
        Pedido pedido = new Pedido();
        pedido.adicionarItem(item);
        
        assertEquals(1600.0, pedido.calcularValorTotal(), 0.01);
        assertEquals(1, pedido.getItens().size());
    }

    /**
     * Testa adição de múltiplos itens ao pedido.
     */
    @Test
    void deveAdicionarMultiplosItensAoPedido() {
        Pedido pedido = new Pedido();
        
        assertEquals(0, pedido.getItens().size());
        
        Produto p1 = new Produto(1, "Item 1", 100.0);
        pedido.adicionarItem(new ItemPedido(p1, 1));
        assertEquals(1, pedido.getItens().size());
        
        Produto p2 = new Produto(2, "Item 2", 200.0);
        pedido.adicionarItem(new ItemPedido(p2, 1));
        assertEquals(2, pedido.getItens().size());
        
        Produto p3 = new Produto(3, "Item 3", 300.0);
        pedido.adicionarItem(new ItemPedido(p3, 1));
        assertEquals(3, pedido.getItens().size());
    }

    /**
     * Testa pedido com grandes quantidades.
     */
    @Test
    void deveCalcularPedidoComGrandesQuantidades() {
        Produto produto = new Produto(1, "Caneta", 2.50);
        ItemPedido item = new ItemPedido(produto, 1000);
        
        Pedido pedido = new Pedido();
        pedido.adicionarItem(item);
        
        assertEquals(2500.0, pedido.calcularValorTotal(), 0.01);
    }

    /**
     * Testa integração completa do sistema de vendas.
     */
    @Test
    void deveIntegrarSistemaDeVendasCompleto() {
        // Criar produtos
        Produto notebook = new Produto(1, "Notebook Dell", 3500.0);
        Produto mouse = new Produto(2, "Mouse Logitech", 80.0);
        Produto teclado = new Produto(3, "Teclado Mecânico", 250.0);
        
        // Criar itens
        ItemPedido item1 = new ItemPedido(notebook, 2);
        ItemPedido item2 = new ItemPedido(mouse, 3);
        ItemPedido item3 = new ItemPedido(teclado, 2);
        
        // Verificar valores individuais
        assertEquals(7000.0, item1.getValorItem(), 0.01);
        assertEquals(240.0, item2.getValorItem(), 0.01);
        assertEquals(500.0, item3.getValorItem(), 0.01);
        
        // Criar pedido e adicionar itens
        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);
        pedido.adicionarItem(item2);
        pedido.adicionarItem(item3);
        
        // Verificar total
        assertEquals(7740.0, pedido.calcularValorTotal(), 0.01);
        assertEquals(3, pedido.getItens().size());
    }

    /**
     * Testa que os atributos do produto são mantidos corretamente.
     */
    @Test
    void deveManterAtributosDoProduto() {
        Produto produto = new Produto(123, "Produto Teste", 99.99);
        
        assertEquals(123, produto.getCodigo());
        assertEquals("Produto Teste", produto.getNome());
        assertEquals(99.99, produto.getPreco(), 0.001);
    }

    /**
     * Testa alteração de quantidade em item de pedido.
     */
    @Test
    void devePermitirAlteracaoDeQuantidade() {
        Produto produto = new Produto(1, "Produto", 100.0);
        ItemPedido item = new ItemPedido(produto, 2);
        
        assertEquals(200.0, item.getValorItem(), 0.01);
        
        item.setQuantidade(5);
        assertEquals(500.0, item.getValorItem(), 0.01);
    }

    /**
     * Testa pedido com valores decimais.
     */
    @Test
    void deveCalcularPedidoComValoresDecimais() {
        Produto p1 = new Produto(1, "Item A", 10.50);
        Produto p2 = new Produto(2, "Item B", 7.75);
        Produto p3 = new Produto(3, "Item C", 3.25);
        
        ItemPedido item1 = new ItemPedido(p1, 3);  // 31.50
        ItemPedido item2 = new ItemPedido(p2, 2);  // 15.50
        ItemPedido item3 = new ItemPedido(p3, 4);  // 13.00
        
        Pedido pedido = new Pedido();
        pedido.adicionarItem(item1);
        pedido.adicionarItem(item2);
        pedido.adicionarItem(item3);
        
        assertEquals(60.0, pedido.calcularValorTotal(), 0.01);
    }
}

