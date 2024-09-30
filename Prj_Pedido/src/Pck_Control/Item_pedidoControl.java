package Pck_Control;
import Pck_Model.Item_pedidoModel;
import Pck_Persistency.Item_pedidoPersistencia;

public class Item_pedidoControl {
	
		Item_pedidoModel item = new Item_pedidoModel();
		Item_pedidoPersistencia persistirItem = new Item_pedidoPersistencia();
		public void inserirItem(int A04_Id, int A02_Id, int A03_Id, int A04_Quantidade,double A04_Valor_Item)
		 {
			item.setA02_Id(A02_Id);
			item.setA03_Id(A03_Id);
			item.setA04_Id(A04_Id);
			item.setA04_Quantidade(A04_Quantidade);
			item.setA04_Valor_Item(A04_Valor_Item);
			persistirItem.inserirItem(item);
		}
	}


