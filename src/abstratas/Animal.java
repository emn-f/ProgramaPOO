package abstratas;

import interfaces.Imprimivel;
import java.time.LocalDate;

public abstract class Animal implements Imprimivel {

    protected String nome;
    protected int idade;
    protected String especie;
    protected String porte;
    protected LocalDate dataEntrada;
    protected boolean adotado;

    public Animal(String nome, int idade, String especie, String porte, LocalDate dataEntrada) {
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.porte = porte;
        this.dataEntrada = dataEntrada;
        this.adotado = false;
    }

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

    public String getPorte() {
        return porte;
    }

    public boolean isAdotado() {
        return adotado;
    }

    public void setAdotado(boolean adotado) {
        this.adotado = adotado;
    }

    @Override
    public void imprimirResumo() {
        System.out.printf("| %-15s | %-10s | %-3d | %-8s | %-10s |%n",
                nome, especie, idade, porte, (adotado ? "Adotado" : "Disponível"));
    }
}