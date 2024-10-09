package Pck_Control;

import java.util.List;

import Pck_Model.ProdutoModel;
import Pck_Persistency.ProdutoPersistencia;

public class ProdutoControl {
    private ProdutoPersistencia produtoPersistencia;

    public ProdutoControl() {
        produtoPersistencia = new ProdutoPersistencia();
    }

    public void inserirProduto(int estoque, String descricao, double valorUnitario) {
        ProdutoModel produto = new ProdutoModel();
        produto.setA02_Descricao(descricao);
        produto.setA02_Estoque(estoque);
        produto.setA02_Valor_Unitario(valorUnitario);
        produtoPersistencia.inserirProduto(produto);
    }

    public List<ProdutoModel> listarProdutos() {
        return produtoPersistencia.listarProdutos();
    }

    public void alterarProduto(int id, int estoque, String descricao, double valorUnitario) {
        ProdutoModel produto = new ProdutoModel();
        produto.setA02_Id(id);
        produto.setA02_Descricao(descricao);
        produto.setA02_Estoque(estoque);
        produto.setA02_Valor_Unitario(valorUnitario);
        produtoPersistencia.alterarProduto(produto);
    }

    public void excluirProduto(int id) {
        produtoPersistencia.excluirProduto(id);
    }

    public List<ProdutoModel> pesquisarProduto(String descricao) {
        return produtoPersistencia.pesquisarProduto(descricao); // Retorna a lista de produtos encontrados
    }

}
