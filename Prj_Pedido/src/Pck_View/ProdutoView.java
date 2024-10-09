package Pck_View;

import javax.swing.*;

import Pck_Control.ClienteControl;
import Pck_Control.ProdutoControl;
import Pck_Model.ProdutoModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdutoView extends JFrame {
    private ProdutoControl produtoControl = new ProdutoControl();
    private JTextField txtDescricao, txtEstoque, txtValorUnitario, txtId;
    private JTextArea txtAreaProdutos;
    private JButton btnInserir, btnListar, btnAlterar, btnExcluir, btnPesquisar;

    public ProdutoView(ProdutoControl pordutoControl) {
        // Configuração da interface (layout, campos, botões, etc.)
        setTitle("Gerenciamento de Produtos");
        setSize(700, 600);
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
        panelEntrada.add(new JLabel("ID (para alterar/excluir):"));
        txtId = new JTextField();
        panelEntrada.add(txtId);

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
        txtAreaProdutos = new JTextArea(10, 50);
        txtAreaProdutos.setEditable(false);
        txtAreaProdutos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaProdutos);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout()); // Layout em linha
        panelBotoes.setBorder(BorderFactory.createTitledBorder("Ações")); // Borda para o painel
        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

        // Botões
        btnInserir = new JButton("Inserir");
        btnInserir.addActionListener(e -> inserirProduto());
        panelBotoes.add(btnInserir);

        btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listarProdutos());
        panelBotoes.add(btnListar);

        btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(e -> alterarProduto());
        panelBotoes.add(btnAlterar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> excluirProduto());
        panelBotoes.add(btnExcluir);

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(e -> pesquisarProduto());
        panelBotoes.add(btnPesquisar);
        
        // Botão "Voltar"
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new PaginaInicialView().setVisible(true); // Abre a tela inicial
            }
        });
        panelBotoes.add(btnVoltar);
    }

    private void inserirProduto() {
        try {
            String descricao = txtDescricao.getText();
            int estoque = Integer.parseInt(txtEstoque.getText());
            double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
            produtoControl.inserirProduto(estoque, descricao, valorUnitario);
            JOptionPane.showMessageDialog(this, "Produto inserido com sucesso!");
            listarProdutos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inserir produto. Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarProdutos() {
        txtAreaProdutos.setText("");
        List<ProdutoModel> produtos = produtoControl.listarProdutos();
        String header = String.format("%-5s %-20s %-15s %-15s\n", "ID", "Descrição", "Estoque", "Valor Unitário");
        txtAreaProdutos.append(header);
        txtAreaProdutos.append("-------------------------------------------------------------\n");

        for (ProdutoModel produto : produtos) {
            txtAreaProdutos.append(String.format("%-5d %-20s %-15d %-15.2f\n",
                    produto.getA02_Id(),
                    produto.getA02_Descricao(),
                    produto.getA02_Estoque(),
                    produto.getA02_Valor_Unitario()));
        }
    }

    private void pesquisarProduto() {
        String descricao = txtDescricao.getText(); // Usar o campo de descrição para pesquisa
        List<ProdutoModel> produtos = produtoControl.pesquisarProduto(descricao);
        
        txtAreaProdutos.setText(""); // Limpa a área de texto antes de listar os resultados

        if (!produtos.isEmpty()) {
            String header = String.format("%-5s %-20s %-15s %-15s\n", "ID", "Descrição", "Estoque", "Valor Unitário");
            txtAreaProdutos.append(header);
            txtAreaProdutos.append("-------------------------------------------------------------\n");

            for (ProdutoModel produto : produtos) {
                txtAreaProdutos.append(String.format("%-5d %-20s %-15d %-15.2f\n",
                        produto.getA02_Id(),
                        produto.getA02_Descricao(),
                        produto.getA02_Estoque(),
                        produto.getA02_Valor_Unitario()));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum produto encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void alterarProduto() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String descricao = txtDescricao.getText();
            int estoque = Integer.parseInt(txtEstoque.getText());
            double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
            produtoControl.alterarProduto(id, estoque, descricao, valorUnitario);
            JOptionPane.showMessageDialog(this, "Produto alterado com sucesso!");
            listarProdutos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar produto. Verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirProduto() {
        try {
            int id = Integer.parseInt(txtId.getText());
            produtoControl.excluirProduto(id);
            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
            listarProdutos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produto. Verifique o ID.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
    	ProdutoControl produtoControl = new ProdutoControl(); // Instancia o controle
        SwingUtilities.invokeLater(() -> new ProdutoView(produtoControl).setVisible(true));
    }
}
