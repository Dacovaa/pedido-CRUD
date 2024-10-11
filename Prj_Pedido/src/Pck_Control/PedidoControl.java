package Pck_Control;

import Pck_Model.PedidoModel;
import Pck_Persistency.PedidoPersistencia;

import java.util.ArrayList;
import java.util.List;

public class PedidoControl {
    private PedidoPersistencia pedidoPersistencia;

    public PedidoControl() {
        this.pedidoPersistencia = new PedidoPersistencia();
    }

    public int inserirPedido(PedidoModel pedido) {
        if (pedido != null) {
            pedidoPersistencia.inserirPedido(pedido);
            return pedido.getId();
        } else {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
    }

    public List<PedidoModel> listarPedidos() {
        return pedidoPersistencia.listarPedidos();
    }

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

    public void excluirPedido(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do pedido deve ser maior que zero.");
        }
        pedidoPersistencia.excluirPedido(id); // Chama o método de persistência para excluir o pedido
    }
}
