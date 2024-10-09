package Pck_Persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import Pck_Model.Item_pedidoModel;
import Pck_Dao.Conexao_DAO;

public class ItemPedidoPersistencia {

    private Connection connection;

    public ItemPedidoPersistencia() {
        // Obtém a conexão através da classe Conexao_DAO
        Conexao_DAO conexaoDAO = new Conexao_DAO();
        this.connection = conexaoDAO.getConnection();
    }

    // Método para inserir um item de pedido na tabela item_pedido_04
    public void inserirItemPedido(Item_pedidoModel item) {
        String sql = "INSERT INTO item_pedido_04 (A03_Id, A02_Id, A04_Quantidade, A04_Preco_Unitario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, item.getPedidoId());          // A03_Id (ID do pedido)
            stmt.setInt(2, item.getProdutoId());         // A02_Id (ID do produto)
            stmt.setInt(3, item.getQuantidade());        // A04_Quantidade
            stmt.setDouble(4, item.getPrecoUnitario());  // A04_Preco_Unitario

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para inserir múltiplos itens de um pedido
    public void inserirItensPedido(List<Item_pedidoModel> itens) {
        String sql = "INSERT INTO item_pedido_04 (A03_Id, A02_Id, A04_Quantidade, A04_Preco_Unitario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Item_pedidoModel item : itens) {
                stmt.setInt(1, item.getPedidoId());        // A03_Id (ID do pedido)
                stmt.setInt(2, item.getProdutoId());       // A02_Id (ID do produto)
                stmt.setInt(3, item.getQuantidade());      // A04_Quantidade
                stmt.setDouble(4, item.getPrecoUnitario()); // A04_Preco_Unitario

                stmt.addBatch(); // Adiciona ao lote de operações
            }

            stmt.executeBatch(); // Executa o lote de inserções
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item_pedidoModel> listarItensPedido() {
        // TODO: Implementar a lógica para listar os itens do pedido
        return null;
    }
}
