package Pck_Persistency;
import java.sql.CallableStatement;
import java.sql.Connection;
import Pck_Dao.Conexao_DAO;
import Pck_Model.PedidoModel;

public class PedidoPersistencia {
	
	CallableStatement oCall;
	Conexao_DAO oConectar = new Conexao_DAO();
	String query = "{CALL Proc_Inserir_Pedido(?, ?, ?)}";
	
	public void inserirPedido(PedidoModel pedido) {
		try {
			
			Connection conn = oConectar.getConnection();
			
			if (conn != null) {
				oCall = conn.prepareCall(query);
				oCall.setInt(1, pedido.getA01_Id());
				oCall.setDate(2, pedido.getA03_Date());
				oCall.setDouble(3, pedido.getA03_Valor_Total());
				oCall.execute();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
			
	};
}




