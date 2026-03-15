abstract class ContaBancaria {

private String titular;

private double saldo;

public ContaBancaria(String titular) {

this.titular = titular;

this.saldo = 0.0;

    }

public void depositar(double valor) {

if (valor > 0) {

            saldo += valor;

System.out.println("Depósito realizado com sucesso!");

        } else {

System.out.println("Valor inválido para depósito.");

        }

    }

public void sacar(double valor) {

if (valor <= 0) {

System.out.println("Valor inválido para o saque.");

        } else if (valor > saldo) {

System.out.println("Saldo insuficiente.");

        } else {

            saldo -= valor;

System.out.println("Saque realizado com sucesso!");

        }

    }

public void exibirSaldo() {

System.out.printf("Saldo atual: R$ %.2f%n", saldo);

    }

public String getTitular() {

return titular;

    }

public double getSaldo() {

return saldo;

    }
protected void setSaldo(double saldo) {

this.saldo = saldo;

    }

public abstract String getTipo();

public void exibirDados() {

System.out.println("Tipo:    " + getTipo());

System.out.println("Titular: " + titular);

System.out.printf ("Saldo:   R$ %.2f%n", saldo);

    }

}

class ContaCorrente extends ContaBancaria {

private double taxaManutencao = 20.0;

public ContaCorrente(String titular) {

        super(titular);

    }

    @Override

public String getTipo() {

return "Conta Corrente";

    }
public void calcularRendimento() {

if (getSaldo() >= taxaManutencao) {

setSaldo(getSaldo() - taxaManutencao);

System.out.printf("Taxa de manutenção descontada: R$ %.2f%n", taxaManutencao);

        } else {

System.out.println("Saldo insuficiente para cobrar a taxa de manutenção.");

        }

    }

}

class ContaPoupanca extends ContaBancaria {


private double taxaRendimento = 0.05; // 5%


public ContaPoupanca(String titular) {

        super(titular);

    }

    @Override

public String getTipo() {

return "Conta Poupança";

    }

public void calcularRendimento() {

double rendimento = getSaldo() * taxaRendimento;

setSaldo(getSaldo() + rendimento);

System.out.printf("Rendimento aplicado: R$ %.2f (5%% a.m.)%n", rendimento);

    }

}