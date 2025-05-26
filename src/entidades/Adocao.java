package entidades;
import interfaces.Imprimivel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import abstratas.Animal;

public class Adocao implements Imprimivel {
    private Animal animal;
    private Pessoa adotante;
    private LocalDate dataAdocao;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Adocao(Animal animal, Pessoa adotante, LocalDate dataAdocao) {
        this.animal = animal;
        this.adotante = adotante;
        this.dataAdocao = dataAdocao;
        this.animal.setAdotado(true); 
    }

    public Animal getAnimal() {
        return animal;
    }

    public Pessoa getAdotante() {
        return adotante;
    }

    public LocalDate getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(LocalDate dataAdocao) {
        this.dataAdocao = dataAdocao;
    }
    
    @Override
    public String toString() {
        return "Adocao{" +"animal=" + animal.getNome() +", adotante=" + adotante.getNome() +", dataAdocao=" + dataAdocao.format(formatter) +'}';
    }

    @Override
    public void imprimirResumo() {
        System.out.println("Resumo da Adoção:");
        System.out.println("Animal: " + animal.getNome());
        System.out.println("Adotante: " + adotante.getNome());
        System.out.println("Data da Adoção: " + dataAdocao.format(formatter));
    }
}
