package Pck_Persistency;
import java.sql.CallableStatement;
import java.sql.Connection;
import Pck_Dao.Conexao_DAO;
import Pck_Model.Item_pedidoModel;

public class Item_pedidoPersistencia {
	
	CallableStatement oCall;
	Conexao_DAO oConectar = new Conexao_DAO();
	String query = "{CALL Proc_Inserir_Item(?, ?, ?, ?, ?)}";
	
	public void inserirItem(Item_pedidoModel item) {
		try {
			
			Connection conn = oConectar.getConnection();
			
			if (conn != null) {
				oCall = conn.prepareCall(query);
				oCall.setInt(1, item.getA04_Id());
				oCall.setInt(3, item.getA02_Id());
				oCall.setInt(2, item.getA03_Id());
				oCall.setInt(4, item.getA04_Quantidade());
				oCall.setDouble(5, item.getA04_Valor_Item());
				oCall.execute();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
			
	};
}
