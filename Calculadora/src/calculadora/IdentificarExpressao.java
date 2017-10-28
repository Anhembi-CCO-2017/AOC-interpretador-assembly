package calculadora;

public class IdentificarExpressao {

    public IdentificarExpressao() {
    }
    
    public boolean isAssembly(String valor){
        return false;
    }
    
    public boolean isEquacao(String valor){
        Equacao eq = new Equacao();
        
        if (eq.isEquacao(valor)) {
          
            return true;  
        }
        return false;
    }
}
