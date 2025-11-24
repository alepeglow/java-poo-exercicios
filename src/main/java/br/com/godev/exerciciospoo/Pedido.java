package br.com.godev.exerciciospoo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Pedido.
 * Um pedido é composto por vários itens de pedido.
 */
public class Pedido {
    private List<ItemPedido> itens;

    /**
     * Construtor padrão da classe Pedido.
     * Inicializa a lista de itens vazia.
     */
    public Pedido() {
        this.itens = new ArrayList<>();
    }

    /**
     * Adiciona um item ao pedido.
     * 
     * @param item Item a ser adicionado ao pedido
     */
    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    /**
     * Calcula o valor total do pedido.
     * 
     * Regra: Soma o valor de todos os itens do pedido.
     * 
     * @return Valor total do pedido
     */
    public double calcularValorTotal() {
        double total = 0.0;
        for (ItemPedido item : itens) {
            total += item.getValorItem();
        }
        return total;
    }

    /**
     * Retorna a lista de itens do pedido.
     * 
     * @return Lista de itens
     */
    public List<ItemPedido> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "itens=" + itens +
                ", valorTotal=" + calcularValorTotal() +
                '}';
    }
}

