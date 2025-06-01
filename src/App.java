import abstratas.Animal;
import entidades.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class App {

    static ArrayList<Animal> animais = new ArrayList<>();
    static ArrayList<Pessoa> pessoas = new ArrayList<>();
    static ArrayList<Adocao> adocoes = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args){
        cadastroBase();
        menu();
    }

    private static void menu() {
        Integer opcao;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar Animal");
            System.out.println("2. Listar Animais Disponíveis");
            System.out.println("3. Cadastrar Pessoa");
            System.out.println("4. Realizar Adoção");
            System.out.println("5. Listar Adoções");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(in.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarAnimal();
                    break;
                case 2:
                    listarAnimaisDisponiveis();
                    break;
                case 3:
                    cadastrarPessoa();
                    break;
                case 4:
                    realizarAdocao();
                    break;
                case 5:
                    listarAdocoes();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

    private static void cadastrarAnimal() {
        Integer tipo, idade;
        String nome, porte;
        LocalDate dataEntrada;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Digite o tipo (1 - Cachorro / 2 - Gato): ");
        tipo = Integer.parseInt(in.nextLine());

        System.out.print("Nome: ");
        nome = in.nextLine();

        System.out.print("Idade: ");
        idade = Integer.parseInt(in.nextLine());

        System.out.print("Porte (Pequeno/Médio/Grande): ");
        porte = in.nextLine();

        System.out.print("Data de entrada (DD/MM/AAAA): ");
        try {
            dataEntrada = LocalDate.parse(in.nextLine(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Utilize o formato DD/MM/AAAA.");
            return;
        }

        if (tipo == 1) {
            animais.add(new Cachorro(nome, idade, porte, dataEntrada));
            System.out.println("Cachorro cadastrado com sucesso!");
        } else if (tipo == 2) {
            animais.add(new Gato(nome, idade, porte, dataEntrada));
            System.out.println("Gato cadastrado com sucesso!");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void listarAnimaisDisponiveis() {
        boolean encontrou = false;
        
        System.out.println("\n--- Animais Disponíveis ---");
        for (Animal animal : animais) {
            if (!animal.getAdotado()) {
                System.out.println(animal);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum animal disponível para adoção.");
        }
    }

    private static void cadastrarPessoa() {
        String nome, cpf, telefone;

        System.out.print("Nome: ");
        nome = in.nextLine();
        System.out.print("CPF: ");
        cpf = in.nextLine();
        System.out.print("Telefone: ");
        telefone = in.nextLine();

        pessoas.add(new Pessoa(nome, cpf, telefone));
        System.out.println("Pessoa cadastrada com sucesso!");
    }

    private static void realizarAdocao() {
        String cpfAdotante;
        Pessoa adotante = null;
        int escolha;
        Adocao adocao;
         Animal animalEscolhido;
        System.out.print("Informe o CPF do adotante: ");
        cpfAdotante = in.nextLine();

        cpfAdotante = cpfAdotante.replaceAll("[^\\d]", "");
        for (Pessoa pessoa : pessoas) {
            String cpfPessoa = pessoa.getCpf().replaceAll("[^\\d]", "");
            if (cpfPessoa.equals(cpfAdotante)) {
                adotante = pessoa;
                break;
            }
        }

        if (adotante == null) {
            System.out.println("Pessoa não encontrada. Cadastre primeiro.");
            return;
        }

        ArrayList<Animal> disponiveis = new ArrayList<>();
        for (Animal animal : animais) {
            if (!animal.getAdotado()) {
                disponiveis.add(animal);
            }
        }

        if (disponiveis.isEmpty()) {
            System.out.println("Não há animais disponíveis para adoção.");
            return;
        }

        System.out.println("\n--- Animais Disponíveis ---");
        for (int i = 0; i < disponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + disponiveis.get(i));
        }

        System.out.print("Escolha o número do animal que deseja adotar: ");
        escolha = Integer.parseInt(in.nextLine());

        if (escolha < 1 || escolha > disponiveis.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        animalEscolhido = disponiveis.get(escolha - 1);

        adocao = new Adocao(animalEscolhido, adotante, LocalDate.now());
        adocoes.add(adocao);
        System.out.println("Adoção realizada com sucesso!");
    }

    private static void listarAdocoes() {
        System.out.println("\n--- Lista de Adoções ---");
        if (adocoes.isEmpty()) {
            System.out.println("Nenhuma adoção realizada.");
        } else {
            for (Adocao adocao : adocoes) {
                System.out.println(adocao);
            }
        }
    }

    private static void cadastroBase() {
        animais.add(new Cachorro("Rex", 3, "Médio", LocalDate.of(2024, 1, 10)));
        animais.add(new Cachorro("Bolinha", 5, "Pequeno", LocalDate.of(2024, 2, 15)));
        animais.add(new Gato("Mimi", 2, "Pequeno", LocalDate.of(2024, 3, 5)));
        animais.add(new Gato("Frajola", 4, "Médio", LocalDate.of(2024, 3, 20)));
        animais.add(new Cachorro("Thor", 1, "Grande", LocalDate.of(2024, 4, 1)));
        animais.add(new Gato("Garfield", 6, "Médio", LocalDate.of(2024, 5, 8)));
        animais.add(new Cachorro("Princesa", 2, "Pequeno", LocalDate.of(2024, 5, 12)));

        pessoas.add(new Pessoa("Ana Silva", "50286060051", "9439310755"));
        pessoas.add(new Pessoa("Bruno Costa", "50286060051", "8236220107"));
        pessoas.add(new Pessoa("Carlos Dias", "86002254005", "4323295243"));
        pessoas.add(new Pessoa("Daniela Reis", "86125554007", "9826288573"));
        pessoas.add(new Pessoa("Eduardo Lima", "69816445093", "2721416296"));
        pessoas.add(new Pessoa("Fernanda Souza", "23231037093", "8523425221"));
        pessoas.add(new Pessoa("Gustavo Borges", "41066219087", "7932895486"));

    }
}