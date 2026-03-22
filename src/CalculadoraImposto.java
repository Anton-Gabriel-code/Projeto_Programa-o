import java.util.ArrayList;
import java.util.List;






public class CalculadoraImposto {

    private List<Tributavel> tributaveis;
    private double totalAcumulado;

    public CalculadoraImposto() {
        this.tributaveis = new ArrayList<>();
        this.totalAcumulado = 0.0;
    }

    public void registrar(Tributavel t) {
        tributaveis.add(t);
        totalAcumulado += t.calcularTributo();
    }

    public double getTotalTributos() {
        return totalAcumulado;
    }

    public void exibirRelatorio() {
        if (tributaveis.isEmpty()) {
            System.out.println("Nenhuma conta tributável registrada.");
            return;
        }
        System.out.println("\n--- Relatório de Tributos ---");
        for (Tributavel t : tributaveis) {
            if (t instanceof Conta) {
                Conta c = (Conta) t;
                System.out.printf("  %s (%s): R$ %.2f%n",
                        c.getTitular(), c.getTipo(), t.calcularTributo());
            }
        }
        System.out.printf("  TOTAL DE TRIBUTOS: R$ %.2f%n", totalAcumulado);
    }
}
