import abstratas.Animal;
import entidades.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static ArrayList<Animal> animais = new ArrayList<>();
    static ArrayList<Pessoa> pessoas = new ArrayList<>();
    static ArrayList<Adocao> adocoes = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        cadastroBase();
    
    }

    private static void cadastroBase() {
        animais.add(new Cachorro("Rex", 3, "Médio", LocalDate.of(2024, 1, 10)));
        animais.add(new Cachorro("Bolinha", 5, "Pequeno", LocalDate.of(2024, 2, 15)));
        animais.add(new Gato("Mimi", 2, "Pequeno", LocalDate.of(2024, 3, 5)));
        animais.add(new Gato("Frajola", 4, "Médio", LocalDate.of(2024, 3, 20)));
        animais.add(new Cachorro("Thor", 1, "Grande", LocalDate.of(2024, 4, 1)));
        animais.add(new Gato("Garfield", 6, "Médio", LocalDate.of(2024, 5, 8)));
        animais.add(new Cachorro("Princesa", 2, "Pequeno", LocalDate.of(2024, 5, 12)));

        pessoas.add(new Pessoa("Ana Silva", "111.111.111-11", "9999-8888"));
        pessoas.add(new Pessoa("Bruno Costa", "222.222.222-22", "8888-7777"));
        pessoas.add(new Pessoa("Carlos Dias", "333.333.333-33", "7777-6666"));
        pessoas.add(new Pessoa("Daniela Reis", "444.444.444-44", "6666-5555"));
        pessoas.add(new Pessoa("Eduardo Lima", "555.555.555-55", "5555-4444"));
        pessoas.add(new Pessoa("Fernanda Souza", "666.666.666-66", "4444-3333"));
        pessoas.add(new Pessoa("Gustavo Borges", "777.777.777-77", "3333-2222"));

    }
}