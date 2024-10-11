package Pck_View;

import javax.swing.*;
import Pck_Control.ClienteControl;
import Pck_Control.PedidoControl;
import Pck_Control.ProdutoControl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaInicialView extends JFrame {
	
	private static PaginaInicialView instance;
    private ClienteControl clienteControl;
    private ProdutoControl produtoControl;
    private PedidoControl pedidoControl;

    public PaginaInicialView() {
        clienteControl = new ClienteControl();
        produtoControl = new ProdutoControl();
        pedidoControl = new PedidoControl();

        setTitle("Página Inicial");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); 
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton btnClientes = new JButton("Gerenciar Clientes");
        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose(); 
                new ClienteView(clienteControl).setVisible(true);
            }
        });

        JButton btnProdutos = new JButton("Gerenciar Produtos");
        btnProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new ProdutoView(produtoControl).setVisible(true);
            }
        });
        JButton btnPedidos = new JButton("Gerenciar Pedidos");
        btnPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new PedidoView(pedidoControl).setVisible(true);
            }
        });
        panel.add(btnClientes);
        panel.add(btnProdutos);
        panel.add(btnPedidos);
        add(panel, BorderLayout.CENTER);
        
        JLabel titleLabel = new JLabel("Sistema de Gerenciamento", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
    }
    
    public static PaginaInicialView getInstance() {
        if (instance == null) {
            instance = new PaginaInicialView();
        }
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaginaInicialView().setVisible(true);
            }
        });
    }
}
