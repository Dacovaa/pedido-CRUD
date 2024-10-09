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

    // Stored procedures
    private static final String QUERY_INSERIR = "{CALL Proc_Inserir_Pedido(?, ?, ?)}";
    private static final String QUERY_ATUALIZAR = "{CALL Proc_Update_Pedido(?, ?, ?)}"; // Adicione parâmetros conforme necessário
    private static final String QUERY_EXCLUIR = "{CALL Proc_Deletar_Pedido(?)}";
    private static final String QUERY_LISTAR = "{CALL Proc_Listar_Pedidos()}";
    private static final String QUERY_BUSCAR_POR_ID = "{CALL Proc_Select_Pedido_Por_Id(?)}"; // Adicionando o método de busca por ID

    public PedidoPersistencia() {
        oConectar = new Conexao_DAO(); // Inicializa a conexão
    }

    // Método para inserir um pedido
    public void inserirPedido(PedidoModel pedido) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_INSERIR)) {
             
            if (conn != null) {
                oCall.setInt(1, pedido.getClienteId());
                oCall.setDate(2, new java.sql.Date(pedido.getData().getTime())); // Converter Date para SQL
                oCall.setDouble(3, pedido.getValorTotal());
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
                pedido.setId(rs.getInt("id")); // Substitua pelo nome correto da coluna
                pedido.setClienteId(rs.getInt("cliente_id")); // Substitua pelo nome correto da coluna
                pedido.setData(rs.getDate("data")); // Substitua pelo nome correto da coluna
                pedido.setValorTotal(rs.getDouble("valor_total")); // Substitua pelo nome correto da coluna
                lista.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para alterar um pedido
    public void alterarPedido(PedidoModel pedido) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_ATUALIZAR)) {
             
            oCall.setInt(1, pedido.getId()); // Adicione o ID do pedido
            oCall.setInt(2, pedido.getClienteId());
            oCall.setDouble(3, pedido.getValorTotal());
            oCall.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um pedido
    public void excluirPedido(int id) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_EXCLUIR)) {
             
            oCall.setInt(1, id);
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
             
            oCall.setInt(1, id);
            try (ResultSet rs = oCall.executeQuery()) {
                if (rs.next()) {
                    pedido = new PedidoModel();
                    pedido.setId(rs.getInt("id")); // Substitua pelo nome correto da coluna
                    pedido.setClienteId(rs.getInt("cliente_id")); // Substitua pelo nome correto da coluna
                    pedido.setData(rs.getDate("data")); // Substitua pelo nome correto da coluna
                    pedido.setValorTotal(rs.getDouble("valor_total")); // Substitua pelo nome correto da coluna
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedido;
    }
}
