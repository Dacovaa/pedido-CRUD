package Pck_Control;

import Pck_Model.Item_pedidoModel;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoControl {
    private List<Item_pedidoModel> itens; // Lista de itens do pedido

    public ItemPedidoControl() {
        this.itens = new ArrayList<>(); // Inicializa a lista de itens
    }

    // Método para adicionar um item ao pedido
    public void adicionarItem(int pedidoId, int produtoId, int quantidade, double precoUnitario) {
        // Verifica se a quantidade é válida
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        
        Item_pedidoModel novoItem = new Item_pedidoModel(); // Cria um novo item
        novoItem.setPedidoId(pedidoId); // Define o ID do pedido associado
        novoItem.setProdutoId(produtoId); // Define o ID do produto
        novoItem.setQuantidade(quantidade); // Define a quantidade comprada
        novoItem.setPrecoUnitario(precoUnitario); // Define o preço unitário
        itens.add(novoItem); // Adiciona o item à lista
    }

    // Método para listar todos os itens do pedido
    public List<Item_pedidoModel> listarItens() {
        return new ArrayList<>(itens); // Retorna uma cópia da lista de itens
    }
    public void inserirItemPedido(int pedidoId, int produtoId, int quantidade, double precoUnitario) {
        ItemPedidoControl itemPedidoPersistencia = null;
		itemPedidoPersistencia.inserirItemPedido(pedidoId, produtoId, quantidade, precoUnitario);
    }

    // Método para limpar todos os itens do pedido
    public void limparItens() {
        itens.clear(); // Limpa a lista de itens
    }

    // Método para calcular o valor total dos itens
    public double calcularValorTotal() {
        double valorTotal = 0.0;
        for (Item_pedidoModel item : itens) {
            valorTotal += item.getQuantidade() * item.getPrecoUnitario(); // Calcula o valor total
        }
        return valorTotal; // Retorna o valor total
    }
}
