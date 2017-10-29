package calculadora;

public class Interpretador {

    private Assembly asy = new Assembly();
    private Equacao eqc = new Equacao();
    public String data;
    
    private int operacao = 0; //1 -> Assembly, 2 -> Equacao

    public Interpretador() {}
    
    public boolean identificarExpressao() {
        if (this.isAssembly(this.data))
            return true;
        else if (this.isEquacao(this.data))
            return true;

        System.out.println("Operacao invalida");
        return false;
    }

    public void build() {
        if(this.operacao == 1)
            asy.convert();
        else if(this.operacao == 2)
            eqc.convert();
    }
    
    private boolean isAssembly(String valor) {
        if(asy.isAssembly(valor)) {
            this.operacao = 1;
            return true;
        }
        
        return false;
    }

    private boolean isEquacao(String valor) {        
        if (eqc.isEquacao(valor)) {
            this.operacao = 2;
            return true;
        }

        return false;
    }
    
}
