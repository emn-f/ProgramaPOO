package entidades;

import abstratas.Animal;
import java.time.LocalDate;

// Classe que representa um cachorro no sistema de adoção.
// Herda de Animal e define a espécie como "Cachorro".
public class Cachorro extends Animal {

    // Construtor
    public Cachorro(String nome, int idade, String porte, LocalDate dataEntrada) {
        super(nome, idade, "Cachorro", porte, dataEntrada);
    }

    // Exibe um resumo das informações do cachorro.
    @Override
    public void imprimirResumo() {
        System.out.println("Cachorro: " + getNome() + ", Idade: " + getIdade() + ", Porte: " + getPorte());
    }
}