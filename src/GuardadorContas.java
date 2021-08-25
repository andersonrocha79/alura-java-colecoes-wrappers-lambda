public class GuardadorContas
{

    private Conta[] referencias;
    private int posicaoLivre;

    public GuardadorContas()
    {
        this.referencias = new Conta[10];
        this.posicaoLivre = 0;
    }

    public void adiciona(Conta ref)
    {
        this.referencias[posicaoLivre] = ref;
        posicaoLivre++;
    }

    public int getQuantidadeElementos()
    {
        return this.posicaoLivre;
    }

    public Conta getReferencia(int i)
    {
        return this.referencias[i];
    }
}
