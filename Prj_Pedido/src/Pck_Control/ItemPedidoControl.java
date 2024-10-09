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
    public void adicionarItem(int produtoId, int quantidade, double precoUnitario) {
        Item_pedidoModel novoItem = new Item_pedidoModel(); // Cria um novo item
        novoItem.setProdutoId(produtoId); // Define o ID do produto
        novoItem.setQuantidade(quantidade); // Define a quantidade comprada
        novoItem.setPrecoUnitario(precoUnitario); // Define o preço unitário
        itens.add(novoItem); // Adiciona o item à lista
    }

    // Método para listar todos os itens do pedido
    public List<Item_pedidoModel> listarItens() {
        return itens; // Retorna a lista de itens
    }

    // Método para limpar todos os itens do pedido
    public void limparItens() {
        itens.clear(); // Limpa a lista de itens
    }
}
