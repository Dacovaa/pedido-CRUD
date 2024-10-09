package Pck_View;

import javax.swing.*;
import Pck_Control.ClienteControl;
import Pck_Control.PedidoControl;
import Pck_Control.ProdutoControl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaInicialView extends JFrame {

    // Controles para diferentes funcionalidades
    private ClienteControl clienteControl;
    private ProdutoControl produtoControl;
    private PedidoControl pedidoControl;

    public PaginaInicialView() {
        // Inicializa os controles
        clienteControl = new ClienteControl();
        produtoControl = new ProdutoControl();
        pedidoControl = new PedidoControl();

        // Configurações da janela
        setTitle("Página Inicial");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Mudamos o layout para BorderLayout

        // Criação de um painel para os botões
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // GridLayout com espaçamento entre os componentes
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem ao redor do painel

        // Botão para acessar CRUD de Clientes
        JButton btnClientes = new JButton("Gerenciar Clientes");
        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteView(clienteControl).setVisible(true); // Abre a tela de Clientes
            }
        });

        // Botão para acessar CRUD de Produtos
        JButton btnProdutos = new JButton("Gerenciar Produtos");
        btnProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProdutoView(produtoControl).setVisible(true); // Abre a tela de Produtos
            }
        });

        // Botão para acessar CRUD de Pedidos
        JButton btnPedidos = new JButton("Gerenciar Pedidos");
        btnPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PedidoView(pedidoControl).setVisible(true); // Abre a tela de Pedidos
            }
        });

        // Adiciona os botões ao painel
        panel.add(btnClientes);
        panel.add(btnProdutos);
        panel.add(btnPedidos);

        // Adiciona o painel à janela principal
        add(panel, BorderLayout.CENTER);
        
        // Adiciona um título na parte superior
        JLabel titleLabel = new JLabel("Sistema de Gerenciamento", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        // Cria e exibe a tela principal
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaginaInicialView().setVisible(true);
            }
        });
    }
}
