package Pck_View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import Pck_Control.ProdutoControl;

public class ProdutoView extends JFrame {
	
	private JTextField tfEstoque;
	private JTextField tfDescricao;
	private JTextField tfValorUnitario;
	private ProdutoControl produtoControl;
	private JButton btnInserir;
	
	public ProdutoView() {
		produtoControl = new ProdutoControl();
		
		setTitle("Cadastro de Produtos");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel lblEstoque = new JLabel("Estoque:");
        lblEstoque.setBounds(20, 20, 100, 25);
        add(lblEstoque);
        
        tfEstoque = new JTextField();
        tfEstoque.setBounds(120, 20, 200, 25);
        add(tfEstoque);
        
        JLabel lblDescricao = new JLabel("Descricao:");
        lblDescricao.setBounds(20, 60, 100, 25);
        add(lblDescricao);
        
        tfDescricao = new JTextField();
        tfDescricao.setBounds(120, 60, 200, 25);
        add(tfDescricao);
        
        JLabel lblValorTotal = new JLabel("Valor Unitario:");
        lblValorTotal.setBounds(20, 100, 100, 25);
        add(lblValorTotal);
        
        tfValorUnitario = new JTextField();
        tfValorUnitario.setBounds(120, 100, 200, 25);
        add(tfValorUnitario);
        
        btnInserir = new JButton("Inserir");
        btnInserir.setBounds(150, 150, 100, 25);
        add(btnInserir);

        btnInserir.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirProduto();
            }
        });
	}
	
	private void inserirProduto() {
		int A02_Estoque = 0;
		String A02_Descricao = tfDescricao.getText();		
		double A02_Valor_Unitario = 0;
		try {
			 A02_Estoque = Integer.parseInt(tfEstoque.getText());
		 } catch (NumberFormatException e) {
			 JOptionPane.showMessageDialog(this, "O estoque de conter apenas numeros", "Erro", JOptionPane.ERROR_MESSAGE);
		 }
		 
		
		try {
            A02_Valor_Unitario = Double.parseDouble(tfValorUnitario.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor Unitario deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
		
		produtoControl.inserirProduto(A02_Estoque, A02_Descricao, A02_Valor_Unitario);
	}
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new ProdutoView().setVisible(true);
	            }
	        });
	    }
}
