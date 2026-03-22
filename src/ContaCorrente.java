public class ContaCorrente extends Conta implements Tributavel{

    private static final double TAXA_MANUTENCAO = 20.0;
    private static final double ALIQUOTA_TRIBUTO = 0.02;

    public ContaCorrente(String titular){
        super(titular);
    }
    public String getTipo(){
        return "Conta Corrente";
    }
    public double calcularTributo(){
        return getSaldo() * ALIQUOTA_TRIBUTO;
    }

    public void aplicarTaxaManutencao(){
        if(getSaldo() >= TAXA_MANUTENCAO){
        setSaldo(getSaldo() - TAXA_MANUTENCAO);
        addHistorico("Taxa de manutenção: R$" + String.format("%.2f", TAXA_MANUTENCAO));
        System.out.printf("Taxa de manuntenção de R$ %.2f descontada.%n", TAXA_MANUTENCAO);
        } else{
            System.out.println("Saldo insuficiente para cobrar taxa de manuntenção.");
        }
    }
}
