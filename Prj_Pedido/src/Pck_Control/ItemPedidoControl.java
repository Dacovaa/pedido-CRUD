package Pck_Control;

import Pck_Model.Item_pedidoModel;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoControl {
    private List<Item_pedidoModel> itens;

    public ItemPedidoControl() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(int produtoId, int quantidade) {
        Item_pedidoModel novoItem = new Item_pedidoModel();
        itens.add(novoItem);
    }

    public List<Item_pedidoModel> listarItens() {
        return itens;
    }

    public void limparItens() {
        itens.clear();
    }
}
