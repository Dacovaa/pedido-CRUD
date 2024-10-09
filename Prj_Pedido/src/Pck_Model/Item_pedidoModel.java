package Pck_Model;

public class Item_pedidoModel {
    private int id; // Identificador do item
    private int pedidoId; // ID do pedido ao qual este item pertence
    private int produtoId; // ID do produto
    private int quantidade; // Quantidade comprada
    private double precoUnitario; // Preço unitário do produto

    // Construtor padrão
    public Item_pedidoModel() {
    }

    // Construtor com parâmetros
    public Item_pedidoModel(int produtoId, int quantidade, double precoUnitario) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    // Getters e Setters
    public int getId() {
        return id; // Retorna o ID do item
    }

    public void setId(int id) {
        this.id = id; // Define o ID do item
    }

    public int getPedidoId() {
        return pedidoId; // Retorna o ID do pedido
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId; // Define o ID do pedido
    }

    public int getProdutoId() {
        return produtoId; // Retorna o ID do produto
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId; // Define o ID do produto
    }

    public int getQuantidade() {
        return quantidade; // Retorna a quantidade comprada
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade; // Define a quantidade comprada
    }

    public double getPrecoUnitario() {
        return precoUnitario; // Retorna o preço unitário do produto
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario; // Define o preço unitário do produto
    }
}
