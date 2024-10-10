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

    // Stored procedures (atualize com os nomes corretos das suas procedures)
    private static final String QUERY_INSERIR = "{CALL Proc_Inserir_Pedido(?, ?, ?)}"; 
    private static final String QUERY_LISTAR = "{CALL Proc_Listar_Pedidos()}"; 
    private static final String QUERY_EXCLUIR = "{CALL Proc_Deletar_Pedido(?)}"; 
    private static final String QUERY_BUSCAR_POR_ID = "{CALL Proc_Select_Pedidos(?)}"; 

    public PedidoPersistencia() {
        oConectar = new Conexao_DAO(); // Inicializa a conexão
    }

    // Método para inserir um pedido
    public void inserirPedido(PedidoModel pedido) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_INSERIR)) {
             
            if (conn != null) {
                oCall.setInt(1, pedido.getClienteId()); // Define o ID do cliente
                oCall.setDate(2, new java.sql.Date(pedido.getData().getTime())); // Converte java.util.Date para java.sql.Date
                oCall.setDouble(3, pedido.getValorTotal()); // Define o valor total do pedido
                oCall.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os pedidos
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

    // Método para excluir um pedido
    public void excluirPedido(int id) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_EXCLUIR)) {
             
            oCall.setInt(1, id); // Define o ID do pedido a ser excluído
            oCall.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um pedido por ID
    public PedidoModel buscarPedidoPorId(int id) {
        PedidoModel pedido = null;
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_BUSCAR_POR_ID)) {
             
            oCall.setInt(1, id); // Define o ID do pedido a ser buscado
            try (ResultSet rs = oCall.executeQuery()) {
                if (rs.next()) {
                    pedido = new PedidoModel();
                    pedido.setId(rs.getInt("A03_Id")); // Nome da coluna atualizado
                    pedido.setClienteId(rs.getInt("A01_Id")); // Nome da coluna atualizado
                    pedido.setData(rs.getDate("A03_Data")); // Nome da coluna atualizado
                    pedido.setValorTotal(rs.getDouble("A03_Valor_Total")); // Nome da coluna atualizado
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedido;
    }
}
