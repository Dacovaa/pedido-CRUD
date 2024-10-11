package Pck_Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Pck_Persistency.ProdutoPersistencia;

public class PedidoModel {
    private int id; 
    private int clienteId; 
    private Date data; 
    private double valorTotal; 
    private List<Item_pedidoModel> itens; 
    
    public PedidoModel() {
        itens = new ArrayList<>(); 
        valorTotal = 0.0; 
    }

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

    public double getValorTotal() {
        return valorTotal; 
    }
    
    public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

    public void adicionarItem(Item_pedidoModel item) {
        this.itens.add(item);
        this.valorTotal += item.getA04_Quantidade() * item.getA04_Preco_Unitario(); 
    }
    
    public void retirarEstoque() {
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia(); 
        
        for (Item_pedidoModel item : itens) {
            int produtoId = item.getA02_id(); 
            int quantidade = item.getA04_Quantidade();

            if (produtoPersistencia.verificarEstoque(produtoId, quantidade)) {
                produtoPersistencia.atualizarEstoque(produtoId, quantidade);
                System.out.println("Estoque atualizado para o produto ID: " + produtoId);
            } else {
                System.out.println("Estoque insuficiente para o produto ID: " + produtoId);
            }
        }
    }

    public List<Item_pedidoModel> getItens() {
        return itens; 
    }

}
