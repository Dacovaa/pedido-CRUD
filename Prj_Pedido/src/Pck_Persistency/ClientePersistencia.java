package Pck_Persistency;
import java.sql.CallableStatement;
import java.sql.Connection;

import Pck_Dao.Conexao_DAO;

import Pck_Model.ClienteModel;

public class ClientePersistencia {
	
	CallableStatement oCall;
	Conexao_DAO oConectar = new Conexao_DAO();
	String query = "{CALL Proc_Inserir_Cliente(?, ?, ?, ?, ?)}";
	
	public void inserirCliente(ClienteModel cliente) {
		try {
			
			Connection conn = oConectar.getConnection();
			
			if (conn != null) {
				oCall = conn.prepareCall(query);
				oCall.setString(1, cliente.getA01_Nome());
				oCall.setString(2, cliente.getA01_Endereco());
				oCall.setString(3, cliente.getA01_Telefone());
				oCall.setString(4, cliente.getA01_CPF());
				oCall.setDouble(5, cliente.getA01_Credito());
				oCall.execute();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
			
	};
}
