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

public class PedidoNovoView extends JFrame {
    private PedidoControl pedidoControl;
    private ClienteControl clienteControl;
    private ItemPedidoControl itemPedidoControl;

    private JComboBox<ClienteModel> comboClientes;
    private JComboBox<ProdutoModel> comboProdutos;
    private JTextField txtQuantidade;
    private JButton btnAdicionarItem;
    private JButton btnSalvarPedido;
    private JTextArea textAreaItens;

    public PedidoNovoView() {
        pedidoControl = new PedidoControl();
        clienteControl = new ClienteControl();
        itemPedidoControl = new ItemPedidoControl();

        setTitle("Novo Pedido");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        comboClientes = new JComboBox<>();
        carregarClientes();
        add(new JLabel("Selecionar Cliente:"));
        add(comboClientes);

        comboProdutos = new JComboBox<>();
        carregarProdutos();
        add(new JLabel("Selecionar Produto:"));
        add(comboProdutos);

        txtQuantidade = new JTextField(5);
        add(new JLabel("Quantidade:"));
        add(txtQuantidade);

        btnAdicionarItem = new JButton("Adicionar Item");
        btnSalvarPedido = new JButton("Salvar Pedido");
        add(btnAdicionarItem);
        add(btnSalvarPedido);

        textAreaItens = new JTextArea(10, 40);
        textAreaItens.setEditable(false);
        add(new JScrollPane(textAreaItens));

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
                inserirItensPedido();
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

        // Verifica se um produto foi selecionado
        if (produtoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Adiciona o item usando o ID do produto e a quantidade
        itemPedidoControl.adicionarItem(0, produtoSelecionado.getA02_Id(), produtoSelecionado.getA02_Valor_Unitario(), quantidade); // Passando 0 como pedidoId, será atualizado depois

        // Atualiza a área de texto com a lista de itens
        textAreaItens.append("Produto ID: " + produtoSelecionado.getA02_Id() + ", Quantidade: " + quantidade + "\n");
        txtQuantidade.setText("");
    }

    private void salvarPedido() {
        ClienteModel clienteSelecionado = (ClienteModel) comboClientes.getSelectedItem();
        PedidoModel novoPedido = new PedidoModel();
        novoPedido.setClienteId(clienteSelecionado.getA01_Id());
        novoPedido.setData(new java.util.Date());

        for (Item_pedidoModel item : itemPedidoControl.listarItens()) {
            item.setA03_id(novoPedido.getId());
            novoPedido.adicionarItem(item);
        }

        // Insere o pedido no controle fora do loop
        pedidoControl.inserirPedido(novoPedido); // Insere o pedido no controle
        
        // Agora que o pedido foi inserido, atualizamos o ID do pedido
        int pedidoId = novoPedido.getId(); // Captura o ID do pedido gerado

        // Atualiza o estoque após a inserção do pedido
        for (Item_pedidoModel item : novoPedido.getItens()) {
            itemPedidoControl.inserirItemPedido(pedidoId, item.getA02_id(), item.getA04_Quantidade(), item.getA04_Preco_Unitario());
            // estoqueControl.retirarEstoque(item.getA02_id(), item.getA04_Quantidade());
        }

        JOptionPane.showMessageDialog(this, "Pedido salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        itemPedidoControl.limparItens(); // Limpa os itens do pedido após salvar
        textAreaItens.setText(""); // Limpa a área de texto

        // Exibe os itens do pedido e o pedido antes de salvar para depuração
        System.out.println("Itens do Pedido (antes de salvar): " + itemPedidoControl.listarItens());
        System.out.println("Pedido (antes de salvar): " + novoPedido);
        System.out.println("Pedido ID (depois de salvar): " + pedidoId); // Verifica o ID do pedido
    }

    private void inserirItensPedido() {
        System.out.println("Inserindo itens do pedido..."); // Verifica se o método é chamado

        for (Item_pedidoModel item : itemPedidoControl.listarItens()) {
            System.out.println("Item a ser inserido: " + item); // Exibe o item antes de inserir
            // Insere cada item no banco
            itemPedidoControl.adicionarItem(item.getA02_id(), item.getA03_id(), item.getA04_Preco_Unitario(), item.getA04_Quantidade());
        }
        JOptionPane.showMessageDialog(this, "Itens do pedido inseridos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PedidoNovoView view = new PedidoNovoView();
            view.setVisible(true);
        });
    }
}
