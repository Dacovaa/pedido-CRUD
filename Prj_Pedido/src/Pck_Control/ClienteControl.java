package Pck_Control;

import java.util.List;

import Pck_Model.ClienteModel;
import Pck_Persistency.ClientePersistencia;

public class ClienteControl {
    private ClientePersistencia clientePersistencia;

    public ClienteControl() {
        clientePersistencia = new ClientePersistencia();
    }

    public void inserirCliente(String nome, String endereco, String telefone, String cpf, double credito) {
        ClienteModel cliente = new ClienteModel();
        cliente.setA01_Nome(nome);
        cliente.setA01_Endereco(endereco);
        cliente.setA01_Telefone(telefone);
        cliente.setA01_CPF(cpf);
        cliente.setA01_Credito(credito);
        clientePersistencia.inserirCliente(cliente);
    }

    public List<ClienteModel> listarCliente() {
        return clientePersistencia.listarCliente();
    }

    public void alterarCliente(int id, String nome, String endereco, String telefone, String cpf, double credito) {
        ClienteModel cliente = new ClienteModel();
        cliente.setA01_Id(id);
        cliente.setA01_Nome(nome);
        cliente.setA01_Endereco(endereco);
        cliente.setA01_Telefone(telefone);
        cliente.setA01_CPF(cpf);
        cliente.setA01_Credito(credito);
        clientePersistencia.alterarCliente(cliente);
    }

    public void excluirCliente(int id) {
        clientePersistencia.excluirCliente(id);
    }

    public List<ClienteModel> pesquisarCliente(String nome) {
        return clientePersistencia.pesquisarCliente(nome);
    }
}
