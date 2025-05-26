package entidades;
import interfaces.Imprimivel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import abstratas.Animal;

public class Adocao implements Imprimivel {
    private Animal animal;
    private Pessoa adotante;
    private LocalDate dataAdocao;

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

    @Override
    public void imprimirResumo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("| %-15s | %-10s | %-20s | %-15s |%n",
                animal.getNome(), animal.getEspecie(), adotante.getNome(), dataAdocao.format(formatter));
    }
}