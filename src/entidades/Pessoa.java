package entidades;

// Importação da interface Imprimivel
import interfaces.Imprimivel;

public class Pessoa implements Imprimivel {

    // Atributos
    private String nome;
    private String cpf;
    private String telefone;

    // Construtor
    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Exibe as informações da pessoa.
    @Override
    public void imprimirResumo() {
        System.out.println("Nome: " + nome + ", CPF: " + cpf + ", Telefone: " + telefone);
    }
}