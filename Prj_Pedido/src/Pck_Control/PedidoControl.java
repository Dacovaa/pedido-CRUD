package Pck_Control;

import Pck_Model.PedidoModel;
import Pck_Persistency.PedidoPersistencia;

import java.util.ArrayList;
import java.util.List;

public class PedidoControl {
    private PedidoPersistencia pedidoPersistencia;

    public PedidoControl() {
        this.pedidoPersistencia = new PedidoPersistencia(); // Inicializa a persistência
    }

    // Método para inserir o pedido e retornar o ID gerado
    public int inserirPedido(PedidoModel pedido) {
        // Verifica se o pedido não é nulo
        if (pedido != null) {
            pedidoPersistencia.inserirPedido(pedido); // Insere o pedido no banco de dados
            return pedido.getId(); // Retorna o ID do pedido (presumindo que o ID será atualizado após a inserção)
        } else {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
    }

    // Método para listar todos os pedidos
    public List<PedidoModel> listarPedidos() {
        return pedidoPersistencia.listarPedidos(); // Retorna a lista de pedidos do banco de dados
    }

    // Método para pesquisar pedidos por nome de cliente ou ID do pedido/cliente
    public List<PedidoModel> pesquisarPedidos(String pesquisa) {
        List<PedidoModel> pedidosEncontrados = new ArrayList<>();
        for (PedidoModel pedido : listarPedidos()) {
            // Verifica se o pedido corresponde ao ID ou ao ID do cliente
            if (String.valueOf(pedido.getId()).equals(pesquisa) || String.valueOf(pedido.getClienteId()).equals(pesquisa)) {
                pedidosEncontrados.add(pedido);
            }
        }
        return pedidosEncontrados;
    }

    // Método para excluir um pedido pelo ID
    public void excluirPedido(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do pedido deve ser maior que zero.");
        }
        pedidoPersistencia.excluirPedido(id); // Chama o método de persistência para excluir o pedido
    }
}
