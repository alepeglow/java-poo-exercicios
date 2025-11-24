package br.com.godev.exerciciospoo;

/**
 * Classe que representa um Item de Pedido.
 * Associa um produto a uma quantidade específica.
 */
public class ItemPedido {
    private Produto produto;
    private int quantidade;

    /**
     * Construtor completo da classe ItemPedido.
     * 
     * @param produto Produto do item
     * @param quantidade Quantidade do produto
     */
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * Calcula o valor total do item.
     * 
     * Regra: Valor do item = preço do produto × quantidade.
     * 
     * @return Valor total do item
     */
    public double getValorItem() {
        return produto.getPreco() * quantidade;
    }

    // Getters e Setters
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                ", valorItem=" + getValorItem() +
                '}';
    }
}

