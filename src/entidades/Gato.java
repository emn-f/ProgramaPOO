package entidades;

import abstratas.Animal;
import java.time.LocalDate;

public class Gato extends Animal {
    public Gato(String nome, int idade, String porte, LocalDate dataEntrada) {
        super(nome, idade, "Gato", porte, dataEntrada);
    }

    @Override
    public void imprimirResumo() {
        System.out.println("Gato: " + getNome() + ", Idade: " + getIdade() + ", Porte: " + getPorte());
    }
}