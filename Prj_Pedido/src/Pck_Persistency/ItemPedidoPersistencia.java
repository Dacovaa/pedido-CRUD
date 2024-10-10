package Pck_Persistency;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Pck_Dao.Conexao_DAO;
import Pck_Model.Item_pedidoModel;

public class ItemPedidoPersistencia {

    private Conexao_DAO oConectar;

    // Stored procedures
    private static final String QUERY_INSERIR = "{CALL Proc_Inserir_Item_Pedido(?, ?, ?, ?)}";
    private static final String QUERY_LISTAR = "{CALL Proc_Listar_Itens_Pedido()}";
    private static final String QUERY_PESQUISAR_POR_PEDIDO = "{CALL Proc_Select_Itens_Pedido_Por_PedidoId(?)}";

    public ItemPedidoPersistencia() {
        oConectar = new Conexao_DAO(); // Inicializa a conexão
    }

    // Método para inserir um item de pedido
    public void inserirItemPedido(Item_pedidoModel item) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_INSERIR)) {

            if (conn != null) {
                oCall.setInt(1, item.getA02_id());  // ID do Pedido
                oCall.setInt(2, item.getA03_id());  // ID do Produto
                oCall.setDouble(3, item.getA04_Preco_Unitario());
                oCall.setInt(4, item.getA04_Quantidade());
                oCall.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os itens de pedido
    public List<Item_pedidoModel> listarItensPedido() {
        List<Item_pedidoModel> lista = new ArrayList<>();
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_LISTAR);
             ResultSet rs = oCall.executeQuery()) {

            while (rs.next()) {
                Item_pedidoModel item = new Item_pedidoModel();
                item.setA02_id(rs.getInt("A02_Id"));
                item.setA03_id(rs.getInt("A03_Id"));
                item.setA04_Preco_Unitario(rs.getDouble("A04_Preco_Unitario"));
                item.setA04_Quantidade(rs.getInt("A04_Quantidade"));
                lista.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para buscar itens de pedido por ID do pedido
    public List<Item_pedidoModel> buscarItensPorPedidoId(int pedidoId) {
        List<Item_pedidoModel> lista = new ArrayList<>();
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_PESQUISAR_POR_PEDIDO)) {
             
            oCall.setInt(1, pedidoId);  
            try (ResultSet rs = oCall.executeQuery()) {
                while (rs.next()) {
                    Item_pedidoModel item = new Item_pedidoModel();
                    item.setA02_id(rs.getInt("A02_Id"));
                    item.setA03_id(rs.getInt("A03_Id"));
                    item.setA04_Preco_Unitario(rs.getDouble("A04_Preco_Unitario"));
                    item.setA04_Quantidade(rs.getInt("A04_Quantidade"));
                    lista.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
