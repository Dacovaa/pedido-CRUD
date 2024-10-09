package Pck_View;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Pck_Control.ClienteControl;
import Pck_Model.ClienteModel;

public class ClienteView extends JFrame {

    private JTextField txtNome, txtEndereco, txtTelefone, txtCpf, txtCredito, txtId;
    private JTextArea txtAreaClientes;
    private ClienteControl clienteControl;

    public ClienteView() {
        clienteControl = new ClienteControl();

        // Configuração da interface
        setTitle("Gerenciamento de Clientes");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de entrada de dados
        JPanel panelEntrada = new JPanel(new GridLayout(6, 2, 5, 5));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        panelEntrada.add(new JLabel("ID (para alterar/excluir):"));
        txtId = new JTextField();
        panelEntrada.add(txtId);

        panelEntrada.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelEntrada.add(txtNome);

        panelEntrada.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        panelEntrada.add(txtEndereco);

        panelEntrada.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        panelEntrada.add(txtTelefone);

        panelEntrada.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        panelEntrada.add(txtCpf);

        panelEntrada.add(new JLabel("Crédito:"));
        txtCredito = new JTextField();
        panelEntrada.add(txtCredito);

        add(panelEntrada, BorderLayout.NORTH);

        // Área de texto para listar clientes
        txtAreaClientes = new JTextArea(10, 50);
        txtAreaClientes.setEditable(false);
        txtAreaClientes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaClientes);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.setBorder(BorderFactory.createTitledBorder("Ações"));

        JButton btnInserir = new JButton("Inserir");
        btnInserir.addActionListener(e -> inserirCliente());
        panelBotoes.add(btnInserir);

        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listarCliente());
        panelBotoes.add(btnListar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(e -> alterarCliente());
        panelBotoes.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> excluirCliente());
        panelBotoes.add(btnExcluir);

        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(e -> pesquisarCliente());
        panelBotoes.add(btnPesquisar);

        add(panelBotoes, BorderLayout.SOUTH);
    }

    // Método de inserção de cliente
    private void inserirCliente() {
        String nome = txtNome.getText();
        String endereco = txtEndereco.getText();
        String telefone = txtTelefone.getText();
        String cpf = txtCpf.getText();
        double credito;

        try {
            credito = Double.parseDouble(txtCredito.getText());
            clienteControl.inserirCliente(nome, endereco, telefone, cpf, credito);
            JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Crédito deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método de listagem de clientes
    private void listarCliente() {
        txtAreaClientes.setText("");
        List<ClienteModel> clientes = clienteControl.listarCliente();

        String header = String.format("%-5s %-20s %-20s %-15s %-15s %-10s\n", "ID", "Nome", "Endereço", "Telefone", "CPF", "Crédito");
        txtAreaClientes.append(header);
        txtAreaClientes.append("--------------------------------------------------------------------------------\n");

        for (ClienteModel cliente : clientes) {
            String enderecoFormatado = (cliente.getA01_Endereco().length() > 20) ? cliente.getA01_Endereco().substring(0, 20) + "..." : cliente.getA01_Endereco();
            String clienteInfo = String.format("%-5d %-20s %-20s %-15s %-15s %-10.2f\n",
                    cliente.getA01_Id(),
                    cliente.getA01_Nome(),
                    enderecoFormatado,
                    cliente.getA01_Telefone(),
                    cliente.getA01_CPF(),
                    cliente.getA01_Credito());
            txtAreaClientes.append(clienteInfo);
        }
    }

    // Método de alteração de cliente
     private void alterarCliente() {
       try {
         int id = Integer.parseInt(txtId.getText());
          String nome = txtNome.getText();
          String endereco = txtEndereco.getText();
          String telefone = txtTelefone.getText();
          String cpf = txtCpf.getText();
          double credito = Double.parseDouble(txtCredito.getText());
    
          clienteControl.alterarCliente(id, nome, endereco, telefone, cpf, credito);
          JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
      } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(this, "Por favor, insira dados válidos para o ID e Crédito.", "Erro", JOptionPane.ERROR_MESSAGE);
      }
    }

    // Método de exclusão de cliente
     private void excluirCliente() {
    	try {
           int id = Integer.parseInt(txtId.getText());
            clienteControl.excluirCliente(id);
            JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
      } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(this, "Por favor, insira um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

   //  Método de pesquisa de cliente
     private void pesquisarCliente() {
    	    String nome = txtNome.getText(); 
    	    List<ClienteModel> clientes = clienteControl.pesquisarCliente(nome); // Mudar para lista de clientes
    	    
    	    if (clientes != null && !clientes.isEmpty()) {
    	        StringBuilder resultado = new StringBuilder();
    	        
    	        // Construir a string para todos os clientes encontrados
    	        for (ClienteModel cliente : clientes) {
    	            resultado.append(String.format(
    	                "ID: %d\nNome: %s\nEndereço: %s\nTelefone: %s\nCPF: %s\nCrédito: %.2f\n\n",
    	                cliente.getA01_Id(),
    	                cliente.getA01_Nome(),
    	                cliente.getA01_Endereco(),
    	                cliente.getA01_Telefone(),
    	                cliente.getA01_CPF(),
    	                cliente.getA01_Credito()
    	            ));
    	        }
    	        
    	        txtAreaClientes.setText(resultado.toString()); // Mostrar todos os resultados
    	    } else {
    	        JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
    	    }
    	}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteView().setVisible(true));
    }
}
