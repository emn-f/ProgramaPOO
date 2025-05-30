package entidades;

import abstratas.Animal;
import java.time.LocalDate;

public class Cachorro extends Animal {
    public Cachorro(String nome, int idade, String porte, LocalDate dataEntrada) {
        super(nome, idade, "Cachorro", porte, dataEntrada);
    }

    @Override
    public void imprimirResumo() {
        System.out.println("Cachorro: " + getNome() + ", Idade: " + getIdade() + ", Porte: " + getPorte());
    }
}