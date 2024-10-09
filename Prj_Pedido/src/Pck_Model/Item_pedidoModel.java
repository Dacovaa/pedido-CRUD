package Pck_Model;

public class Item_pedidoModel {
    private int id; // Identificador do item
    private int pedidoId; // ID do pedido ao qual este item pertence
    private int produtoId; // ID do produto
    private int quantidade; // Quantidade comprada
    private double precoUnitario; // Preço unitário do produto

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario; // Getter para o preço unitário
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario; // Setter para o preço unitário
    }
}
