package entidades;

import abstratas.Animal;
import java.time.LocalDate;

// Classe que representa um gato no sistema de adoção.
// Herda de Animal e define a espécie como "Gato".
public class Gato extends Animal {

    // Construtor
    public Gato(String nome, int idade, String porte, LocalDate dataEntrada) {
        super(nome, idade, "Gato", porte, dataEntrada);
    }

    // Exibe um resumo das informações do gato.
    @Override
    public void imprimirResumo() {
        System.out.println("Gato: " + getNome() + ", Idade: " + getIdade() + ", Porte: " + getPorte());
    }
}