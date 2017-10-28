
package calculadora;

public class Interpretador {

    private String valor_digitado;
    private String valor_convertido;
    private String operacao;
    private String[][] matriz_operacao;
    
    public Interpretador(String valor_digitado) {
        
        this.valor_digitado = valor_digitado;
    
    }
    
    public boolean identificarExpressao(String valor){
        IdentificarExpressao op = new IdentificarExpressao();
        
        if (op.isAssembly(valor)) {
            this.operacao = "eqc";
            return true;
        }else if (op.isEquacao(valor)) {
            this.operacao = "asy";
            return true;
        }
        return false;
    }
    
}
