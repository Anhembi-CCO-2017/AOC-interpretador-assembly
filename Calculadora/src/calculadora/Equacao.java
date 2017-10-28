
package calculadora;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equacao {

    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){

        char[] vetor = {'/','*','+','-'};
        Pattern regex = Pattern.compile("[$&,\\:;=?!@#|]");
        Matcher matcher = regex.matcher(valor);

        Pattern regex1 = Pattern.compile("[/*+-]");
        Matcher matcher1 = regex1.matcher(valor);
        
        Pattern regex2 = Pattern.compile("[a-zA-Z]");
        Matcher matcher3 = regex2.matcher(valor);
        
        // numeros de 0-9
        Pattern regex3 = Pattern.compile("[0-9]");
        Matcher matcher4 = regex3.matcher(valor);
        
        // identificar se tem caracteres especias
        if (matcher.find()) {
            return false;
        }
        
        //saber se tem mescla de numeros e letras
        if(matcher3.find()){
            if (matcher4.find()) {
                return false;
            }
        }
        
        
        for (int i = 0; i < valor.length(); i++) {
            for (int j = 0; j < vetor.length; j++) {
                if (valor.charAt(i) == vetor[j]) {
                    for (int k = 0; k < vetor.length; k++) 
                        if (valor.charAt(i+1) == vetor[k]) 
                            return false;
            }
           }
        }
        
        
        
        
        
        
        int qtdaberto=0;
        int qtdfechado=0;
        
        //verifica se tem os mesmos numeros de parenteses!
        for (int i = 0; i < valor.length(); i++) {
            
            if (valor.charAt(i) == '(') {
                
                qtdaberto ++;
            }
            if (valor.charAt(i) == ')') {
                
                qtdfechado ++;
            }
        }
        
        if (qtdaberto != qtdfechado) {
            
            return false;
        }
        
        // checar se tem  tem formula
        if (matcher1.find()) {
            //saber se a formula é no final
            for (int j = 0; j < vetor.length; j++){
                if (valor.charAt(0) == vetor[j] || valor.charAt(valor.length() - 1) == vetor[j]){
                    return false;
                }
            }
            splitEquation(valor);
            return true;
        }
        
        
        
        return false;
    }
    
    public String[][] splitEquation(String valor){
        String[][] name = new String[3][3];
        Pattern pattern = Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\+\\-\\*/\\(\\)]))");
        Matcher m = pattern.matcher(valor);
        
        while (m.find()) {
            System.out.printf("%s ", m.group());
        }
        
        return name;
    }

    
    
}
