
package calculadora;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equacao {

    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){

        char[] vetor = {'/','*','+','-'};
        Pattern regex = Pattern.compile("[$&+,:;=?!@#|]");
        Matcher matcher = regex.matcher(valor);
        
        Pattern regex1 = Pattern.compile("[/*+-]");
        Matcher matcher1 = regex1.matcher(valor);
         
        for (int j = 0; j < vetor.length; j++)
                if (valor.charAt(0) == vetor[j]) 
                        return false;
        
        
        if (matcher.find() == false)
            
            return false;
        
        
        if (matcher1.find())
            return true;
        
        
        return false;
    }
    
}
