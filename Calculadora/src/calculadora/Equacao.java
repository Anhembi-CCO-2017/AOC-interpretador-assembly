
package calculadora;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equacao {

    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){

        char[] vetor = {'/','*','+','-'};
        Pattern regex = Pattern.compile("[$&,:;=?!@#|]");
        Matcher matcher = regex.matcher(valor);
        
        System.out.println("Check 1");
        if (matcher.find()) {
            return false;
        }
        
        Pattern regex1 = Pattern.compile("[/*+-]");
        Matcher matcher1 = regex1.matcher(valor);
        
        Pattern regex2 = Pattern.compile("[a-zA-Z]");
        Matcher matcher3 = regex2.matcher(valor);
        
        Pattern regex3 = Pattern.compile("[0-9]");
        Matcher matcher4 = regex3.matcher(valor);
        
        if(matcher1.find()){
            if (matcher4.find()) {
                return false;
            }
            return true;
        }
        
        System.out.println("Check 2");
        for (int j = 0; j < vetor.length; j++){
            if (valor.charAt(0) == vetor[j] || valor.charAt(valor.length() - 1) == vetor[j]){
                return false;
            }
        }
        
        System.out.println("Check 3");
        if (matcher1.find()) {
            return true;
        }
        
        return false;
    }
    
}
