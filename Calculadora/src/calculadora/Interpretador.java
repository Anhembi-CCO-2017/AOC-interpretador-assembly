package calculadora;
public class Interpretador {
    
    public String valor_digitado;
    private String valor_convertido;
    private String operacao;
    private String[][] matriz_operacao;
    
    public Interpretador() {
    }
    
    public boolean identificarExpressao(){
        IdentificarExpressao op = new IdentificarExpressao();
        String valor = this.valor_digitado;
        if (op.isAssembly(valor)) {
            this.matriz_operacao = op.matriz_operacao;
            this.operacao = "eqc";
            System.out.println("ASS");
            return true;
        }else if (op.isEquacao(valor)) {
            System.out.println("EQ");
            this.operacao = "asy";
            return true;
        }
        System.out.println("Operacao invalida");
        return false;
    }
    
    
}
