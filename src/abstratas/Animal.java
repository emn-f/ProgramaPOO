package abstratas;

// Importa a interface para impressão de resumo
import interfaces.Imprimivel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//  Classe abstrata que representa um animal no sistema de adoção.
public abstract class Animal implements Imprimivel {

    // Atributos
    private String nome;
    private int idade;
    private String especie;
    private String porte;
    private LocalDate dataEntrada;
    private boolean adotado;

    // Construtor
    public Animal(String nome, int idade, String especie, String porte, LocalDate dataEntrada) {
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.porte = porte;
        this.dataEntrada = dataEntrada;
        this.adotado = false;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public boolean getAdotado() {
        return adotado;
    }

    public void setAdotado(boolean adotado) {
        this.adotado = adotado;
    }

    // Retorna os dados do animal
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Nome: " + nome + ", Idade: " + idade + ", Espécie: " + especie + ", Porte: " + porte
                + ", Data de Entrada: " + dataEntrada.format(formatter) + ", Adotado: " + (adotado ? "Sim" : "Não");
    }
}