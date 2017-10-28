package calculadora;

public class IdentificarExpressao {
    private String[][] matriz_operacao;
    
    public IdentificarExpressao() {
    }
    
    public boolean isAssembly(String valor){
        Assembly eq = new Assembly();

        if(eq.isAssembly(valor))
            return true;

        return false;
    }
    
    public boolean isEquacao(String valor){
        Equacao eq = new Equacao();
        
        if (eq.isEquacao(valor))
            return true;

        return false;
    }
}
