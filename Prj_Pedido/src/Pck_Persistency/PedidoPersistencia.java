package Pck_Persistency;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Pck_Dao.Conexao_DAO;
import Pck_Model.PedidoModel;

public class PedidoPersistencia {

    private Conexao_DAO oConectar;

    private static final String QUERY_INSERIR = "{CALL Proc_Inserir_Pedido(?, ?, ?)}"; 
    private static final String QUERY_LISTAR = "{CALL Proc_Listar_Pedidos()}"; 
    private static final String QUERY_EXCLUIR = "{CALL Proc_Deletar_Pedido(?)}"; 
    private static final String QUERY_BUSCAR_POR_ID = "{CALL Proc_Select_Pedidos(?)}"; 

    public PedidoPersistencia() {
        oConectar = new Conexao_DAO(); 
    }

    public void inserirPedido(PedidoModel pedido) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_INSERIR)) {
             
            if (conn != null) {
                oCall.setInt(1, pedido.getClienteId()); 
                oCall.setDate(2, new java.sql.Date(pedido.getData().getTime())); // Converte java.util.Date para java.sql.Date
                oCall.setDouble(3, pedido.getValorTotal()); 
                oCall.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PedidoModel> listarPedidos() {
        List<PedidoModel> lista = new ArrayList<>();
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_LISTAR);
             ResultSet rs = oCall.executeQuery()) {
             
            while (rs.next()) {
                PedidoModel pedido = new PedidoModel();
                pedido.setId(rs.getInt("A03_Id"));
                pedido.setClienteId(rs.getInt("A01_Id"));
                pedido.setData(rs.getDate("A03_Data"));
                pedido.setValorTotal(rs.getDouble("A03_Valor_Total"));
                lista.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void excluirPedido(int id) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_EXCLUIR)) {
             
            oCall.setInt(1, id);
            oCall.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public PedidoModel buscarPedidoPorId(int id) {
        PedidoModel pedido = null;
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_BUSCAR_POR_ID)) {
             
            oCall.setInt(1, id); 
            try (ResultSet rs = oCall.executeQuery()) {
                if (rs.next()) {
                    pedido = new PedidoModel();
                    pedido.setId(rs.getInt("A03_Id")); 
                    pedido.setClienteId(rs.getInt("A01_Id")); 
                    pedido.setData(rs.getDate("A03_Data"));
                    pedido.setValorTotal(rs.getDouble("A03_Valor_Total"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedido;
    }
}
