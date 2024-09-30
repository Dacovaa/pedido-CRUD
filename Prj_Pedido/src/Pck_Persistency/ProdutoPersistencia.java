package Pck_Persistency;
import java.sql.CallableStatement;
import java.sql.Connection;
import Pck_Dao.Conexao_DAO;
import Pck_Model.ProdutoModel;

public class ProdutoPersistencia {
	
	CallableStatement oCall;
	Conexao_DAO oConectar = new Conexao_DAO();
	String query = "{CALL Proc_Inserir_Produto(?, ?, ?)}";
	
	public void inserirProduto(ProdutoModel produto) {
		try {
			
			Connection conn = oConectar.getConnection();
			
			if (conn != null) {
				oCall = conn.prepareCall(query);
				oCall.setString(1, produto.getA02_Descricao());
				oCall.setDouble(3, produto.getA02_Valor_Unitario());
				oCall.setInt(2, produto.getA02_Estoque());
				oCall.execute();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
			
	};
}

