import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Banco banco = new Banco("Banco Java");
    static Gerente gerente = new Gerente("Amaury Silva", "244466666", "001");
    static CalculadoraImposto calculadora = new CalculadoraImposto();

    public static void main(String[] args) {
        int opcao;

        System.out.println("-------------------------------------------");
        System.out.println("     Bem-vindo ao " + banco.getNome());
        System.out.println("-------------------------------------------");

        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 0: 
                System.out.println("Encerrando o sistema......."); break;
                case 1: 
                criarConta(); break;
                case 2: 
                banco.listarContas(); break;
                case 3: 
                depositar(); break;
                case 4:
                    sacar(); break;
                case 5: 
                transferir(); break;
                case 6: 
                consultarSaldo(); break;
                case 7: 
                calcularTributos(); break;
                case 8: 
                autenticarGerente(); break;
                case 9: 
                verHistorico(); break;
                default: System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }

    static void exibirMenu() {
        System.out.println("\n------- MENU -------");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Listar contas");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Transferir");
        System.out.println("6 - Consultar saldo");
        System.out.println("7 - Calcular tributo de contas correntes");
        System.out.println("8 - Autenticar gerente");
        System.out.println("9 - Ver histórico de transações");
        System.out.println("0 - Sair");
        System.out.println("--------------------");
    }

    static void criarConta() {
        sc.nextLine();
        System.out.print("Nome do titular: ");
        String nome = sc.nextLine();

        System.out.println("Tipo de conta:");
        System.out.println("1 - Corrente");
        System.out.println("2 - Poupança");
        int tipo = lerInt("Escolha: ");

        if (tipo == 1) {
            banco.adicionarConta(new ContaCorrente(nome));
            System.out.println("Conta Corrente criada com sucesso!");
        } else if (tipo == 2) {
            banco.adicionarConta(new ContaPoupanca(nome));
            System.out.println("Conta Poupança criada com sucesso!");
        } else {
            System.out.println("Tipo inválido. Conta não criada.");
        }
    }

    static void depositar() {
        Conta conta = selecionarConta();
        if (conta == null) return;
        double valor = lerDouble("Valor para depósito: R$ ");
        conta.depositar(valor);
    }

    static void sacar() {
        Conta conta = selecionarConta();
        if (conta == null) return;
        double valor = lerDouble("Valor para saque: R$ ");
        conta.sacar(valor);
    }

    static void consultarSaldo() {
        Conta conta = selecionarConta();
        if (conta != null) conta.exibirDados();
    }

    static void transferir() {
        if (banco.totalContas() < 2) {
            System.out.println("É necessário ter ao menos 2 contas para transferir.");
            return;
        }
        System.out.println("Conta de ORIGEM:");
        Conta origem = selecionarConta();
        if (origem == null) return;

        System.out.println("Conta de DESTINO:");
        Conta destino = selecionarConta();
        if (destino == null) return;

        if (origem == destino) {
            System.out.println("Origem e destino não podem ser a mesma conta.");
            return;
        }

        double valor = lerDouble("Valor da transferência: R$ ");

        if (valor > origem.getSaldo()) {
            System.out.println("Saldo insuficiente para a transferência.");
            return;
        }
        origem.sacar(valor);
        destino.depositar(valor);
        System.out.println("Transferência concluída com sucesso!");
    }

    static void calcularTributos() {
        if (!banco.temContas()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        calculadora = new CalculadoraImposto();

        for (Conta c : banco.getContas()){
            if (c instanceof Tributavel){
                calculadora.registrar((Tributavel) c);
            }
        }

        calculadora.exibirRelatorio();
    }

    static void autenticarGerente() {
        sc.nextLine();
        System.out.print("Senha do gerente: ");
        String senha = sc.nextLine();

        if (gerente.autenticar(senha)) {
            System.out.println("Autenticação bem-sucedida! Bem-vindo, " + gerente.getNome() + ".");
            System.out.println(gerente);
        } else {
            System.out.println("Autenticação falhou.");
        }
    }

    static void verHistorico() {
        Conta conta = selecionarConta();
        if (conta != null) conta.exibirHistorico();
    }
    static Conta selecionarConta() {
        if (!banco.temContas()) {
            System.out.println("Nenhuma conta cadastrada. Crie uma conta primeiro.");
            return null;
        }
        banco.listarContas();
        int indice = lerInt("Selecione o número da conta: ");
        Conta c = banco.getConta(indice);
        if (c == null) System.out.println("Conta inválida.");
        return c;
    }

    static int lerInt(String mensagem) {
        System.out.print(mensagem);
        while (!sc.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!sc.hasNextDouble()) {
            System.out.print("Digite um valor válido: ");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }
}
