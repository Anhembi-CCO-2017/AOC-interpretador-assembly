
package calculadora;

public class Calculadora {

    public static void main(String[] args) {

        IdentificarExpressao ie = new IdentificarExpressao();
        
        String num = "move add ";
        
        System.out.println(ie.isEquacao(num));
        
        
    }
    
}
