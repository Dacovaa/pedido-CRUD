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
import Pck_Model.ProdutoModel; // Importando o modelo do produto
import Pck_Persistency.ProdutoPersistencia;

public class PedidoView extends JFrame {
    private PedidoControl pedidoControl;
    private ClienteControl clienteControl;
    private ItemPedidoControl itemPedidoControl;

    private JComboBox<ClienteModel> comboClientes;
    private JComboBox<ProdutoModel> comboProdutos;
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
        comboProdutos = new JComboBox<>();
        carregarProdutos();
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

    private void carregarProdutos() {
        ProdutoPersistencia produtoPersistencia = new ProdutoPersistencia();
        List<ProdutoModel> produtos = produtoPersistencia.listarProdutos();
        comboProdutos.removeAllItems(); // Limpa o combo box antes de adicionar novos produtos
        
        for (ProdutoModel produto : produtos) {
            comboProdutos.addItem(produto); // Adiciona o objeto ProdutoModel diretamente
        }
    }

    private void adicionarItem() {
        ProdutoModel produtoSelecionado = (ProdutoModel) comboProdutos.getSelectedItem();
        int quantidade;

        try {
            quantidade = Integer.parseInt(txtQuantidade.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Adiciona o item usando o ID do produto e a quantidade
        itemPedidoControl.adicionarItem(produtoSelecionado.getA02_Id(), quantidade, produtoSelecionado.getA02_Valor_Unitario());

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
            sb.append("Pedido ID: ").append(pedido.getId())
              .append(", Cliente ID: ").append(pedido.getClienteId())
              .append(", Data: ").append(pedido.getData())
              .append("\n");
            for (Item_pedidoModel item : pedido.getItens()) {
            	double totalItem = item.getQuantidade() * item.getPrecoUnitario();
                sb.append("    Produto ID: ").append(item.getProdutoId())
                  .append(", Quantidade: ").append(item.getQuantidade())
                  .append(", Preço Unitário: ").append(item.getPrecoUnitario())
                  .append(", Total: R$ ").append(totalItem)
                  .append("\n");
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
}
