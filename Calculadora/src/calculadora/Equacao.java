
package calculadora;

public class Equacao {

    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){
    
        char[] vetor = {'/','*','+','-'};
        
        for (int i = 0; i < valor.length(); i++) 
            for (int j = 0; j < vetor.length; j++)
                if (valor.charAt(i) == vetor[j]){
                    if (valor.charAt(0) == vetor[j]) 
                        return false;
                    
                    return true;
                }
        
        return false;
    }
    
}
