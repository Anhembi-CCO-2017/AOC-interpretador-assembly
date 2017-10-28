
package calculadora;

public class Calculadora {

    public static void main(String[] args) {

        IdentificarExpressao ie = new IdentificarExpressao();
        
        String num = "! 9";
        
        System.out.println(ie.isEquacao(num));
        
        
    }
    
}
