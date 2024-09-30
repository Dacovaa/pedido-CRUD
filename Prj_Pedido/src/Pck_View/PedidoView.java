package Pck_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.ParseException;

import Pck_Control.PedidoControl;

public class PedidoView extends JFrame{
	
	private JTextField tfIdCliente;
	private JTextField tfDate;
	private JTextField tfValorTotal;
	private JButton btnInserir;
	private PedidoControl pedidoControl;
	
	public PedidoView() {
		pedidoControl = new PedidoControl();
		
		setTitle("Cadastro de Pedidos");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel lblIdCliente = new JLabel("IdCliente:");
        lblIdCliente.setBounds(20, 20, 100, 25);
        add(lblIdCliente);
        
        tfIdCliente = new JTextField();
        tfIdCliente.setBounds(120, 20, 200, 25);
        add(tfIdCliente);
        
        JLabel lblDate = new JLabel("Data:");
        lblDate.setBounds(20, 60, 100, 25);
        add(lblDate);
        
        tfDate = new JTextField();
        tfDate.setBounds(120, 60, 200, 25);
        add(tfDate);
        
        JLabel lblValorTotal = new JLabel("Valor Total:");
        lblValorTotal.setBounds(20, 100, 100, 25);
        add(lblValorTotal);
        
        tfValorTotal = new JTextField();
        tfValorTotal.setBounds(120, 100, 200, 25);
        add(tfValorTotal);
        
        btnInserir = new JButton("Inserir");
        btnInserir.setBounds(150, 150, 100, 25);
        add(btnInserir);

        btnInserir.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirPedido();
            }
        });
	}
	
	private void inserirPedido() {
		 int A01_Id = 0;
		 double A03_Valor_Total;
		 Date A03_Data;
		 
		 try {
			 A01_Id = Integer.parseInt(tfIdCliente.getText());
		 } catch (NumberFormatException e) {
			 JOptionPane.showMessageDialog(this, "O estoque de conter apenas numeros", "Erro", JOptionPane.ERROR_MESSAGE);
		 }
		 
		 
		 try {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
			 java.util.Date Data = dateFormat.parse(tfDate.getText());
			 A03_Data = new Date (Data.getTime());
		 } catch (ParseException e){
			 JOptionPane.showMessageDialog(this, "Data deve ser no formato dd-MM-yyyy", "Erro", JOptionPane.ERROR_MESSAGE);
			 return;
		 }
		 
		 try {
	            A03_Valor_Total = Double.parseDouble(tfValorTotal.getText());
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "Valor Total deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
		 
		 pedidoControl.inserirPedido(A01_Id, A03_Data, A03_Valor_Total);
		 JOptionPane.showMessageDialog(this, "Pedido inserido com sucesso", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

	}
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new PedidoView().setVisible(true);
	            }
	        });
	    }
	
}
