package Pck_View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Pck_Control.PedidoControl;
import Pck_Control.ProdutoControl;
import Pck_Model.PedidoModel;

public class PedidoView extends JFrame {
    private PedidoControl pedidoControl;
    private JTextArea textAreaPedidos;
    private JTextField txtPesquisa;
    private JTextField txtExclusaoId;
    private JButton btnNovoPedido;
    private JButton btnPesquisar;
    private JButton btnExcluirPedido;
    private JButton btnListar; // Novo botão para listar pedidos

    public PedidoView(PedidoControl pedidocontrol) {
        pedidoControl = new PedidoControl();

        // Configurações da janela
        setTitle("Gerenciar Pedidos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de botões e campos de entrada no topo
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new GridLayout(1, 5, 5, 5)); // Usar GridLayout para melhor distribuição
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento

        btnNovoPedido = new JButton("Novo Pedido");
        btnPesquisar = new JButton("Pesquisar");
        btnExcluirPedido = new JButton("Excluir Pedido");
        btnListar = new JButton("Listar"); // Inicializando o botão de listar

        txtPesquisa = new JTextField(15); // Campo para pesquisa (nome ou ID)
        txtExclusaoId = new JTextField(5); // Campo para inserir ID para exclusão

        panelBotoes.add(new JLabel("Pesquisar (ID):"));
        panelBotoes.add(txtPesquisa);
        panelBotoes.add(btnPesquisar);
        panelBotoes.add(new JLabel("Excluir Pedido (ID):"));
        panelBotoes.add(txtExclusaoId);
        panelBotoes.add(btnExcluirPedido);
        panelBotoes.add(btnNovoPedido);

        add(panelBotoes, BorderLayout.NORTH); // Painel de botões no topo

        // Área de texto para listar pedidos
        textAreaPedidos = new JTextArea(20, 50);
        textAreaPedidos.setEditable(false);
        textAreaPedidos.setLineWrap(true); // Quebra de linha
        textAreaPedidos.setWrapStyleWord(true); // Quebra de linha com palavras inteiras
        textAreaPedidos.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fonte monoespaçada para melhor formatação
        add(new JScrollPane(textAreaPedidos), BorderLayout.CENTER);

        // Painel inferior para o botão de listar
        JPanel panelListar = new JPanel();
        panelListar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento
        panelListar.add(btnListar); // Adicionando o botão de listar
        add(panelListar, BorderLayout.SOUTH); // Painel de botões na parte inferior

        // Listar pedidos ao abrir
        listarPedidos();

        // Ações dos botões
        btnNovoPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaNovoPedido();
            }
        });

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarPedidos();
            }
        });

        btnExcluirPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirPedido();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPedidos(); // Atualiza a lista de pedidos ao clicar no botão "Listar"
            }
        });
        
        // Botão "Voltar"
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new PaginaInicialView().setVisible(true); // Abre a tela inicial
            }
        });
        panelListar.add(btnVoltar);
    }

    // Método para listar pedidos
    private void listarPedidos() {
        StringBuilder sb = new StringBuilder();
        List<PedidoModel> pedidos = pedidoControl.listarPedidos();
        for (PedidoModel pedido : pedidos) {
            sb.append(String.format("Pedido ID: %d | Cliente ID: %d | Data: %s | Valor Total: R$ %.2f%n", 
                    pedido.getId(), pedido.getClienteId(), pedido.getData(), pedido.getValorTotal()));
        }
        textAreaPedidos.setText(sb.toString());
    }

    // Método para abrir a tela de novo pedido
    private void abrirJanelaNovoPedido() {
        PedidoNovoView novoPedidoView = new PedidoNovoView(); // Certifique-se de que essa classe esteja implementada
        novoPedidoView.setVisible(true);
    }

    // Método para pesquisar pedidos por nome ou ID
    private void pesquisarPedidos() {
        String pesquisa = txtPesquisa.getText();
        if (pesquisa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um nome ou ID para pesquisa.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        List<PedidoModel> pedidos = pedidoControl.pesquisarPedidos(pesquisa);
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum pedido encontrado.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (PedidoModel pedido : pedidos) {
                sb.append(String.format("Pedido ID: %d | Cliente ID: %d | Data: %s | Valor Total: R$ %.2f%n", 
                        pedido.getId(), pedido.getClienteId(), pedido.getData(), pedido.getValorTotal()));
            }
            textAreaPedidos.setText(sb.toString());
        }
    }

    // Método para excluir um pedido pelo ID
    private void excluirPedido() {
        try {
            int id = Integer.parseInt(txtExclusaoId.getText());
            int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o pedido?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                pedidoControl.excluirPedido(id);
                JOptionPane.showMessageDialog(this, "Pedido excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                listarPedidos(); // Atualiza a lista após exclusão
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
    	PedidoControl pedidoControl = new PedidoControl(); // Instancia o controle
        SwingUtilities.invokeLater(() -> {
            PedidoView view = new PedidoView(pedidoControl);
            view.setVisible(true);
        });
    }
}
