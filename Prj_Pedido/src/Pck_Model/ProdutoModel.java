package Pck_Model;

public class ProdutoModel {
    private int A02_Id; // ID do produto
    private int A02_Estoque; // Quantidade em estoque
    private String A02_Descricao; // Descrição do produto
    private double A02_Valor_Unitario; // Preço unitário do produto

    // Getters e Setters
    public int getA02_Id() {
        return A02_Id;
    }

    public void setA02_Id(int a02_Id) {
        A02_Id = a02_Id;
    }

    public int getA02_Estoque() {
        return A02_Estoque;
    }

    public void setA02_Estoque(int a02_Estoque) {
        A02_Estoque = a02_Estoque;
    }

    public String getA02_Descricao() {
        return A02_Descricao;
    }

    public void setA02_Descricao(String a02_Descricao) {
        A02_Descricao = a02_Descricao;
    }

    public double getA02_Valor_Unitario() {
        return A02_Valor_Unitario;
    }

    public void setA02_Valor_Unitario(double a02_Valor_Unitario) {
        A02_Valor_Unitario = a02_Valor_Unitario;
    }

    @Override
    public String toString() {
        return A02_Descricao + " - R$ " + String.format("%.2f", A02_Valor_Unitario); 
    }
}
