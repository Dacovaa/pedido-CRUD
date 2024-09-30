package Pck_Control;
import java.sql.Date;

import Pck_Model.PedidoModel;
import Pck_Persistency.PedidoPersistencia;

public class PedidoControl {
	
	PedidoModel pedido = new PedidoModel();
	PedidoPersistencia persistirPedido = new PedidoPersistencia();
	public void inserirPedido(int A01_Id, Date A03_Date, double A03_Valor_Total) {
		pedido.setA01_Id(A01_Id);
		pedido.setA03_Date(A03_Date);
		pedido.setA03_Valor_Total(A03_Valor_Total);
		persistirPedido.inserirPedido(pedido);
	}
}


