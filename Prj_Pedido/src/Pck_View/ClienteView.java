package Pck_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Pck_Control.ClienteControl;

public class ClienteView extends JFrame{
	
	private JTextField tfNome;
    private JTextField tfEndereco;
    private JTextField tfTelefone;
    private JTextField tfCpf;
    private JTextField tfCredito;
    private JButton btnInserir;
    private ClienteControl clienteControl;
    
    public ClienteView() {
    	clienteControl = new ClienteControl();
    	
    	setTitle("Cadastro de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 100, 25);
        add(lblNome);
        
        tfNome = new JTextField();
        tfNome.setBounds(120, 20, 200, 25);
        add(tfNome);
        
        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(20, 60, 100, 25);
        add(lblEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(120, 60, 200, 25);
        add(tfEndereco);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 100, 100, 25);
        add(lblTelefone);

        tfTelefone = new JTextField();
        tfTelefone.setBounds(120, 100, 200, 25);
        add(tfTelefone);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 140, 100, 25);
        add(lblCpf);

        tfCpf = new JTextField();
        tfCpf.setBounds(120, 140, 200, 25);
        add(tfCpf);

        JLabel lblCredito = new JLabel("Crédito:");
        lblCredito.setBounds(20, 180, 100, 25);
        add(lblCredito);

        
        tfCredito = new JTextField();
        tfCredito.setBounds(120, 180, 200, 25);
        add(tfCredito);

        btnInserir = new JButton("Inserir");
        btnInserir.setBounds(150, 220, 100, 25);
        add(btnInserir);

        btnInserir.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirCliente();
            }
        });
    }
    
    private void inserirCliente() {
        String nome = tfNome.getText();
        String endereco = tfEndereco.getText();
        String telefone = tfTelefone.getText();
        String cpf = tfCpf.getText();
        double credito;

        try {
            credito = Double.parseDouble(tfCredito.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Crédito deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        clienteControl.inserirCliente(nome, endereco, telefone, cpf, credito);
        JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClienteView().setVisible(true);
            }
        });
    }
}
