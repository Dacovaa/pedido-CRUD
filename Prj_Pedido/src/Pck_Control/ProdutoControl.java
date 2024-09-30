package Pck_Control;
import Pck_Model.ProdutoModel;
import Pck_Persistency.ProdutoPersistencia;

public class ProdutoControl {
	
	ProdutoModel produto = new ProdutoModel();
	ProdutoPersistencia persistirProduto = new ProdutoPersistencia();
	public void inserirProduto(int A02_Estoque, String A02_Descricao, double A02_Valor_Unitario) {
		produto.setA02_Estoque(A02_Estoque);
		produto.setA02_Descricao(A02_Descricao);
		produto.setA02_Valor_Unitario(A02_Valor_Unitario);
		persistirProduto.inserirProduto(produto);
	}
}



