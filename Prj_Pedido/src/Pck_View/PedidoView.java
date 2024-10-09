package Pck_View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Pck_Control.ClienteControl;
import Pck_Control.ItemPedidoControl;
import Pck_Control.PedidoControl;
import Pck_Model.ClienteModel;
import Pck_Model.Item_pedidoModel;
import Pck_Model.PedidoModel;

public class PedidoView extends JFrame {
    private PedidoControl pedidoControl;
    private ClienteControl clienteControl;
    private ItemPedidoControl itemPedidoControl;

    private JComboBox<ClienteModel> comboClientes;
    private JComboBox<String> comboProdutos; // Supondo que você tenha uma lista de produtos como Strings
    private JTextField txtQuantidade;
    private JButton btnAdicionarItem;
    private JButton btnSalvarPedido;
    private JButton btnListarPedidos;
    private JTextArea textAreaPedidos;

    public PedidoView() {
        pedidoControl = new PedidoControl();
        clienteControl = new ClienteControl();
        itemPedidoControl = new ItemPedidoControl();

        // Configurações da janela
        setTitle("Gerenciar Pedidos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // ComboBox para selecionar cliente
        comboClientes = new JComboBox<>();
        carregarClientes();
        add(new JLabel("Selecionar Cliente:"));
        add(comboClientes);

        // ComboBox para selecionar produto
        comboProdutos = new JComboBox<>(new String[]{"Produto 1", "Produto 2", "Produto 3"}); // Exemplo de produtos
        add(new JLabel("Selecionar Produto:"));
        add(comboProdutos);

        // Campo para quantidade
        txtQuantidade = new JTextField(5);
        add(new JLabel("Quantidade:"));
        add(txtQuantidade);

        // Botões
        btnAdicionarItem = new JButton("Adicionar Item");
        btnSalvarPedido = new JButton("Salvar Pedido");
        btnListarPedidos = new JButton("Listar Pedidos");
        add(btnAdicionarItem);
        add(btnSalvarPedido);
        add(btnListarPedidos);

        // Área de texto para listar pedidos
        textAreaPedidos = new JTextArea(10, 40);
        textAreaPedidos.setEditable(false);
        add(new JScrollPane(textAreaPedidos));

        // Ações dos botões
        btnAdicionarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });

        btnSalvarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPedido();
            }
        });

        btnListarPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPedidos();
            }
        });
    }

    private void carregarClientes() {
        List<ClienteModel> clientes = clienteControl.listarCliente();
        for (ClienteModel cliente : clientes) {
            comboClientes.addItem(cliente);
        }
    }

    private void adicionarItem() {
        String produto = (String) comboProdutos.getSelectedItem();
        int quantidade;

        try {
            quantidade = Integer.parseInt(txtQuantidade.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int produtoId = getProdutoId(produto); // Obtém o ID do produto
        itemPedidoControl.adicionarItem(produtoId, quantidade);
        JOptionPane.showMessageDialog(this, "Item adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        txtQuantidade.setText(""); // Limpa o campo de quantidade
    }

    private void salvarPedido() {
        ClienteModel clienteSelecionado = (ClienteModel) comboClientes.getSelectedItem();
        PedidoModel novoPedido = new PedidoModel();
        novoPedido.setClienteId(clienteSelecionado.getA01_Id()); // ID do cliente
        novoPedido.setData(new java.util.Date()); // Data atual

        for (Item_pedidoModel item : itemPedidoControl.listarItens()) {
            novoPedido.adicionarItem(item);
        }

        pedidoControl.inserirPedido(novoPedido);
        JOptionPane.showMessageDialog(this, "Pedido salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        itemPedidoControl.limparItens(); // Limpa os itens do pedido após salvar
    }

    private void listarPedidos() {
        StringBuilder sb = new StringBuilder();
        List<PedidoModel> pedidos = pedidoControl.listarPedidos();
        for (PedidoModel pedido : pedidos) {
            sb.append("Pedido ID: ").append(pedido.getClienteId())
              .append(", Cliente ID: ").append(pedido.getClienteId())
              .append(", Data: ").append(pedido.getData())
              .append("\n");
            for (Item_pedidoModel item : pedido.getItens()) {
                sb.append("    Produto ID: ").append(item.getProdutoId())
                  .append(", Quantidade: ").append(item.getQuantidade()).append("\n");
            }
        }
        textAreaPedidos.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PedidoView view = new PedidoView();
            view.setVisible(true);
        });
    }

    private int getProdutoId(String produto) {
        // Aqui você deve implementar a lógica para retornar o ID do produto baseado na string
        // Para este exemplo, vamos apenas retornar um ID fictício
        switch (produto) {
            case "Produto 1":
                return 1;
            case "Produto 2":
                return 2;
            case "Produto 3":
                return 3;
            default:
                return -1; // Produto inválido
        }
    }
}
