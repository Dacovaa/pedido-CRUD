package Pck_Persistency;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Pck_Dao.Conexao_DAO;
import Pck_Model.ClienteModel;

public class ClientePersistencia {

    private Conexao_DAO oConectar;

    // Stored procedures
    private static final String QUERY_INSERIR = "{CALL Proc_Inserir_Cliente(?, ?, ?, ?, ?)}";
    private static final String QUERY_ATUALIZAR = "{CALL Proc_Update_Cliente(?, ?, ?, ?, ?, ?)}"; // Corrigido para incluir o CPF
    private static final String QUERY_EXCLUIR = "{CALL Proc_Deletar_Cliente(?)}";
    private static final String QUERY_LISTAR = "{CALL Proc_Listar_Clientes()}";
    private static final String QUERY_PESQUISAR = "{CALL Proc_Select_Cliente(?)}"; 
    private static final String QUERY_BUSCAR_POR_ID = "{CALL Proc_Select_Cliente_Por_Id(?)}"; // Adicionando o método de busca por ID

    public ClientePersistencia() {
        oConectar = new Conexao_DAO(); // Inicializa a conexão
    }

    // Método para inserir um cliente
    public void inserirCliente(ClienteModel cliente) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_INSERIR)) {
             
            if (conn != null) {
                oCall.setString(1, cliente.getA01_Nome());
                oCall.setString(2, cliente.getA01_Endereco());
                oCall.setString(3, cliente.getA01_Telefone());
                oCall.setString(4, cliente.getA01_CPF());
                oCall.setDouble(5, cliente.getA01_Credito());
                oCall.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os clientes
    public List<ClienteModel> listarCliente() {
        List<ClienteModel> lista = new ArrayList<>();
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_LISTAR);
             ResultSet rs = oCall.executeQuery()) {
             
            while (rs.next()) {
                ClienteModel cliente = new ClienteModel();
                cliente.setA01_Id(rs.getInt("A01_Id"));
                cliente.setA01_Nome(rs.getString("A01_Nome"));
                cliente.setA01_Endereco(rs.getString("A01_Endereco"));
                cliente.setA01_Telefone(rs.getString("A01_Telefone"));
                cliente.setA01_CPF(rs.getString("A01_CPF"));
                cliente.setA01_Credito(rs.getDouble("A01_Credito"));
                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para pesquisar um cliente por nome
    public List<ClienteModel> pesquisarCliente(String nome) {
        List<ClienteModel> lista = new ArrayList<>();
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_PESQUISAR)) {
             
            oCall.setString(1, nome);  
            try (ResultSet rs = oCall.executeQuery()) {
                while (rs.next()) {
                    ClienteModel cliente = new ClienteModel();
                    cliente.setA01_Id(rs.getInt("A01_Id"));
                    cliente.setA01_Nome(rs.getString("A01_Nome"));
                    cliente.setA01_Endereco(rs.getString("A01_Endereco"));
                    cliente.setA01_Telefone(rs.getString("A01_Telefone"));
                    cliente.setA01_CPF(rs.getString("A01_CPF"));
                    cliente.setA01_Credito(rs.getDouble("A01_Credito"));
                    lista.add(cliente);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para alterar um cliente
    public void alterarCliente(ClienteModel cliente) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_ATUALIZAR)) {
             
            oCall.setInt(1, cliente.getA01_Id());
            oCall.setString(2, cliente.getA01_Nome());
            oCall.setString(3, cliente.getA01_Endereco());
            oCall.setString(4, cliente.getA01_Telefone());
            oCall.setString(5, cliente.getA01_CPF());
            oCall.setDouble(6, cliente.getA01_Credito());
            oCall.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um cliente
    public void excluirCliente(int id) {
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_EXCLUIR)) {
             
            oCall.setInt(1, id);
            oCall.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um cliente por ID
    public ClienteModel buscarClientePorId(int id) {
        ClienteModel cliente = null;
        try (Connection conn = oConectar.getConnection(); 
             CallableStatement oCall = conn.prepareCall(QUERY_BUSCAR_POR_ID)) {
             
            oCall.setInt(1, id);
            try (ResultSet rs = oCall.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteModel();
                    cliente.setA01_Id(rs.getInt("A01_Id"));
                    cliente.setA01_Nome(rs.getString("A01_Nome"));
                    cliente.setA01_Endereco(rs.getString("A01_Endereco"));
                    cliente.setA01_Telefone(rs.getString("A01_Telefone"));
                    cliente.setA01_CPF(rs.getString("A01_CPF"));
                    cliente.setA01_Credito(rs.getDouble("A01_Credito"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
