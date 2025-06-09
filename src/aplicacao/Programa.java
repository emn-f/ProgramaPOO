package aplicacao;

import abstratas.Animal;
import entidades.Adocao;
import entidades.Cachorro;
import entidades.Gato;
import entidades.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Programa {

    static ArrayList<Animal> animais = new ArrayList<>();
    static ArrayList<Pessoa> pessoas = new ArrayList<>();
    static ArrayList<Adocao> adocoes = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    // Método principal que inicia o programa
    public static void main(String[] args) {
        cadastroBase();
        menu();
    }

    // Método responsável por exibir o menu e gerenciar as opções
    private static void menu() {
        Integer opcao;

        do {
            System.out.println(
                    "\n--- MENU ---\n" +
                            "1. Realizar Adoção\n" +
                            "2. Listar Adoções\n" +
                            "3. Cadastrar Animal\n" +
                            "4. Alterar Dados do Animal\n" +
                            "5. Listar Animais Disponíveis\n" +
                            "6. Remover Animal\n" +
                            "7. Cadastrar Pessoa\n" +
                            "8. Alterar Dados da Pessoa\n" +
                            "9. Listar Pessoas\n" +
                            "10. Remover Pessoa\n" +
                            "11. Buscar Animal por Nome\n" +
                            "12. Buscar Pessoa por CPF\n" +
                            "13. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(in.nextLine());

            switch (opcao) {
                case 1:
                    realizarAdocao();
                    break;
                case 2:
                    listarAdocoes();
                    break;
                case 3:
                    cadastrarAnimal();
                    break;
                case 4:
                    alterarDadosAnimal();
                    break;
                case 5:
                    listarAnimaisDisponiveis();
                    break;
                case 6:
                    removerAnimal();
                    break;
                case 7:
                    cadastrarPessoa();
                    break;
                case 8:
                    alterarDadosPessoa();
                    break;
                case 9:
                    listarPessoas();
                    break;
                case 10:
                    removerPessoas();
                    break;
                case 11:
                    buscarAnimalPorNome();
                    break;
                case 12:
                    buscarPessoaPorCpf();
                    break;
                case 13:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 13);
    }

    // Método responsável por cadastrar um novo animal
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

        while (true) {
            System.out.print("Data de entrada (DD/MM/AAAA): ");
            try {
                dataEntrada = LocalDate.parse(in.nextLine(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Utilize o formato DD/MM/AAAA.");
            }
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

    // Método responsável por alterar os dados de um animal cadastrado
    private static void alterarDadosAnimal() {
        Integer escolha, novaIdade;
        Animal animalEscolhido;
        String novoNome, idadeStr, novoPorte;

        System.out.println("\n--- Alterar Dados do Animal ---");

        // Sai do métedo se não houver animais cadastrados
        if (animais.isEmpty()) {
            System.out.println("Não existem animais cadastrados");
            return;
        }

        // Lista os animais cadastrados e pergunta ao usuário qual ele quer alterar
        for (int i = 0; i < animais.size(); i++) {
            System.out.println((i + 1) + " - " + animais.get(i));
        }

        System.out.print("\n Escolha o número do animal que deseja alterar: ");
        try {
            escolha = Integer.parseInt(in.nextLine());
            if (escolha < 1 || escolha > animais.size()) {
                System.out.println("Número inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido.");
            return;
        }
        // Se o animal escolhido já foi adotado, não permite a alteração
        animalEscolhido = animais.get(escolha - 1);
        if (animalEscolhido.getAdotado()) {
            System.out.println("\n Não é possível alterar os dados de um animal já adotado.");
            return;
        }

        // Exibe os dados atuais do animal
        System.out.println("\n Dados atuais do animal:");
        System.out.println(animalEscolhido);

        // Pergunta ao usuário quais dados ele quer alterar
        System.out.println("\n Digite os novos dados (ou pressione Enter para manter o valor atual):");
        System.out.print("Novo nome: ");
        novoNome = in.nextLine();
        if (!novoNome.isEmpty()) {
            animalEscolhido.setNome(novoNome);
        }
        System.out.print("Nova idade: ");
        idadeStr = in.nextLine();
        if (!idadeStr.isEmpty()) {
            try {
                novaIdade = Integer.parseInt(idadeStr);
                if (novaIdade >= 0) {
                    animalEscolhido.setIdade(novaIdade);
                } else {
                    System.out.println("Idade inválida. Idade atual será mantida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Idade inválida. Idade atual será mantida");
            }
        }

        System.out.print("Novo porte (Pequeno/Médio/Grande) [" + animalEscolhido.getPorte() + "]: ");
        novoPorte = in.nextLine();
        if (!novoPorte.isEmpty()) {
            if (novoPorte.equalsIgnoreCase("Pequeno") ||
                    novoPorte.equalsIgnoreCase("Médio") ||
                    novoPorte.equalsIgnoreCase("Grande")) {
                animalEscolhido.setPorte(novoPorte);
            } else {
                System.out.println("Porte inválido. Porte atual será mantido.");
            }
        }

        System.out.println("\nDados alterados com sucesso!");
        System.out.println("Novos dados do animal:");
        System.out.println(animalEscolhido);
    }

    // Método responsável por listar os animais disponíveis para adoção
    private static void listarAnimaisDisponiveis() {
        boolean encontrou = false;

        // Exibe cada animal cadastrado. Se não houver, exibe uma mensagem informando
        // que não há animais disponíveis.
        System.out.println("\n--- Animais Disponíveis ---");
        for (Animal animal : animais) {
            if (!animal.getAdotado()) {
               animal.toString();
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum animal disponível para adoção.");
        }
    }

    // Método responsável por cadastrar uma nova pessoa
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

    // Método responsável por alterar os dados de uma pessoa cadastrada
    private static void alterarDadosPessoa() {
        Integer escolha;
        Pessoa pessoaEscolhida;
        boolean temAdocao, cpfExiste;
        String novoCpf, novoTelefone;

        System.out.println("\n--- Alterar Dados da Pessoa ---");

        // Verifica se há pessoas cadastradas antes de prosseguir
        if (pessoas.isEmpty()) {
            System.out.println("Não existem pessoas cadastradas");
            return;
        }
        // Lista as pessoas cadastradas e pergunta ao usuário qual ele quer alterar
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println((i + 1) + " - " + pessoas.get(i));
        }

        System.out.print("Escolha o número da pessoa que deseja alterar: ");

        try {
            escolha = Integer.parseInt(in.nextLine());
            if (escolha < 1 || escolha > pessoas.size()) {
                System.out.println("Número inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido.");
            return;
        }

        pessoaEscolhida = pessoas.get(escolha - 1);

        // Verifica se a pessoa tem adoções antes de permitir alterações
        temAdocao = adocoes.stream().anyMatch(adocao -> adocao.getAdotante().getCpf().equals(pessoaEscolhida.getCpf()));

        // Na inclusão de CPF, verifica se já existe outro cadastrado
        if (!temAdocao) {
            System.out.print("Novo CPF: ");
            novoCpf = in.nextLine();
            if (!novoCpf.isEmpty()) {
                cpfExiste = pessoas.stream().anyMatch(p -> p.getCpf().equals(novoCpf) && p != pessoaEscolhida);
                if (cpfExiste) {
                    System.out.println("CPF já cadastrado, mantendo CPF atual");
                } else {
                    pessoaEscolhida.setCpf(novoCpf);
                }
            }
        } else {
            System.out.println("CPF não pode ser alterado pois a pessoa possui adoções registradas");
        }

        System.out.print("Novo telefone: ");
        novoTelefone = in.nextLine();
        if (!novoTelefone.isEmpty()) {
            pessoaEscolhida.setTelefone(novoTelefone);
        }

        System.out.println("\nDados alterados com sucesso!");
        System.out.println("Novos dados da pessoa:");
        System.out.println(pessoaEscolhida);

    }

    // Método responsável por listar as pessoas cadastradas
    private static void listarPessoas() {
        System.out.println("\n --- Lista de Pessoas ---");
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            for (Pessoa pessoa : pessoas) {
                pessoa.toString();
            }
        }
    }

    // Método responsável por buscar um animal pelo nome
    private static void buscarAnimalPorNome() {
        String nomeAnimal = in.nextLine().trim();
        System.out.println("\n--- Digite o nome do animal ---");

        for (Animal animal : animais) {
            if (animal.getNome().equalsIgnoreCase(nomeAnimal)) {
                animal.toString();
                return;
            }
        }

        System.out.println("Nenhum animal encontrado com o nome: " + nomeAnimal);
    }

    // Método responsável por buscar uma pessoa pelo CPF
    private static void buscarPessoaPorCpf() {
        String cpfString;

        System.out.println("\n--- Digite o CPF (apenas os números) ---");
        cpfString = in.nextLine().trim();

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpfString)) {
                pessoa.toString();
                return;
            }
        }

        System.out.println("Nenhuma pessoa encontrada com este CPF: " + cpfString);
    }

    // Método responsável por remover uma pessoa cadastrada
    private static void removerPessoas() {
        Integer escolha;
        Pessoa pessoaEscolhida;
        boolean temAdocao;
        String confirmacao;

        System.out.println("\n--- Remover Pessoa ---");

        // Verifica se há pessoas cadastradas antes de prosseguir
        if (pessoas.isEmpty()) {
            System.out.println("Não existem pessoas cadastradas");
            return;
        }

        // Lista as pessoas cadastradas e pergunta ao usuário qual ele quer remover
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println((i + 1) + " - " + pessoas.get(i));
        }

        System.out.print("Escolha o número da pessoa que deseja remover: ");
        try {
            escolha = Integer.parseInt(in.nextLine());
            if (escolha < 1 || escolha > pessoas.size()) {
                System.out.println("Número inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido.");
            return;
        }

        pessoaEscolhida = pessoas.get(escolha - 1);

        // Se a pessoa escolhida tem adoções registradas, não permite a remoção
        temAdocao = adocoes.stream()
                .anyMatch(adocao -> adocao.getAdotante().getCpf().equals(pessoaEscolhida.getCpf()));

        if (temAdocao) {
            System.out.println("Não é possível remover a pessoa pois ela possui adoções registradas.");
            return;
        }

        // Confirmação antes de remover a pessoa
        System.out.print("\n Tem certeza que deseja remover a pessoa " + pessoaEscolhida.getNome() + "? (S/N): ");
        confirmacao = in.nextLine().trim().toUpperCase();

        if (confirmacao.equalsIgnoreCase("S")) {
            pessoas.remove(pessoaEscolhida);
            System.out.println("\n Pessoa removida com sucesso!");
        } else {
            System.out.println("\n Remoção cancelada.");
        }
    }

    // Método responsável por remover um animal cadastrado
    private static void removerAnimal() {
        Integer escolha;
        Animal animalEscolhido;
        boolean temAdocao;
        String confirmacao;

        System.out.println("\n--- Remover Animal ---");

        if (animais.isEmpty()) {
            System.out.println("\n Não existem animais cadastrados.");
            return;
        }

        for (int i = 0; i < animais.size(); i++) {
            System.out.println((i + 1) + " - " + animais.get(i));
        }

        // Lista os animais cadastrados e pergunta ao usuário qual ele quer remover
        System.out.print("\n Escolha o número da animais que deseja remover: ");
        try {
            escolha = Integer.parseInt(in.nextLine());
            if (escolha < 1 || escolha > animais.size()) {
                System.out.println("Número inválido");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido");
            return;
        }

        // Se o animal escolhido já foi adotado, não permite a remoção
        animalEscolhido = animais.get(escolha - 1);
        temAdocao = adocoes.stream()
                .anyMatch(adocao -> adocao.getAnimal() == animalEscolhido);
        if (animalEscolhido.getAdotado() || temAdocao) {
            System.out.println("Não é possível remover o animal pois ele já foi adotado");
            return;
        }

        System.out.print("Tem certeza que deseja remover o animal " + animalEscolhido.getNome() + "? (S/N): ");
        confirmacao = in.nextLine().trim().toUpperCase();

        if (confirmacao.equalsIgnoreCase("S")) {
            animais.remove(animalEscolhido);
            System.out.println("Animal removido com sucesso!");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    // Método responsável por realizar a adoção de um animal
    private static void realizarAdocao() {
        Integer escolha;
        String cpfAdotante, cpfPessoa;

        ArrayList<Animal> disponiveis = new ArrayList<>();

        Pessoa adotante = null;
        Adocao adocao;
        Animal animalEscolhido;

        System.out.print("\n Informe o CPF do adotante: ");
        cpfAdotante = in.nextLine().replaceAll("[^\\d]", "");
        // Verifica se o CPF informado é válido
        for (Pessoa pessoa : pessoas) {
            cpfPessoa = pessoa.getCpf().replaceAll("[^\\d]", "");
            if (cpfPessoa.equals(cpfAdotante)) {
                adotante = pessoa;
                break;
            }
        }
        // Se não encontrar a pessoa, pede para o usuário cadastrar primeiro
        if (adotante == null) {
            System.out.println("\n Pessoa não encontrada, cadastre primeiro");
            return;
        }

        // Verifica se há animais disponíveis para adoção
        for (Animal animal : animais) {
            if (!animal.getAdotado()) {
                disponiveis.add(animal);
            }
        }

        if (disponiveis.isEmpty()) {
            System.out.println("Não há animais disponíveis para adoção");
            return;
        }
        // Lista os animais disponíveis para adoção
        System.out.println("\n--- Animais Disponíveis ---");
        for (int i = 0; i < disponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + disponiveis.get(i));
        }
        // Pede ao usuário para escolher um animal e realiza a adoção
        System.out.print("Escolha o número do animal que deseja adotar: ");
        escolha = Integer.parseInt(in.nextLine());

        if (escolha < 1 || escolha > disponiveis.size()) {
            System.out.println("Escolha inválida");
            return;
        }

        animalEscolhido = disponiveis.get(escolha - 1);

        adocao = new Adocao(animalEscolhido, adotante, LocalDate.now());
        adocoes.add(adocao);
        System.out.println("\n Adoção realizada com sucesso!");
    }

    // Método responsável por listar as adoções realizadas
    private static void listarAdocoes() {
        System.out.println("\n--- Lista de Adoções ---");
        if (adocoes.isEmpty()) {
            System.out.println("Nenhuma adoção realizada.");
        } else {
            for (Adocao adocao : adocoes) {
               adocao.imprimirResumo();
            }
        }
    }

    // Método responsável por cadastrar uma base de dados inicial
    private static void cadastroBase() {
        animais.add(new Cachorro("Rex", 3, "Médio", LocalDate.of(2024, 1, 10)));
        animais.add(new Cachorro("Bolinha", 5, "Pequeno", LocalDate.of(2024, 2, 15)));
        animais.add(new Gato("Mimi", 2, "Pequeno", LocalDate.of(2024, 3, 5)));
        animais.add(new Gato("Frajola", 4, "Médio", LocalDate.of(2024, 3, 20)));
        animais.add(new Cachorro("Thor", 1, "Grande", LocalDate.of(2024, 4, 1)));
        animais.add(new Gato("Garfield", 6, "Médio", LocalDate.of(2024, 5, 8)));
        animais.add(new Cachorro("Princesa", 2, "Pequeno", LocalDate.of(2024, 5, 12)));

        pessoas.add(new Pessoa("Ana Silva", "50286060051", "9439310755"));
        pessoas.add(new Pessoa("Bruno Costa", "50286060053", "8236220107"));
        pessoas.add(new Pessoa("Carlos Dias", "86002254005", "4323295243"));
        pessoas.add(new Pessoa("Daniela Reis", "86125554007", "9826288573"));
        pessoas.add(new Pessoa("Eduardo Lima", "69816445093", "2721416296"));
        pessoas.add(new Pessoa("Fernanda Souza", "23231037093", "8523425221"));
        pessoas.add(new Pessoa("Gustavo Borges", "41066214087", "7932895486"));

    }
}