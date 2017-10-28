
package calculadora;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equacao {

    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){

        Pattern regex = Pattern.compile("[$&+,:;=?!@#|]");
        Matcher matcher = regex.matcher(valor);
        System.out.println(matcher.find());
        char[] vetor = {'/','*','+','-'};
        
        for (int i = 0; i < valor.length(); i++) 
            for (int j = 0; j < vetor.length; j++) 
                if (valor.charAt(i) == vetor[j])
                        return true;
   
        return false;
    }
    
}
