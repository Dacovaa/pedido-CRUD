package Pck_Model;

public class ClienteModel {
    private int a01_Id;
    private String a01_Nome;
    private String a01_Endereco;
    private String a01_Telefone;
    private String a01_CPF;
    private double a01_Credito;

    // Getters e Setters
    public int getA01_Id() {
        return a01_Id;
    }

    public void setA01_Id(int a01_Id) {
        this.a01_Id = a01_Id;
    }

    public String getA01_Nome() {
        return a01_Nome;
    }

    public void setA01_Nome(String a01_Nome) {
        this.a01_Nome = a01_Nome;
    }

    public String getA01_Endereco() {
        return a01_Endereco;
    }

    public void setA01_Endereco(String a01_Endereco) {
        this.a01_Endereco = a01_Endereco;
    }

    public String getA01_Telefone() {
        return a01_Telefone;
    }

    public void setA01_Telefone(String a01_Telefone) {
        this.a01_Telefone = a01_Telefone;
    }

    public String getA01_CPF() {
        return a01_CPF;
    }

    public void setA01_CPF(String a01_CPF) {
        this.a01_CPF = a01_CPF;
    }

    public double getA01_Credito() {
        return a01_Credito;
    }

    public void setA01_Credito(double a01_Credito) {
        this.a01_Credito = a01_Credito;
    }
    @Override
    public String toString() {
        return a01_Nome; 
    }
}
