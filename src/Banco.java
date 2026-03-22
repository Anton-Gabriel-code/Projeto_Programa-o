import java.util.ArrayList;

public class Banco {
    private ArrayList<Conta> contas;
    private String nome;

    public Banco(String nome){
        this.nome = nome;
        this.contas = new ArrayList<>();
    }
    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public String getNome() {
        return nome;
    }

    public void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        System.out.println("\n--- Contas cadastradas em " + nome + " ---");
        for (int i = 0; i < contas.size(); i++) {
            Conta c = contas.get(i);
            System.out.printf("[%d] %s | %s | Saldo: R$ %.2f%n",
                    i + 1, c.getTipo(), c.getTitular(), c.getSaldo());
        }
    }
    public Conta getConta(int indice) {
        if (indice < 1 || indice > contas.size()) return null;
        return contas.get(indice - 1);
    }

    public boolean temContas() {
        return !contas.isEmpty();
    }

    public int totalContas() {
        return contas.size();
    }
}
