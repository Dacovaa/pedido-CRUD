package Pck_Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoModel {
    private int id; // ID do pedido
    private int clienteId; // ID do cliente que fez o pedido
    private Date data; // Data em que o pedido foi realizado
    private double valorTotal; // Valor total do pedido
    private List<Item_pedidoModel> itens; // Lista de itens do pedido
    
    public PedidoModel() {
        itens = new ArrayList<>(); // Inicializa a lista de itens
        valorTotal = 0.0; // Inicializa o valor total como 0
    }

    // Getters e Setters
    public int getId() {
        return id; // Retorna o ID do pedido
    }

    public void setId(int id) {
        this.id = id; // Define o ID do pedido
    }

    public int getClienteId() {
        return clienteId; // Retorna o ID do cliente
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId; // Define o ID do cliente
    }

    public Date getData() {
        return data; // Retorna a data do pedido
    }

    public void setData(Date data) {
        this.data = data; // Define a data do pedido
    }

    public double getValorTotal() {
        return valorTotal; // Retorna o valor total do pedido
    }
    
    public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	// Método para adicionar um item à lista de itens do pedido
    public void adicionarItem(Item_pedidoModel item) {
        this.itens.add(item); // Adiciona o item à lista
        this.valorTotal += item.getQuantidade() * item.getPrecoUnitario(); // Atualiza o valor total do pedido
    }

    public List<Item_pedidoModel> getItens() {
        return itens; // Retorna a lista de itens do pedido
    }

}
