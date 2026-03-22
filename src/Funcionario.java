public class Funcionario implements Autenticavel {

    protected String nome;
    private String senha;
    private int tentativasFalhas;
    private boolean bloqueado;

    private static final int MAX_TENTATIVAS = 3;

    public Funcionario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.tentativasFalhas = 0;
        this.bloqueado = false;
    }
    public boolean autenticar(String senha) {
        if (bloqueado) {
            System.out.println("Conta bloqueada após " + MAX_TENTATIVAS + " tentativas inválidas.");
            return false;
        }
        if (this.senha.equals(senha)) {
            tentativasFalhas = 0;
            return true;
        } else {
            tentativasFalhas++;
            int restantes = MAX_TENTATIVAS - tentativasFalhas;
            if (tentativasFalhas >= MAX_TENTATIVAS) {
                bloqueado = true;
                System.out.println("Senha incorreta. Conta BLOQUEADA por excesso de tentativas.");
            } else {
                System.out.println("Senha incorreta. Tentativas restantes: " + restantes);
            }
            return false;
        }
    }

    public String getNome()     { return nome; }
    public boolean isBloqueado() { return bloqueado; }
}
