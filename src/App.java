import abstratas.Animal;
import entidades.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class App {

    private static final ArrayList<Animal> animais = new ArrayList<>();
    private static final ArrayList<Pessoa> pessoas = new ArrayList<>();
    private static final ArrayList<Adocao> adocoes = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cadastrarDadosIniciais();

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuGerenciarAnimais();
                    break;
                case 2:
                    menuGerenciarPessoas();
                    break;
                case 3:
                    registrarAdocao();
                    break;
                case 4:
                    listarAdocoes();
                    break;
                case 5:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Sistema de Adoção de Animais ---");
        System.out.println("1. Gerenciar Animais");
        System.out.println("2. Gerenciar Pessoas");
        System.out.println("3. Registrar Adoção");
        System.out.println("4. Listar Adoções");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Lê a opção numérica do usuário.
     * 
     * @return O número da opção escolhida.
     */
    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Digite um número: ");
            scanner.next(); // Limpa o buffer
        }
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consome a quebra de linha
        return opcao;
    }

    private static void menuGerenciarAnimais() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Animais ---");
            System.out.println("1. Cadastrar Animal");
            System.out.println("2. Listar Animais");
            System.out.println("3. Listar Animais por Idade (ordenado)");
            System.out.println("4. Buscar Animal por Nome");
            System.out.println("5. Alterar Animal");
            System.out.println("6. Remover Animal");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarAnimal();
                    break;
                case 2:
                    listarAnimais();
                    break;
                case 3:
                    listarAnimaisOrdenadosPorIdade();
                    break;
                case 4:
                    buscarAnimalPorNome();
                    break;
                case 5:
                    alterarAnimal();
                    break;
                case 6:
                    removerAnimal();
                    break;
                case 7:
                    System.out.println("Retornando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
    }

    private static void cadastrarAnimal() {
        System.out.print("Digite o tipo (Cachorro/Gato): ");
        String tipo = scanner.nextLine();
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a idade: ");
        int idade = lerOpcao();
        System.out.print("Digite o porte (Pequeno/Médio/Grande): ");
        String porte = scanner.nextLine();

        if (tipo.equalsIgnoreCase("Cachorro")) {
            animais.add(new Cachorro(nome, idade, porte, LocalDate.now()));
        } else if (tipo.equalsIgnoreCase("Gato")) {
            animais.add(new Gato(nome, idade, porte, LocalDate.now()));
        } else {
            System.out.println("Tipo de animal inválido.");
            return;
        }
        System.out.println("Animal cadastrado com sucesso!");
    }

    private static void listarAnimais() {
        System.out.println("\n--- Lista de Animais Cadastrados ---");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-3s | %-8s | %-10s |%n", "Nome", "Espécie", "Idade", "Porte", "Status");
        System.out.println("-------------------------------------------------------------------");
        for (Animal animal : animais) {
            animal.imprimirResumo();
        }
        System.out.println("-------------------------------------------------------------------");
    }

    private static void listarAnimaisOrdenadosPorIdade() {
        ArrayList<Animal> animaisOrdenados = new ArrayList<>(animais);
        animaisOrdenados.sort(Comparator.comparingInt(Animal::getIdade));

        System.out.println("\n--- Lista de Animais Ordenados por Idade ---");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-3s | %-8s | %-10s |%n", "Nome", "Espécie", "Idade", "Porte", "Status");
        System.out.println("-------------------------------------------------------------------");
        for (Animal animal : animaisOrdenados) {
            animal.imprimirResumo();
        }
        System.out.println("-------------------------------------------------------------------");
    }

    private static void buscarAnimalPorNome() {
        System.out.print("Digite o nome do animal para buscar: ");
        String nomeBusca = scanner.nextLine();
        boolean encontrado = false;

        System.out.println("\n--- Resultado da Busca ---");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-3s | %-8s | %-10s |%n", "Nome", "Espécie", "Idade", "Porte", "Status");
        System.out.println("-------------------------------------------------------------------");

        for (Animal animal : animais) {
            if (animal.getNome().equalsIgnoreCase(nomeBusca)) {
                animal.imprimirResumo();
                encontrado = true;
            }
        }
        System.out.println("-------------------------------------------------------------------");

        if (!encontrado) {
            System.out.println("Nenhum animal encontrado com este nome.");
        }
    }

    private static void alterarAnimal() {
        System.out.print("Digite o nome do animal para alterar: ");
        String nomeBusca = scanner.nextLine();
        Animal animalParaAlterar = null;

        for (Animal animal : animais) {
            if (animal.getNome().equalsIgnoreCase(nomeBusca) && !animal.isAdotado()) {
                animalParaAlterar = animal;
                break;
            }
        }

        if (animalParaAlterar == null) {
            System.out.println("Animal não encontrado ou já adotado.");
            return;
        }

        System.out.print("Digite o novo nome (deixe em branco para não alterar): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isEmpty()) {
            animalParaAlterar.setNome(novoNome);
        }

        System.out.print("Digite a nova idade (digite 0 para não alterar): ");
        int novaIdade = lerOpcao();
        if (novaIdade > 0) {
            animalParaAlterar.setIdade(novaIdade);
        }

        System.out.println("Dados do animal alterados com sucesso!");
    }

    private static void removerAnimal() {
        System.out.print("Digite o nome do animal para remover: ");
        String nomeBusca = scanner.nextLine();
        Animal animalParaRemover = null;

        for (Animal animal : animais) {
            if (animal.getNome().equalsIgnoreCase(nomeBusca) && !animal.isAdotado()) {
                animalParaRemover = animal;
                break;
            }
        }

        if (animalParaRemover != null) {
            animais.remove(animalParaRemover);
            System.out.println("Animal removido com sucesso!");
        } else {
            System.out.println("Animal não encontrado ou já foi adotado (não pode ser removido).");
        }
    }

    private static void menuGerenciarPessoas() {
        System.out.println("\n--- Gerenciar Pessoas ---");
        System.out.println("1. Cadastrar Pessoa");
        System.out.println("2. Listar Pessoas");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                cadastrarPessoa();
                break;
            case 2:
                listarPessoas();
                break;
            default:
                break;
        }
    }

    private static void cadastrarPessoa() {
        System.out.print("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();

        pessoas.add(new Pessoa(nome, cpf, telefone));
        System.out.println("Pessoa cadastrada com sucesso!");
    }

    private static void listarPessoas() {
        System.out.println("\n--- Lista de Pessoas Cadastradas ---");
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %-20s | %-15s | %-15s |%n", "Nome", "CPF", "Telefone");
        System.out.println("---------------------------------------------------------");
        for (Pessoa pessoa : pessoas) {
            pessoa.imprimirResumo();
        }
        System.out.println("---------------------------------------------------------");
    }

    private static void registrarAdocao() {
        System.out.println("\n--- Registrar Nova Adoção ---");
        System.out.print("Digite o nome do animal a ser adotado: ");
        String nomeAnimal = scanner.nextLine();

        Animal animalParaAdotar = null;
        for (Animal animal : animais) {
            if (animal.getNome().equalsIgnoreCase(nomeAnimal) && !animal.isAdotado()) {
                animalParaAdotar = animal;
                break;
            }
        }

        if (animalParaAdotar == null) {
            System.out.println("Animal não encontrado ou já adotado.");
            return;
        }

        System.out.print("Digite o CPF do adotante: ");
        String cpfAdotante = scanner.nextLine();
        Pessoa adotante = null;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpfAdotante)) {
                adotante = pessoa;
                break;
            }
        }

        if (adotante == null) {
            System.out.println("Adotante não encontrado. Cadastre a pessoa primeiro.");
            return;
        }

        Adocao novaAdocao = new Adocao(animalParaAdotar, adotante, LocalDate.now());
        adocoes.add(novaAdocao);

        System.out.println("Adoção registrada com sucesso!");
    }

    private static void listarAdocoes() {
        System.out.println("\n--- Lista de Adoções Realizadas ---");
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-20s | %-15s |%n", "Animal", "Espécie", "Adotante", "Data da Adoção");
        System.out.println("--------------------------------------------------------------------");
        for (Adocao adocao : adocoes) {
            adocao.imprimirResumo();
        }
        System.out.println("--------------------------------------------------------------------");
    }

    private static void cadastrarDadosIniciais() {
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
        
        System.out.println("Dados iniciais carregados para teste.");
    }
}