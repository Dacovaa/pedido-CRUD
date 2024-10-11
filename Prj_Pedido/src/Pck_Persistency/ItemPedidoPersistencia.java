package Pck_Persistency;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Pck_Control.ItemPedidoControl;
import Pck_Dao.Conexao_DAO;
import Pck_Model.Item_pedidoModel;

public class ItemPedidoPersistencia {

    private Conexao_DAO oConectar;

    private static final String QUERY_INSERIR = "{CALL Proc_Inserir_Item_Pedido(?, ?, ?, ?)}";
    private static final String QUERY_LISTAR = "{CALL Proc_Listar_Itens_Pedido()}";
    private static final String QUERY_PESQUISAR_POR_PEDIDO = "{CALL Proc_Select_Itens_Pedido_Por_PedidoId(?)}";

    public ItemPedidoPersistencia() {
        oConectar = new Conexao_DAO();
    }

    public void inserirItemPedido(int pedidoId, int produtoId, int quantidade, double precoUnitario) {
    	ItemPedidoControl itemPedidoPersistencia = new ItemPedidoControl();
        String sql = "INSERT INTO item_pedido_04 (A02_Id, A03_Id, A04_Preco_Unitario, A04_Quantidade) VALUES (?, ?, ?, ?)";
        try (Connection conn = oConectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, produtoId);
            stmt.setInt(2, pedidoId);
            stmt.setDouble(3, precoUnitario);
            stmt.setInt(4, quantidade);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
