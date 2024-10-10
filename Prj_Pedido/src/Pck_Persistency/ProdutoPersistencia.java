package Pck_Persistency;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Pck_Dao.Conexao_DAO;
import Pck_Model.ProdutoModel;

public class ProdutoPersistencia {
    Conexao_DAO oConectar = new Conexao_DAO();
    String queryInserir = "{CALL Proc_Inserir_Produto(?, ?, ?)}";
    String queryAlterar = "{CALL Proc_Update_Produto(?, ?, ?, ?)}";
    String queryExcluir = "{CALL Proc_Deletar_Produto(?)}";
    String querySelect = "{CALL Proc_Select_Produto(?)}"; 
    String queryListar = "{CALL Proc_Listar_Produtos()}"; 

    // Método para inserir produto
    public void inserirProduto(ProdutoModel produto) {
        try {
            Connection conn = oConectar.getConnection();
            CallableStatement oCall = conn.prepareCall(queryInserir);
            oCall.setString(1, produto.getA02_Descricao());
            oCall.setDouble(2, produto.getA02_Valor_Unitario());
            oCall.setInt(3, produto.getA02_Estoque());
            oCall.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public boolean verificarEstoque(int produtoId, int quantidade) {
        String query = "SELECT A02_Estoque FROM produto_02 WHERE A02_Id = ?";
        try (Connection conn = oConectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int estoqueAtual = rs.getInt("A02_Estoque");
                return estoqueAtual >= quantidade; // Retorna true se houver estoque suficiente
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Retorna false se não encontrou o produto ou houve erro
    }

    // Método para atualizar o estoque
    public void atualizarEstoque(int produtoId, int quantidade) {
        String query = "UPDATE produto_02 SET A02_Estoque = A02_Estoque - ? WHERE A02_Id = ?";
        try (Connection conn = oConectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, quantidade);
            stmt.setInt(2, produtoId);
            stmt.executeUpdate(); // Executa a atualização
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para listar produtos
    public List<ProdutoModel> listarProdutos() {
        List<ProdutoModel> lista = new ArrayList<>();
        try {
            Connection conn = oConectar.getConnection();
            CallableStatement oCall = conn.prepareCall(queryListar);
            ResultSet rs = oCall.executeQuery();
            while (rs.next()) {
                ProdutoModel produto = new ProdutoModel();
                produto.setA02_Id(rs.getInt("A02_Id"));
                produto.setA02_Estoque(rs.getInt("A02_Estoque"));
                produto.setA02_Descricao(rs.getString("A02_Descricao"));
                produto.setA02_Valor_Unitario(rs.getDouble("A02_Valor_Unitario"));
                lista.add(produto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    // Método para alterar produto
    public void alterarProduto(ProdutoModel produto) {
        try {
            Connection conn = oConectar.getConnection();
            CallableStatement oCall = conn.prepareCall(queryAlterar);
            oCall.setInt(1, produto.getA02_Id());
            oCall.setString(2, produto.getA02_Descricao());
            oCall.setDouble(3, produto.getA02_Valor_Unitario());
            oCall.setInt(4, produto.getA02_Estoque());
            oCall.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Método para excluir produto
    public void excluirProduto(int A02_Id) {
        try {
            Connection conn = oConectar.getConnection();
            CallableStatement oCall = conn.prepareCall(queryExcluir);
            oCall.setInt(1, A02_Id);
            oCall.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Método para pesquisar produto por nome
    public List<ProdutoModel> pesquisarProduto(String descricao) {
        List<ProdutoModel> lista = new ArrayList<>();
        try {
            Connection conn = oConectar.getConnection();
            CallableStatement oCall = conn.prepareCall(querySelect);
            oCall.setString(1, descricao); // Certifique-se de que o parâmetro está sendo usado corretamente
            ResultSet rs = oCall.executeQuery();
            
            while (rs.next()) {
                ProdutoModel produto = new ProdutoModel();
                produto.setA02_Id(rs.getInt("A02_Id"));
                produto.setA02_Estoque(rs.getInt("A02_Estoque"));
                produto.setA02_Descricao(rs.getString("A02_Descricao"));
                produto.setA02_Valor_Unitario(rs.getDouble("A02_Valor_Unitario"));
                lista.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
