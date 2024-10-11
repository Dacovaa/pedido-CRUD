package Pck_Control;

import Pck_Model.Item_pedidoModel;
import Pck_Persistency.ItemPedidoPersistencia; // Certifique-se de importar a classe correta
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoControl {
    private List<Item_pedidoModel> itens;
    private ItemPedidoPersistencia itemPedidoPersistencia;

    public ItemPedidoControl() {
        this.itens = new ArrayList<>();
        this.itemPedidoPersistencia = new ItemPedidoPersistencia();
    }

    public void adicionarItem(int pedidoId, int produtoId, double precoUnitario, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        Item_pedidoModel novoItem = new Item_pedidoModel();
        novoItem.setA03_id(pedidoId);
        novoItem.setA02_id(produtoId);
        novoItem.setA04_Preco_Unitario(precoUnitario);
        novoItem.setA04_Quantidade(quantidade);
        itens.add(novoItem);
    }

    public List<Item_pedidoModel> listarItens() {
        return new ArrayList<>(itens);
    }

    public void inserirItemPedido(int pedidoId, int produtoId, int quantidade, double precoUnitario) {
        adicionarItem(pedidoId, produtoId, precoUnitario, quantidade);
        itemPedidoPersistencia.inserirItemPedido(pedidoId, produtoId, quantidade, precoUnitario); 
    }

    public void limparItens() {
        itens.clear();
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;
        for (Item_pedidoModel item : itens) {
            valorTotal += item.getA04_Quantidade() * item.getA04_Preco_Unitario();
        }
        return valorTotal;
    }
}
