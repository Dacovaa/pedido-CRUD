package Pck_Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoModel {
    private int id; // ID do pedido
    private int clienteId; // ID do cliente
    private Date data; // Data do pedido
    private List<Item_pedidoModel> itens; // Lista de itens no pedido

    public PedidoModel() {
        itens = new ArrayList<>(); // Inicializa a lista de itens
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Item_pedidoModel> getItens() {
        return itens;
    }

    public void adicionarItem(Item_pedidoModel item) {
        this.itens.add(item);
    }
}
