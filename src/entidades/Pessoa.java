package entidades;

import interfaces.Imprimivel;

public class Pessoa implements Imprimivel {
    private String nome;
    private String cpf;
    private String telefone;

    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Pessoa{" +"nome='" + nome + '\'' +", cpf='" + cpf + '\'' +", telefone='" + telefone + '\'' +'}';
    }

    @Override
    public void imprimirResumo() {
        System.out.println("Nome: " + nome + ", CPF: " + cpf + "Telefone: " + telefone);
    }
}