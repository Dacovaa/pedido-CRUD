package Pck_View;

import javax.swing.*;
import Pck_Control.ProdutoControl;
import Pck_Model.ProdutoModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdutoView extends JFrame {
    private ProdutoControl produtoControl = new ProdutoControl();
    private JTextField txtDescricao, txtEstoque, txtValorUnitario;
    private JTextArea txtAreaProdutos;

    public ProdutoView() {
        // Configuração da interface (layout, campos, botões, etc.)
        setTitle("Gerenciamento de Produtos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento nas bordas
        add(panelPrincipal, BorderLayout.CENTER);

        // Painel de entrada de dados
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new GridLayout(4, 2, 10, 10)); // 4 linhas, 2 colunas com espaçamento
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Dados do Produto")); // Borda para o painel
        panelPrincipal.add(panelEntrada, BorderLayout.NORTH);

        // Campos de entrada
        panelEntrada.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        panelEntrada.add(txtDescricao);

        panelEntrada.add(new JLabel("Estoque:"));
        txtEstoque = new JTextField();
        panelEntrada.add(txtEstoque);

        panelEntrada.add(new JLabel("Valor Unitário:"));
        txtValorUnitario = new JTextField();
        panelEntrada.add(txtValorUnitario);

        // Área de texto para listar produtos
        txtAreaProdutos = new JTextArea();
        txtAreaProdutos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaProdutos);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout()); // Layout em linha
        panelBotoes.setBorder(BorderFactory.createTitledBorder("Ações")); // Borda para o painel
        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

        // Botões
        JButton btnInserir = new JButton("Inserir");
        btnInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inserirProduto();
            }
        });
        panelBotoes.add(btnInserir);

        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarProdutos();
            }
        });
        panelBotoes.add(btnListar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alterarProduto();
            }
        });
        panelBotoes.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirProduto();
            }
        });
        panelBotoes.add(btnExcluir);
    }

    private void inserirProduto() {
        String descricao = txtDescricao.getText();
        int estoque = Integer.parseInt(txtEstoque.getText());
        double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
        produtoControl.inserirProduto(estoque, descricao, valorUnitario);
        JOptionPane.showMessageDialog(this, "Produto inserido com sucesso!");
        listarProdutos();
    }

    private void listarProdutos() {
        txtAreaProdutos.setText("");
        List<ProdutoModel> produtos = produtoControl.listarProdutos();
        for (ProdutoModel produto : produtos) {
            txtAreaProdutos.append("ID: " + produto.getA02_Id() + ", Descrição: " + produto.getA02_Descricao() + 
                                   ", Estoque: " + produto.getA02_Estoque() + ", Valor Unitário: " + produto.getA02_Valor_Unitario() + "\n");
        }
    }

    private void alterarProduto() {
        String idStr = JOptionPane.showInputDialog("Informe o ID do produto a ser alterado:");
        int id = Integer.parseInt(idStr);
        String descricao = txtDescricao.getText();
        int estoque = Integer.parseInt(txtEstoque.getText());
        double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
        produtoControl.alterarProduto(id, estoque, descricao, valorUnitario);        // Id dando erro na procedure
        JOptionPane.showMessageDialog(this, "Produto alterado com sucesso!");
        listarProdutos();
    }

    private void excluirProduto() {
        String idStr = JOptionPane.showInputDialog("Informe o ID do produto a ser excluído:");
        int id = Integer.parseInt(idStr);
        produtoControl.excluirProduto(id);
        JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
        listarProdutos();
    }

    public static void main(String[] args) {
        ProdutoView view = new ProdutoView();
        view.setVisible(true);
    }
}
