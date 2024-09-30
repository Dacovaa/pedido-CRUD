package Pck_Control;
import Pck_Model.ClienteModel;
import Pck_Persistency.ClientePersistencia;

public class ClienteControl {
	
	ClienteModel cliente = new ClienteModel();
	ClientePersistencia persistirCliente = new ClientePersistencia();
	public void inserirCliente(String A01_Nome, String A01_Endereco, String A01_Telefone, String A01_CPF, double A01_Credito) {
		cliente.setA01_Nome(A01_Nome);
		cliente.setA01_Endereco(A01_Endereco);
		cliente.setA01_Telefone(A01_Telefone);
		cliente.setA01_CPF(A01_CPF);
		cliente.setA01_Credito(A01_Credito);
		persistirCliente.inserirCliente(cliente);
	}
}

