public class Gerente extends Funcionario {
    private String agencia;

    public Gerente(String nome, String senha, String agencia){
        super(nome, senha);
        this.agencia = agencia;
    }
    public String getAgencia(){
        return agencia;
    }
    public String toString(){
        return "Gerente: " + nome + "| Agência: " + agencia;
    }
    
}
