package Pck_Control;

import Pck_Model.PedidoModel;

import java.util.ArrayList;
import java.util.List;

public class PedidoControl {
    private List<PedidoModel> pedidos;

    public PedidoControl() {
        this.pedidos = new ArrayList<>();
    }

    public void inserirPedido(PedidoModel pedido) {
        pedidos.add(pedido);
    }

    public List<PedidoModel> listarPedidos() {
        return pedidos;
    }
}
