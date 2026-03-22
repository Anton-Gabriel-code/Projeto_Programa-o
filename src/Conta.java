import java.util.ArrayList;
import java.util.List;


public abstract class Conta {

    private String titular;
    private double saldo;
    private List<String> historico;

    public Conta(String titular) {
        this.titular = titular;
        this.saldo = 0.0;
        this.historico = new ArrayList<>();
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            historico.add("Depósito: R$ " + String.format("%.2f", valor));
            System.out.println("Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para saque.");
        } else if (valor > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            saldo -= valor;
            historico.add("Saque: R$ " + String.format("%.2f", valor));
            System.out.println("Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
        }
    }

    public void exibirDados() {
        System.out.println("Tipo:    " + getTipo());
        System.out.println("Titular: " + titular);
        System.out.printf("Saldo:   R$ %.2f%n", saldo);
    }

    public void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
            return;
        }
        System.out.println("--- Histórico de " + titular + " ---");
        for (String registro : historico) {
            System.out.println("  " + registro);
        }
    }

    public String getTitular() { return titular; }
    public double getSaldo()   { return saldo; }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    protected void addHistorico(String registro) {
        this.historico.add(registro);
    }

    public abstract String getTipo();
}
