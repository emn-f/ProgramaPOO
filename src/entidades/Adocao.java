package entidades;

// Importações da interface, da classe abstrata Aniaml e outras classes para formatação das datas
import interfaces.Imprimivel;
import abstratas.Animal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Classe representa o registro de uma adoção de animal. Armazena o animal adotado, a pessoa adotante e a data da adoção.

public class Adocao implements Imprimivel {

    // Atributos
    private Animal animal;
    private Pessoa adotante;
    private LocalDate dataAdocao;

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Construtor
    public Adocao(Animal animal, Pessoa adotante, LocalDate dataAdocao) {
        this.animal = animal;
        this.adotante = adotante;
        this.dataAdocao = dataAdocao;
        this.animal.setAdotado(true);
    }

    // Getters e Setters
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Pessoa getAdotante() {
        return adotante;
    }

    public void setAdotante(Pessoa adotante) {
        this.adotante = adotante;
    }

    public LocalDate getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(LocalDate dataAdocao) {
        this.dataAdocao = dataAdocao;
    }

    // Método toString que retorna os dados da adoção.
    @Override
    public String toString() {
        return "Adocao: " + "Animal=" + animal.getNome() + ", Adotante=" + adotante.getNome() + ", Data de adoção="
                + dataAdocao.format(FORMATTER);
    }

    // Imprime um resumo da adoção.
    @Override
    public void imprimirResumo() {
        System.out.println("Resumo da Adoção:");
        System.out.println("Animal: " + animal.getNome());
        System.out.println("Adotante: " + adotante.getNome());
        System.out.println("Data da Adoção: " + dataAdocao.format(FORMATTER));
    }
}
