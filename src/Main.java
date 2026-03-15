import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static ArrayList<ContaBancaria> contas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n ---Menu---");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Listar contas");
            System.out.println("3 - Depositar");
            System.out.println("4 - Ver Saldo");
            System.out.println("5 - Sacar");
            System.out.println("6 - Transferir");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    listarContas(); 
                    break;
                case 3:
                    ContaBancaria contaDep = selecionarConta();
                    if (contaDep != null) {
                        System.out.print("Digite o valor para depósito: ");
                        double deposito = sc.nextDouble();
                        contaDep.depositar(deposito);
                    }
                    break;
                case 4:
                    ContaBancaria contaSaldo = selecionarConta();
                    if (contaSaldo != null) {
                        contaSaldo.exibirDados();
                    }
                    break;
                case 5:
                    ContaBancaria contaSaque = selecionarConta();
                    if (contaSaque != null) {
                        System.out.print("Digite o valor para saque: ");
                        double saque = sc.nextDouble();
                        contaSaque.sacar(saque);
                    }
                    break;
                case 6:
                    transferir(); 
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 7);

        sc.close();
    }
    static void criarConta() {
        sc.nextLine(); // limpa o buffer do scanner
        System.out.print("Nome do titular: ");
        String nome = sc.nextLine();

        System.out.println("Tipo de conta:");
        System.out.println("1 - Corrente");
        System.out.println("2 - Poupança");
        System.out.print("Escolha: ");
        int tipo = sc.nextInt();

        if (tipo == 1) {
            contas.add(new ContaCorrente(nome));
            System.out.println("Conta Corrente criada com sucesso!");
        } else if (tipo == 2) {
            contas.add(new ContaPoupanca(nome));
            System.out.println("Conta Poupança criada com sucesso!");
        } else {
            System.out.println("Tipo inválido. Conta não criada.");
        }
    }
    static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        System.out.println("\n--- Contas cadastradas ---");
        for (int i = 0; i < contas.size(); i++) {
            ContaBancaria c = contas.get(i);
            System.out.printf("[%d] %s | %s | Saldo: R$ %.2f%n",
                    i + 1, c.getTipo(), c.getTitular(), c.getSaldo());
        }
    }

    static ContaBancaria selecionarConta() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada. Crie uma conta primeiro.");
            return null;
        }
        listarContas();
        System.out.print("Selecione o número da conta: ");
        int indice = sc.nextInt();
        if (indice < 1 || indice > contas.size()) {
            System.out.println("Conta inválida.");
            return null;
        }
        return contas.get(indice - 1);
    }
    static void transferir() {
        if (contas.size() < 2) {
            System.out.println("É necessário ter ao menos 2 contas para transferir.");
            return;
        }
        System.out.println("Conta de ORIGEM:");
        ContaBancaria origem = selecionarConta();
        if (origem == null) return;

        System.out.println("Conta de DESTINO:");
        ContaBancaria destino = selecionarConta();
        if (destino == null) return;

        System.out.print("Valor da transferência: R$ ");
        double valor = sc.nextDouble();

        if (valor > origem.getSaldo()) {
            System.out.println("Saldo insuficiente para a transferência.");
            return;
        }
        origem.sacar(valor);
        destino.depositar(valor);
        System.out.println("Transferência concluída!");
    }
}