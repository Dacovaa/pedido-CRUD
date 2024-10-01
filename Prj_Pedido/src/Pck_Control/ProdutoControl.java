package Pck_Control;

import Pck_Model.ProdutoModel;
import Pck_Persistency.ProdutoPersistencia;
import java.util.List;

public class ProdutoControl {
    ProdutoPersistencia persistirProduto = new ProdutoPersistencia();

    public void inserirProduto(int A02_Estoque, String A02_Descricao, double A02_Valor_Unitario) {
        ProdutoModel produto = new ProdutoModel();
        produto.setA02_Estoque(A02_Estoque);
        produto.setA02_Descricao(A02_Descricao);
        produto.setA02_Valor_Unitario(A02_Valor_Unitario);
        persistirProduto.inserirProduto(produto);
    }

    public List<ProdutoModel> listarProdutos() {
        return persistirProduto.listarProdutos();
    }

    public void alterarProduto(int A02_Id, int A02_Estoque, String A02_Descricao, double A02_Valor_Unitario) {
        ProdutoModel produto = new ProdutoModel();
        produto.setA02_Id(A02_Id);
        produto.setA02_Estoque(A02_Estoque);
        produto.setA02_Descricao(A02_Descricao);
        produto.setA02_Valor_Unitario(A02_Valor_Unitario);
        persistirProduto.alterarProduto(produto);
    }

    public void excluirProduto(int A02_Id) {
        persistirProduto.excluirProduto(A02_Id);
    }
}
