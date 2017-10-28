
package calculadora;

public class Equacao {

    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){
    
        char[] vetor = {'/','*','+','-'};
        
        for (int i = 0; i < valor.length(); i++) 
            for (int j = 0; j < vetor.length; j++) 
                if (valor.charAt(i) == vetor[j])
                        return true;
            
            
        
        
        return false;
    }
    
}
