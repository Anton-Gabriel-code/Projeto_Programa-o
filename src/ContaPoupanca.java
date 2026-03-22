public class ContaPoupanca extends Conta {

    private static final double TAXA_RENDIMENTO = 0.05;
    public ContaPoupanca(String titular){
        super(titular);
    }

    public String getTipo(){
        return "Conta Poupança";
    }

    public void aplicarRendimento(){
        double rendimento = getSaldo() * TAXA_RENDIMENTO;
        setSaldo(getSaldo() + rendimento);
        addHistorico("Rendimento aplicado: +R$ " + String.format("%.2f", rendimento));
        System.out.printf("Rendimento de R$ %.2f (5%% a.m.) aplicado com sucesso!%n", rendimento);
    }
}
