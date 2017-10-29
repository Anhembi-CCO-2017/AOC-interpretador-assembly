
package calculadora;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equacao {
    private String valor;
    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){
        this.valor = valor;
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
            convert();
            return true;
        }

        return false;
    }
    
    public String convert(){
        String result = "";
        System.out.println(valor);
        String[] vars = {"x", "y", "t"};
        Pattern p = Pattern.compile("\\(([^()]*)\\)((\\\\d*\\\\.\\\\d+)|(\\\\d+)|([\\\\+\\\\-\\\\*/\\\\(\\\\)]))");
        Matcher m = p.matcher(valor);

        System.out.println(m.groupCount());
        if(m.find()){
            for (int i = 0; i < m.groupCount(); i++) {
                System.out.println(m.group(i));
            }
       
        }

        for (int i = 0; i < valor.length(); i++) {
            
            switch(valor.charAt(i)){
                
                case '(':
                    if(i == 0){
                        result += "MOVE ";
                    }
                    break;
                case ')':
                    break;
                    
                case '+':
                    result += "ADD ";
                    
                    break;
                case '-':
                    result += "SUB ";

                    break;
                case '/':
                    result += "DIV ";
                    break;
                case '*':
                    result += "MPY ";
                    break;
                default:
                    if (i == 0) {
                        result += "MOVE "+ vars[0] + ", " +valor.charAt(i)+"\n";
                    }else {
                        result += valor.charAt(i) + "\n";
                    }
                    
            }
        }
        System.out.println(result);
        
        return result;
    }
   
    //Gera letras do alfabeto aleatórias
    public char rndChar () {
        
        int rnd = (int) (Math.random() * 52);
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    }
}
